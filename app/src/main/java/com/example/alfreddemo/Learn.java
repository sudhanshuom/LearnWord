package com.example.alfreddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static android.content.Context.MODE_PRIVATE;

public class Learn extends Fragment {
    Fragment fragment;
    LinearLayout loginWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.learn, container, false);

        TextView start = view.findViewById(R.id.startnumber);
        loginWindow = view.findViewById(R.id.status);

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String user = sh.getString("user", "");

        if (user.equals("")) {
            loginWindow.setVisibility(View.VISIBLE);
        }

        loginWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LogIn.class));
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new Learn_words();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.host_view, fragment).commit();
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String user = sh.getString("user", "");

        if (user.equals("")) {
            loginWindow.setVisibility(View.VISIBLE);
        } else {
            loginWindow.setVisibility(View.GONE);
        }
    }
}



