package com.example.alfreddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

public class Examine3 extends Fragment {

    View ref, prog;

    public Examine3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.examine3, container, false);

        TextView ques = view.findViewById(R.id.qq);
        TextView qc = view.findViewById(R.id.ques_count);
        TextView o1t = view.findViewById(R.id.o1);
        TextView o2t = view.findViewById(R.id.o2);
        TextView o3t = view.findViewById(R.id.o3);
        TextView o4t = view.findViewById(R.id.o4);
        TextView res = view.findViewById(R.id.res);
        TextView next = view.findViewById(R.id.next);
        TextView complete = view.findViewById(R.id.complete);
        TextView outof = view.findViewById(R.id.outof);
        ref = view.findViewById(R.id.reff);
        prog = view.findViewById(R.id.progresse3);

        LinearLayout loginWindow = view.findViewById(R.id.status);

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

        String q = getArguments().getString("ques");
        String o1 = getArguments().getString("o1");
        String o2 = getArguments().getString("o2");
        String o3 = getArguments().getString("o3");
        String o4 = getArguments().getString("o4");
        String corr = getArguments().getString("corr");
        String clicked = getArguments().getString("clicked");
        final int count = Integer.parseInt(getArguments().getString("count"));

        qc.setText("Question:" + count + "/10");
        ques.setText(q);
        o1t.setText(o1);
        o2t.setText(o2);
        o3t.setText(o3);
        o4t.setText(o4);

        if (corr.equals("true")) {
            if (clicked.equals(o1))
                o1t.setTextColor(Color.parseColor("#00FF00"));
            if (clicked.equals(o2))
                o2t.setTextColor(Color.parseColor("#00FF00"));
            if (clicked.equals(o3))
                o3t.setTextColor(Color.parseColor("#00FF00"));
            if (clicked.equals(o4))
                o4t.setTextColor(Color.parseColor("#00FF00"));
        } else {
            if (clicked.equals(o1))
                o1t.setTextColor(Color.parseColor("#FF0000"));
            if (clicked.equals(o2))
                o2t.setTextColor(Color.parseColor("#FF0000"));
            if (clicked.equals(o3))
                o3t.setTextColor(Color.parseColor("#FF0000"));
            if (clicked.equals(o4))
                o4t.setTextColor(Color.parseColor("#FF0000"));
        }

        if (count == 10) {
            next.setText("Your Score");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new Examine4();
                    Bundle args = new Bundle();
                    args.putString("correct_count", getArguments().getString("correct_count"));
                    args.putString("questions", getArguments().getString("questions"));
                    args.putString("correctOption", getArguments().getString("correctOption"));
                    args.putString("optedOption", getArguments().getString("optedOption"));
                    fragment.setArguments(args);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.host_view_examine, fragment).commit();
                }
            });
        } else {
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction().remove(Examine3.this).commit();
                }
            });
        }

        complete.setText("Complete " + (10 - count) + " more questions to unlock next level.");
        outof.setText(count + " out of 10 Completed.");

        if (corr.equals("true")) {
            res.setText("Correct Answer");
            res.setBackground(getResources().getDrawable(R.drawable.corr_bg));
            ;
        } else {
            res.setText("Incorrect Answer");
            res.setBackground(getResources().getDrawable(R.drawable.incorr_bg));
            ;
        }


        ViewTreeObserver observer = view.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // Do what you need with yourView here...
                ViewGroup.LayoutParams layoutParams = prog.getLayoutParams();
                ViewGroup.LayoutParams reff = ref.getLayoutParams();

                int width = ref.getWidth();

                Log.e("width", width + " ");

                layoutParams.width = (int) (Math.ceil((double) (width / 10)) * count);

                prog.setLayoutParams(layoutParams);
            }
        });

        return view;
    }
}
