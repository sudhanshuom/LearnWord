package com.example.alfreddemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LangAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mThumbIds = {
            "Madari", "Hindi", "Santhali", "English", "Ho", "Mandari", "Kurukh", "Santhali",
            "Hindi", "ho", "English", "Kurukh", "Ho", "Mandari", "Kurukh", "Santhali",
            "Hindi", "Ho", "English", "Kurukh", "English", "Kurukh"
    };

    public LangAdapter(Context con) {
        mContext = con;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = LayoutInflater.from(mContext).inflate(R.layout.grid_itemrow, parent, false);
        }
        TextView city = (TextView) view.findViewById(R.id.city);
        city.setText(mThumbIds[position]);
        return view;
    }

}
