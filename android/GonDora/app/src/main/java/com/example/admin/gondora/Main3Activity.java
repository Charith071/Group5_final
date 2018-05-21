package com.example.admin.gondora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private EditText birthdatetxt,phoneNumbertxt,profilepictxt;
    private RadioGroup group2;
    private Button nextbtn;
    private RadioButton typebtn;


    private String birthdate,phoneNumber,profilepicname,type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        birthdatetxt=findViewById(R.id.bdate_txt);
        phoneNumbertxt=findViewById(R.id.phoneNo_txt);
        profilepictxt=findViewById(R.id.pic_txt);
        group2=findViewById(R.id.radio_group2);
        nextbtn=findViewById(R.id.next2_btn);

        set_next_btn_clickListner();

    }

    public void set_next_btn_clickListner(){
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(birthdatetxt.getText().length()>0 && phoneNumbertxt.getText().length()>0 &&
                        profilepictxt.getText().length()>0){
                    typebtn=findViewById(group2.getCheckedRadioButtonId());
                    if(typebtn.getText().equals("Counceller")){
                        Intent intent=new Intent(getApplicationContext(),counceller.class);
                        intent.putExtra("name",getIntent().getExtras().getString("name"));
                        intent.putExtra("address",getIntent().getExtras().getString("address"));
                        intent.putExtra("email",getIntent().getExtras().getString("email"));
                        intent.putExtra("username",getIntent().getExtras().getString("username"));
                        intent.putExtra("password",getIntent().getExtras().getString("password"));
                        intent.putExtra("gender",getIntent().getExtras().getString("gender"));

                        intent.putExtra("birthdate",birthdatetxt.getText());
                        intent.putExtra("phoneNumber",phoneNumbertxt.getText());
                        intent.putExtra("profilepicture",profilepictxt.getText());
                        intent.putExtra("type",typebtn.getText());
                        startActivity(intent);



                    }else{
                        Intent intent1=new Intent(getApplicationContext(),patiant.class);
                        intent1.putExtra("name",getIntent().getExtras().getString("name"));
                        intent1.putExtra("address",getIntent().getExtras().getString("address"));
                        intent1.putExtra("email",getIntent().getExtras().getString("email"));
                        intent1.putExtra("username",getIntent().getExtras().getString("username"));
                        intent1.putExtra("password",getIntent().getExtras().getString("password"));
                        intent1.putExtra("gender",getIntent().getExtras().getString("gender"));
                        intent1.putExtra("birthdate",birthdatetxt.getText().toString());
                        intent1.putExtra("phoneNumber",phoneNumbertxt.getText().toString());
                        intent1.putExtra("profilepicture",profilepictxt.getText().toString());
                        intent1.putExtra("type",typebtn.getText());

                       // Toast.makeText(getApplicationContext(),birthdatetxt.getText(),Toast.LENGTH_LONG).show();


                         startActivity(intent1);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Fields Cannot be Empty!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
