package com.example.abaka.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sign_in extends AppCompatActivity {
    private EditText userName,password;
    private Button sure,cancle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userName=(EditText)findViewById(R.id.inputUsename2);
        password=(EditText)findViewById(R.id.inputPassword2);
        sure=(Button)findViewById(R.id.Sure2);
        cancle=(Button)findViewById(R.id.Cancel);
        Intent intent = getIntent();
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = userName.getText().toString();
                String result2 = password.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("result",result);
                intent.putExtra("result2",result2);
                setResult(1001,intent);
                finish();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(sign_in.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
