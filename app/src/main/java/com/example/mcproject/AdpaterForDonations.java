package com.example.mcproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.List;


public class AdpaterForDonations extends ArrayAdapter<String> {

    private List<String> items;
    private LayoutInflater inflater;

    public AdpaterForDonations(Context context, List<String> iitems) {
        super(context, 0, iitems);
        this.items = iitems;
        this.inflater = LayoutInflater.from(context);
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

        String item = items.get(position);
        holder.textView.setText(item);

        return convertView;
    }



    private static class ViewHolder {
        TextView textView;
    }
}
