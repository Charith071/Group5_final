package com.example.admin.gondora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class counceller extends AppCompatActivity {

    private EditText certificatetxt, qualificationtxt;
    private Button createbtn;

    String name, address, email, gender, username, password, type, birthdate, phoneNumber, gps,
            certificate, qualification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counceller);

        certificatetxt = findViewById(R.id.certificate_txt);
        qualificationtxt = findViewById(R.id.qualification_txt);
        createbtn = findViewById(R.id.createCouncellet_btn);

        set_councellerbtnLinstner();
    }

    public void set_councellerbtnLinstner() {
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (certificatetxt.getText().length() > 0 && qualificationtxt.getText().length() > 0) {

                    name = getIntent().getExtras().getString("name");
                    address = getIntent().getExtras().getString("address");
                    email = getIntent().getExtras().getString("email");
                    gender = getIntent().getExtras().getString("gender");
                    username = getIntent().getExtras().getString("username");
                    password = getIntent().getExtras().getString("password");
                    // type=getIntent().getExtras().getString("type");
                    type = "counceller";
                    birthdate = getIntent().getExtras().getString("birthdate");
                    phoneNumber = getIntent().getExtras().getString("phoneNumber");
                    certificate = String.valueOf(certificatetxt.getText());
                    qualification = String.valueOf(qualificationtxt.getText());
                    gps = "1234590";


                    send_request();


                } else {
                    Toast.makeText(getApplicationContext(), "Fields Cannot be Empty!!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    public void send_request() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://206.189.89.19:8081/")
                .build();

        Api api = retrofit.create(Api.class);

        String json = "{" +

                "\"name\":" + "\"" + name + "\"" + "," +
                "\"address\":" + "\"" + address + "\"" + "," +
                "\"email\":" + "\"" + email + "\"" + "," +
                "\"gender\":" + "\"" + gender + "\"" + "," +
                "\"username\":" + "\"" + username + "\"" + "," +
                "\"password\":" + "\"" + password + "\"" + "," +
                "\"type\":" + "\"" + type + "\"" + "," +
                "\"birth_date\":" + "\"" + birthdate + "\"" + "," +
                "\"phone_number\":" + "\"" + phoneNumber + "\"" + "," +
                "\"gps\":" + "\"" + gps + "\"" + "," +
                "\"certificate\":" + "\"" + certificate + "\"" + "," +
                "\"qualification\":" + "\"" + qualification + "\"" + "," +
                "\"profile_pic_name\":" + "\"" + "image" + "\"" + "}";

        //  Toast.makeText(getApplicationContext(),json,Toast.LENGTH_LONG).show();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        api.CreateAccount(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Toast.makeText(getApplicationContext(), response.body().string(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Cannot send Requset to server", Toast.LENGTH_LONG).show();

            }
        });

    }
}