package com.example.demo.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.commonFunction.CommnFunction;
import com.example.demo.extra.Loginjson;
import com.example.demo.extra.Signupjson;




@Component
public class KeyCloakService {

	@Value("${keycloak.credentials.secret}")
	private String SECRETKEY;

	@Value("${keycloak.resource}")
	private String CLIENTID;

	@Value("${keycloak.auth-server-url}")
	private String AUTHURL;

	@Value("${keycloak.realm}")
	private String REALM;
	
	CommnFunction func=new CommnFunction();
	

	public String getToken(Loginjson userCredentials) {

		String responseToken = null;
		try {

			String username = userCredentials.getUsername();

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("grant_type", "password"));
			urlParameters.add(new BasicNameValuePair("client_id", CLIENTID));
			urlParameters.add(new BasicNameValuePair("username", username));
			urlParameters.add(new BasicNameValuePair("password",func.string_encript(userCredentials.getPassword())));
			urlParameters.add(new BasicNameValuePair("client_secret", SECRETKEY));

			//System.out.println(urlParameters);
			responseToken = sendPost(urlParameters);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseToken;
		
		

	}

	public String getByRefreshToken(String refreshToken) {

		String responseToken = null;
		try {

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("grant_type", "refresh_token"));
			urlParameters.add(new BasicNameValuePair("client_id", CLIENTID));
			urlParameters.add(new BasicNameValuePair("refresh_token", refreshToken));
			urlParameters.add(new BasicNameValuePair("client_secret", SECRETKEY));

			responseToken = sendPost(urlParameters);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return responseToken;
	}
	
	
//==============signup======================================
	public String createUserInKeyCloak(Signupjson userDTO) {

		int statusId = 0;
		String userId="";
		try {

			UsersResource userRessource = getKeycloakUserResource();

			UserRepresentation user = new UserRepresentation();
			user.setUsername(userDTO.getUsername());
			//user.setEmail(userDTO.getEmailAddress());
			user.setFirstName(userDTO.getName());
			//user.setLastName(userDTO.getLastName());
			user.setEnabled(true);

			// Create user
			Response result = userRessource.create(user);
			
			System.out.println("Keycloak create user response code>>>>" + result.getStatus());

			statusId = result.getStatus();

			if (statusId == 201) {

				 userId = result.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

				System.out.println("User created with userId:" + userId);

				// Define password credential
				CredentialRepresentation passwordCred = new CredentialRepresentation();
				passwordCred.setTemporary(false);
				passwordCred.setType(CredentialRepresentation.PASSWORD);
				passwordCred.setValue(func.string_encript(userDTO.getPassword()));

				// Set password credential
				userRessource.get(userId).resetPassword(passwordCred);
				//user.setClientRoles();
				// set role
				
				if(userDTO.getType().equals("user")) {
					RealmResource realmResource = getRealmResource();
					RoleRepresentation savedRoleRepresentation = realmResource.roles().get("user").toRepresentation();
					realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(savedRoleRepresentation));
				}else {
					RealmResource realmResource = getRealmResource();
					RoleRepresentation savedRoleRepresentation = realmResource.roles().get("counceller").toRepresentation();
					realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(savedRoleRepresentation));
				}
				

				System.out.println("Username==" + userDTO.getUsername() + " created in keycloak successfully");

				
				
				
				
				
			}

			else if (statusId == 409) {
				System.out.println("Username==" + userDTO.getUsername() + " already present in keycloak");

			} else {
				System.out.println("Username==" + userDTO.getUsername() + " could not be created in keycloak");

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return userId;

	}

	// after logout user from the keycloak system. No new access token will be
	// issued.
	public void logoutUser(String userId) {

		UsersResource userRessource = getKeycloakUserResource();

		userRessource.get(userId).logout();

	}

	// Reset passowrd
	public void resetPassword(String newPassword, String userId) {

		UsersResource userResource = getKeycloakUserResource();

		// Define password credential
		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue(newPassword.toString().trim());

		// Set password credential
		userResource.get(userId).resetPassword(passwordCred);
		

	}

	private UsersResource getKeycloakUserResource() {

		Keycloak kc = KeycloakBuilder.builder().serverUrl(AUTHURL).realm("master").username("stresscontroller").password("group5pwd")
				.clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
				.build();

		RealmResource realmResource = kc.realm(REALM);
		UsersResource userRessource = realmResource.users();

		return userRessource;
	}

	private RealmResource getRealmResource() {

		Keycloak kc = KeycloakBuilder.builder().serverUrl(AUTHURL).realm("master").username("stresscontroller").password("group5pwd")
				.clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
				.build();

		RealmResource realmResource = kc.realm(REALM);

		return realmResource;

	}

	private String sendPost(List<NameValuePair> urlParameters) throws Exception {

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(AUTHURL + "/realms/" + REALM + "/protocol/openid-connect/token");

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();

	}

}