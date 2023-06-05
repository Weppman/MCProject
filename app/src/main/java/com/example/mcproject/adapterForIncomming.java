package com.example.mcproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterForIncomming extends BaseAdapter {
    ArrayList<ListDetailsIncomingClass> itemsInList;
    Context mContext;

    LayoutInflater inflater;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<ListDetailsIncomingClass> getItemsInList() {
        return itemsInList;
    }

    public void setItemsInList(ArrayList<ListDetailsIncomingClass> itemsInList) {
        this.itemsInList = itemsInList;
    }

    public adapterForIncomming(ArrayList<ListDetailsIncomingClass> itemsInList, Context mContext) {
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
            convertView = inflater.inflate( R.layout.list_items_view_icog,null);
        }

        TextView nameOfItem = (TextView) convertView.findViewById(R.id.nameInList);

        ListDetailsIncomingClass items = itemsInList.get(position);

        String name =items.getfname();

        nameOfItem.setText( items.getfname()+" "+items.getquantity()+" "+items.getiname()+"  Cell: "+items.cellNumber);

        return convertView;
    }
}
