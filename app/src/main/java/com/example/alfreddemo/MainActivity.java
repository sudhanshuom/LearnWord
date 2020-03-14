package com.example.alfreddemo;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String lang = sh.getString("language", "");

        if(lang.equals("")){
            startActivity(new Intent(getApplicationContext(),LanguageChoose.class));
            finish();
        }else{
            startActivity(new Intent(getApplicationContext(),MainPagerActivity.class));
            finish();
        }

    }
}