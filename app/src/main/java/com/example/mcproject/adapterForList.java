package com.example.mcproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterForList extends BaseAdapter {
    ArrayList<ListDetailsListClass> itemsInList;
    Context mContext;

    LayoutInflater inflater;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<ListDetailsListClass> getItemsInList() {
        return itemsInList;
    }

    public void setItemsInList(ArrayList<ListDetailsListClass> itemsInList) {
        this.itemsInList = itemsInList;
    }

    public adapterForList(ArrayList<ListDetailsListClass> itemsInList, Context mContext) {
        this.itemsInList = itemsInList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {return itemsInList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsInList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null){
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate( R.layout.list_items_view,null);
        }

        TextView nameOfItem = (TextView) convertView.findViewById(R.id.nameInList);

        ListDetailsListClass items = itemsInList.get(position);

        nameOfItem.setText( items.getname()+" Donated "+items.getquantity()+" Items");

        return convertView;
    }
}
