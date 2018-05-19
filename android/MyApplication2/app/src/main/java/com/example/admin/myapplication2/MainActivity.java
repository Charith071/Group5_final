package com.example.admin.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private Button signin_btn;
    private String Username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin_btn=findViewById(R.id.signinbtn);

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://206.189.89.19:8081/")
                        .build();

                Api api=retrofit.create(Api.class);

                //request json object eka paatha string json ekata cpy karapn;
                /*
                *       {
                        "username":"user1",
                        "password":"pass1"
                        }
                *
                *
                *
                *
                * uda object eka copy kappn paatha json ekata...
                * /t vage eva auto hedenava..ubata thinne copy karanna vitharai...
                *
                * dependacy ekk thiva gradle eke..eka copy karala sync karapn
                * internet permition hadapn
                *
                * meka bala uba app eken usgen data aragena eva patha json ekata aisin karala
                * yapn ethakota harii...
                *
                *
                * .............fuck me dora...........
                * */

                String json="{\n" +
                        "\t\"username\":\"user1\",\n" +
                        "\t\"password\":\"pass1\"\n" +
                        "}";

                RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),json);
                api.login(requestBody).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext()," login fail",Toast.LENGTH_LONG).show();
                    }
                });


                /*RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),json);
                api.Home().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"sdsa up",Toast.LENGTH_LONG).show();
                    }
                });*/

            }
        });
    }
}
