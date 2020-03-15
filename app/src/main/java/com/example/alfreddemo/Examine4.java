package com.example.alfreddemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class Examine4 extends Fragment {

    View corrpg, incorrpg, ref;
    Button retake;

    public Examine4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.examine4, container, false);

        final int corr = Integer.parseInt(getArguments().getString("correct_count"));

        corrpg = view.findViewById(R.id.correctpg);
        incorrpg = view.findViewById(R.id.incorrprog);
        ref = view.findViewById(R.id.reffe4);
        ref = view.findViewById(R.id.reffe4);
        retake = view.findViewById(R.id.retake);
        TextView cortv = view.findViewById(R.id.corrcount);
        TextView incortv = view.findViewById(R.id.incorrcount);
        TextView summary = view.findViewById(R.id.summary);

        cortv.setText("Correct : " + corr);
        incortv.setText("Incorrect : " + (10 - corr));

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Summary();
                Bundle args = new Bundle();
                args.putString("questions", getArguments().getString("questions"));
                args.putString("correctOption", getArguments().getString("correctOption"));
                args.putString("optedOption", getArguments().getString("optedOption"));
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.host_view_examine, fragment).commit();
            }
        });

        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(Examine4.this).commit();
            }
        });

        ViewTreeObserver observer = view.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // Do what you need with yourView here...
                ViewGroup.LayoutParams layoutParams = corrpg.getLayoutParams();
                ViewGroup.LayoutParams layoutParams2 = incorrpg.getLayoutParams();
                ViewGroup.LayoutParams reff = ref.getLayoutParams();

                int width = ref.getWidth();

                Log.e("width", width + " ");

                layoutParams.width = (int) ((double) (width / 10) * corr);
                layoutParams2.width = (int) ((double) (width / 10) * (10 - corr));

                corrpg.setLayoutParams(layoutParams);
                incorrpg.setLayoutParams(layoutParams2);
            }
        });

        return view;
    }
}
