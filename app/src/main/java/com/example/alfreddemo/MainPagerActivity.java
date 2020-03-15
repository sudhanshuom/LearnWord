package com.example.alfreddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainPagerActivity extends AppCompatActivity {

    ViewPager vpPager;
    private TabLayout tabLayout;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pager);

        vpPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Learn(), "Learn");
        adapter.addFragment(new Examine(), "Examine");

        vpPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpPager);
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }

        Toast.makeText(this, "Please click BACK again", Toast.LENGTH_SHORT).show();
        doubleBackToExitPressedOnce = true;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
