package com.example.mcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class ListFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private LeaderBoardAdapter itemAdapter;
    private List<LeaderModel> itemList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.RecyclerViewLeaderBoard);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        itemList = generateItemList(); // Replace this with your own logic to generate the list of items
        itemAdapter = new LeaderBoardAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
        return view;
    }

    private List<LeaderModel> generateItemList() {
        // Replace this with your own logic to generate the list of items dynamically
        List<LeaderModel> itemList = new ArrayList<>();
        itemList.add(new LeaderModel("Item 1","user",1));
        itemList.add(new LeaderModel("Item 2","user",2));
        itemList.add(new LeaderModel("Item 3","user",3));
        // ...
        return itemList;
    }
}
