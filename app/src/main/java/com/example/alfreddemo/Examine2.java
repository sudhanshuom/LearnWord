package com.example.alfreddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static android.content.Context.MODE_PRIVATE;

public class Examine2 extends Fragment {

    LinearLayout loginWindow;
    String ques[] = {"What is the sum of 2+14?", "What is the sum of 2+14?", "What is the sum of 2+14?",
            "What is the sum of 2+14?", "What is the sum of 2+14?", "What is the sum of 2+14?",
            "What is the sum of 2+14?", "What is the sum of 2+14?", "What is the sum of 2+14?",
            "What is the sum of 2+14?"};
    String options[] = {"10", "25", "16", "30"};
    int corr = 0;
    String opted[] = new String[10];
    int i = 0;
    boolean correct[] = new boolean[10];
    TextView tv1, tv2, tv3, tv4, qno, question;
    RelativeLayout progressbar;
    public Examine2(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.examine2, container, false);

        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        tv4 = view.findViewById(R.id.tv4);
        question = view.findViewById(R.id.question);
        qno = view.findViewById(R.id.ques_no);

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

        setNextQuestion();

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(tv1);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(tv2);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(tv3);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(tv4);
            }
        });

        return view;
    }

    private void setNextQuestion(){

        if(i >= 10){

            return;
        }
        tv1.setClickable(true);
        tv2.setClickable(true);
        tv3.setClickable(true);
        tv4.setClickable(true);

        qno.setText("Question:" + (i+1) + "/10");
        question.setText(ques[i]);
        tv1.setText(options[0]);
        tv2.setText(options[1]);
        tv3.setText(options[2]);
        tv4.setText(options[3]);
        i++;
    }

    private void checkAnswer(TextView tv){
        opted[i-1] = tv.getText().toString();
        if(tv.getText().toString().equals("16")){
            correct[i-1] = true;
            corr++;
        }

        Fragment fragment = new Examine3();
        Bundle args = new Bundle();
        args.putString("ques", ques[i-1]);
        args.putString("corr", correct[i-1] + "");
        args.putString("o1", "10");
        args.putString("o2", "25");
        args.putString("o3", "16");
        args.putString("o4", "30");
        args.putString("clicked", tv.getText().toString());
        args.putString("count", i+"");
        args.putString("correct_count", corr + "");
        fragment.setArguments(args);

        if(i == 10) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.host_view_examine, fragment).commit();
        }else{
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.host_view_examine, fragment).commit();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setNextQuestion();
            }
        }, 300);

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
