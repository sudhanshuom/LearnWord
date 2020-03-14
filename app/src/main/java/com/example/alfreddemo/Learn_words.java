package com.example.alfreddemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Learn_words extends Fragment {

    private TextView word;
    private Button next;
    String currentWord = "";
    LinearLayout wordll, meaningll;

    public Learn_words(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learn_words, container, false);

        TextView tap = view.findViewById(R.id.tap);
        TextView knew = view.findViewById(R.id.knew);
        TextView dontknew = view.findViewById(R.id.dontknew);


        word = view.findViewById(R.id.meaning);
        next = view.findViewById(R.id.next);
        wordll = view.findViewById(R.id.wordll);
        meaningll = view.findViewById(R.id.meaningll);

        searchNextWord();

        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeMeaning(currentWord);
                wordll.setVisibility(View.GONE);
                meaningll.setVisibility(View.VISIBLE);
            }
        });

        knew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Knew and Mastered", Toast.LENGTH_LONG).show();
            }
        });

        dontknew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Learning", Toast.LENGTH_LONG).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Will search database for next word\nIf completed go to category", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    //From Database see meaning
    private void seeMeaning(String w){
        word.setText("ONE");
    }

    //From Database search new word
    private void searchNextWord(){
        currentWord = "Miyad";
        word.setText(currentWord);
    }
}