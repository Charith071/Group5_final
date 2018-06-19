package com.example.demo.Service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AllUsers;

@Service
public class KeycloakTokenValidater {
	
	@Autowired
	private AllusersService allusersService;
	
	public String Validate(String jwtToken,String secId) {
		try {
			 System.out.println("------------ Decode JWT ------------");
		        String[] split_string = jwtToken.split("\\.");
		        String base64EncodedHeader = split_string[0];
		        String base64EncodedBody = split_string[1];
		        String base64EncodedSignature = split_string[2];

		      //  System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
		        Base64 base64Url = new Base64(true);
		        String header = new String(base64Url.decode(base64EncodedHeader));
		        System.out.println("JWT Header : " + header);


		     //   System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
		        String body = new String(base64Url.decode(base64EncodedBody));
		        System.out.println("JWT Body : "+body);  
		       
		       //return the type : JWT
		        String type=header.split(":")[2].split(",")[0].trim().replaceAll("^\"|\"$", "");
		      //  System.out.println(type);
		       
		      //return the role
		       String role1=body.split(":")[17].split("}")[0].replaceAll("\\[", "").replaceAll("\\]","").split(",")[0].replaceAll("^\"|\"$", "").trim();
		       String role2=body.split(":")[17].split("}")[0].replaceAll("\\[", "").replaceAll("\\]","").split(",")[1].replaceAll("^\"|\"$", "").trim();
		       String kid=body.split(":")[9].split(",")[0].trim().replaceAll("^\"|\"$", "");
		       System.out.println(kid);
		       
		       if(allusersService.is_exisit_by_keycloakId(kid)) {
		    	 if(allusersService.getInstanceFrom_keycloakId(kid).getId().toString().equals(secId)) {
		    		 if(type.contentEquals("JWT")) {
				    	   if(role1.equals("user") || role2.equals("user")) {
				    		   return "user";
				    	   }else if(role1.equals("counceller") || role2.equals("counceller")) {
				    		   return "counceller";
				    	   }else if(role1.equals("admin") || role2.equals("admin")) {
				    		   return "admin";
				    	   }else {
				    		   return "Fail";
				    	   }
				       }else {
				    	   return "Fail";
				       }
		    	 }else {
		    		 return "Fail";
		    	 }
		    	   
		       }else {
		    	   return "Fail";
		       }
		       		

		      // System.out.println(role1+ "\n"+role2);
		       // return body+header;
		} catch (Exception e) {
			return "Fail";
		}
       
        
       
        
        
       
    }

}
