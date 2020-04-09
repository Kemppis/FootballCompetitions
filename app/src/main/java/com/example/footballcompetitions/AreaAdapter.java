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

public class AreaAdapter extends ArrayAdapter<Area> {

    public AreaAdapter(Context context, ArrayList<Area> areas) {
        super(context,0, areas);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Area area = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            layoutId = R.layout.area_list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView textViewAreaName = convertView.findViewById(R.id.textViewAreaName);
        textViewAreaName.setText(area.getName());

        return convertView;
    }

}
