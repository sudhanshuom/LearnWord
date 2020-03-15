package com.example.alfreddemo;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Examine6 extends Fragment {

    public Examine6() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.examine6, container, false);

        TextView back = view.findViewById(R.id.back);
        TextView ques = view.findViewById(R.id.quese6);
        TextView corr = view.findViewById(R.id.corre6);
        TextView opte = view.findViewById(R.id.opte6);
        TextView res = view.findViewById(R.id.rese6);
        ImageView imv = view.findViewById(R.id.imge6);

        String que = getArguments().getString("ques");
        String opt = getArguments().getString("opt");
        String cor = getArguments().getString("cor");

        ques.setText(que);
        corr.setText(cor);
        opte.setText(opt);

        if (opt.equals(cor)) {
            res.setText("Correct Answer");
            imv.setImageResource(R.drawable.correct);
            imv.setBackgroundColor(Color.parseColor("#00FF00"));
        } else {
            res.setText("Incorrect Answer");
            imv.setImageResource(R.drawable.wrong);
            imv.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(Examine6.this).commit();
            }
        });

        return view;
    }
}
