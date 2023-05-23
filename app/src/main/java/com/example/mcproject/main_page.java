package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class main_page extends AppCompatActivity {
TableLayout tab;
ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        tab = findViewById(R.id.tabMainPage);
        viewPager = findViewById(R.id.viewpagerMain);



    }
}