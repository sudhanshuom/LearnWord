package com.example.alfreddemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class Summary extends Fragment {

    public Summary() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.summary, container, false);

        GridView gv = view.findViewById(R.id.summarygv);
        TextView back = view.findViewById(R.id.backsum);

        String questions[] = getArguments().getString("questions").split("@@@@");
        String correctOption[] = getArguments().getString("correctOption").split("@@@@");
        String optedOption[] = getArguments().getString("optedOption").split("@@@@");

        final ArrayList<String> ques = new ArrayList<>();
        final ArrayList<String> copt = new ArrayList<>();
        final ArrayList<String> optedop = new ArrayList<>();

        for (int i = 0; i < questions.length; i++) {
            ques.add(questions[i]);
            copt.add(correctOption[i]);
            optedop.add(optedOption[i]);
        }

        SummaryAdapter adapter = new SummaryAdapter(getContext(), ques, optedop, copt);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new Examine6();
                Bundle args = new Bundle();
                args.putString("ques", ques.get(position));
                args.putString("opt", optedop.get(position));
                args.putString("cor", copt.get(position));
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.host_view_examine, fragment).commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(Summary.this).commit();
            }
        });

        return view;
    }
}
