package com.example.alfreddemo;


import android.content.Context;
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

public class Examine extends Fragment {
    LinearLayout loginWindow;

    public Examine(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.examine, container, false);
        TextView startnum = view.findViewById(R.id.startnumber);
        loginWindow = view.findViewById(R.id.status);

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String user = sh.getString("user", "");

        if(user.equals("")){
            loginWindow.setVisibility(View.VISIBLE);
        }

        loginWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LogIn.class));
            }
        });

        startnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Examine2();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.host_view_examine, fragment).commit();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String user = sh.getString("user", "");

        if(user.equals("")){
            loginWindow.setVisibility(View.VISIBLE);
        }else{
            loginWindow.setVisibility(View.GONE);
        }
    }

}
