package com.example.alfreddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnlogin = findViewById(R.id.btnlogin);
        Button btnsignup = findViewById(R.id.btnsign);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("user", "email");

                myEdit.apply();
                //startActivity(new Intent(getApplicationContext(), MainPagerActivity.class));
                finish();
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), MainPagerActivity.class));

                Toast.makeText(LogIn.this, "Will Open SignUp", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
