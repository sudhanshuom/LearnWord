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

public class Examine extends Fragment {

    LinearLayout status;
    public Examine(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learn, container, false);
        TextView startnum = view.findViewById(R.id.startnumber);
        status = view.findViewById(R.id.status);

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sh.getString("user", "null");
        if(s1 == null){
            status.setVisibility(View.VISIBLE);

            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), LogIn.class));
                }
            });
        }

        startnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Examine2.class));
            }
        });

        return view;
    }

}
