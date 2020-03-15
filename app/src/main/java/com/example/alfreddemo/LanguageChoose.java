package com.example.alfreddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LanguageChoose extends AppCompatActivity {
    String selectedItem = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);
        GridView gv = findViewById(R.id.gridView);
        final TextView tv = findViewById(R.id.city);
        ArrayList<String> list = new ArrayList<>();
        list.add("Mundari");
        LangAdapter adapter = new LangAdapter(getBaseContext());
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                          selectedItem = parent.getItemAtPosition(position).toString();
                                          tv.setVisibility(View.VISIBLE);
                                          tv.setText("Selected Language : " + selectedItem + "\nClick here to learn");
                                      }
                                  }
        );

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("language", selectedItem);

                myEdit.apply();
                startActivity(new Intent(getApplicationContext(), MainPagerActivity.class));
                finish();
            }
        });
    }
}