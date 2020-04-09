package com.example.footballcompetitions;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LeagueAdapter extends ArrayAdapter<League> {

    public LeagueAdapter(Context context, ArrayList<League> leagues) {
        super(context,0, leagues);
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        League league = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            layoutId = R.layout.leagues_list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView textViewLeagueName = convertView.findViewById(R.id.textViewLeagueName);
        textViewLeagueName.setText(league.getName());

        return convertView;
    }

}
