package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class main_page extends AppCompatActivity {
    public BottomNavigationView bottomNavigationView;
    public HomeFragment homeFragment = new HomeFragment();
    public ListFragment listFragment = new ListFragment();
    public DonateFragment donateFragment = new DonateFragment();
    public SettingFragment settingFragment = new SettingFragment();

    public PendingDonationsFragment pendingDonationsFragment = new PendingDonationsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This hides title and action bar
        setContentView(R.layout.activity_main_page);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //System.out.println((int)(item.getItemId()) );
                switch ((int)(item.getItemId()) ){
                    case (int)(R.id.home):
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case (int)(R.id.donate):
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,donateFragment).commit();
                        return true;
                    case (int)(R.id.donationList):
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,listFragment).commit();
                        return true;
                    case (int)(R.id.setting):
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingFragment).commit();
                        return true;
                    case (int)(R.id.pendingDonation):
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,pendingDonationsFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }
}