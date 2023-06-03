package com.example.mcproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdpaterForDonations extends ArrayAdapter<String> {
    private int selectedPosition = -1;
    private List<String> iitems;
    private LayoutInflater inflater;

    public AdpaterForDonations(Context context, List<String> iitems) {
        super(context, 0, iitems);
        this.iitems = iitems;
        this.inflater = LayoutInflater.from(context);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_items_view, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.nameInList);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String item = iitems.get(position);
        holder.textView.setText(item);

        if (position == selectedPosition) {
            holder.textView.setTextColor(Color.RED); // Change the color to your desired color
        } else {
            holder.textView.setTextColor(Color.WHITE); // Change the color to your desired color
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
    }
}

