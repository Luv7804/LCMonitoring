package com.example.lcmonitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {
TabLayout tab;
ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tab =findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        viewPagerLCAdapter adapter = new viewPagerLCAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }
}