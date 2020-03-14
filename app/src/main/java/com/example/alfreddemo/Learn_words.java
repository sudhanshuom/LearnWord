package com.example.alfreddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class Learn_words extends Fragment {

    private TextView word, stats;
    private Button next;
    String currentWord = "";
    LinearLayout wordll, meaningll;
    View progressMastered, progressReviewed, progressLearning, reference;

    public Learn_words(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.learn_words, container, false);

        TextView tap = view.findViewById(R.id.tap);
        TextView knew = view.findViewById(R.id.knew);
        stats = view.findViewById(R.id.stats);
        TextView dontknew = view.findViewById(R.id.dontknew);

        /*
        * Setting progress to progressbar
        * */
        progressMastered = view.findViewById(R.id.progress);
        progressReviewed = view.findViewById(R.id.progress2);
        progressLearning = view.findViewById(R.id.progress3);
        reference = view.findViewById(R.id.ref);

        LinearLayout loginWindow = view.findViewById(R.id.status);

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
                stats.setText("You should create account to see your progress. Click here :)");
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

        ViewTreeObserver observer = view.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // Do what you need with yourView here...
                ViewGroup.LayoutParams layoutParams = progressMastered.getLayoutParams();
                ViewGroup.LayoutParams layoutParams2 = progressReviewed.getLayoutParams();
                ViewGroup.LayoutParams layoutParams3 = progressLearning.getLayoutParams();
                ViewGroup.LayoutParams reff = reference.getLayoutParams();

                int width = reference.getWidth();

                Log.e("width", width + " ");

                layoutParams.width = (int) (Math.ceil((double)(width/50))*10);
                layoutParams2.width = (int) (Math.ceil((double)(width/50))*20);
                layoutParams3.width = (int) (Math.ceil((double)(width/50))*50);

                progressMastered.setLayoutParams(layoutParams);
                progressReviewed.setLayoutParams(layoutParams2);
                progressLearning.setLayoutParams(layoutParams3);
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