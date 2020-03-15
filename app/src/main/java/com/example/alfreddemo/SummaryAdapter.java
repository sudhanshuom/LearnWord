package com.example.alfreddemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SummaryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> ques;
    private ArrayList<String> optedOption;
    private ArrayList<String> corrOption;

    public SummaryAdapter(Context con, ArrayList<String> q, ArrayList<String> oO, ArrayList<String> cO) {
        mContext = con;
        ques = q;
        optedOption = oO;
        corrOption = cO;
    }

    public int getCount() {
        return ques.size();
    }

    public Object getItem(int position) {
        return ques.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = LayoutInflater.from(mContext).inflate(R.layout.summary_list_item, parent, false);
        }

        TextView questv = view.findViewById(R.id.questiontext);
        questv.setText(ques.get(position));
        ImageView iv = view.findViewById(R.id.imagev);
        if (optedOption.get(position).equals(corrOption.get(position))) {
            iv.setImageResource(R.drawable.correct);
            iv.setBackground(mContext.getResources().getDrawable(R.drawable.corr_bg));
        } else {
            iv.setImageResource(R.drawable.wrong);
            iv.setBackground(mContext.getResources().getDrawable(R.drawable.incorr_bg));
        }
        return view;
    }

}
