package com.example.abaka.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button register,sure;
    private EditText userName,password;
    public EditText nameView;
    public  static String sName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register=(Button)findViewById(R.id.Register1);
        sure=(Button)findViewById(R.id.Sure1);
        userName=(EditText)findViewById(R.id.inputUsename1);
        password=(EditText)findViewById(R.id.inputPassword1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,sign_in.class);
                String s1 = userName.getText().toString();
                String s2 = password.getText().toString();
                startActivityForResult(intent,1000);
            }
        });

        //sName = userName.getText().toString();

            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sName = userName.getText().toString();
                    if (!"".equals(sName)) {
                        Intent intent = new Intent(MainActivity.this, count.class);
                        startActivity(intent);
                    }
                }
            });

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1000 && resultCode == 1001){
            String result = data.getStringExtra("result");
            String result2 = data.getStringExtra("result2");
            userName.setText(result);
            password.setText(result2);
        }
    }
}
