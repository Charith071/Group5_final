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

public class MainActivity extends AppCompatActivity {

    private EditText Fullnametxt,AddressTxt,emailtxt,usernametxt,passwordtxt;
    private RadioGroup group1;
    private RadioButton genderbtn;
    private Button nextbtn;


    private String name,address,email,username,password,gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fullnametxt=findViewById(R.id.name_txt);
        AddressTxt=findViewById(R.id.address_txt);
        emailtxt=findViewById(R.id.email_txt);
        usernametxt=findViewById(R.id.username_txt);
        passwordtxt=findViewById(R.id.pwd_txt);
        group1=findViewById(R.id.radiogroup);


        nextbtn=findViewById(R.id.next_btn);

       set_nextbtnClick_listner();
    }

    public void set_nextbtnClick_listner(){
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Fullnametxt.getText().length()>0 && AddressTxt.getText().length()>0 && emailtxt.getText().length() >0
                        && usernametxt.getText().length()>0 && passwordtxt.getText().length()>0){

                    name= String.valueOf(Fullnametxt.getText());
                    address= String.valueOf(AddressTxt.getText());
                    email= String.valueOf(emailtxt.getText());
                    username= String.valueOf(usernametxt.getText());
                    password= String.valueOf(passwordtxt.getText());
                    genderbtn=findViewById(group1.getCheckedRadioButtonId());
                    gender= String.valueOf(genderbtn.getText());

                    /*Toast.makeText(getApplicationContext(),name+"\n"+address+"\n"
                            +email+"\n"+username+"\n"+password+"\n"+gender,Toast.LENGTH_LONG).show();*/

               Intent myintent=new Intent(getApplicationContext(),Main3Activity.class);
                    myintent.putExtra("name",name);
                    myintent.putExtra("address",address);
                    myintent.putExtra("email",email);
                    myintent.putExtra("username",username);
                    myintent.putExtra("password",password);
                    myintent.putExtra("gender",gender);

                    startActivity(myintent);

                }else{
                    Toast.makeText(getApplicationContext(),"Fields Cannot be Empty!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
