package com.example.usermanagementapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.usermanagementapp.R;
import com.example.usermanagementapp.UserSettings;
import com.example.usermanagementapp.adapter.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private RadioGroup radioGroup;
    private View parentView;
    private UserSettings settings;
    private RadioButton radioButton1, radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        radioGroup = findViewById(R.id.radio_group);
        parentView = findViewById(R.id.parentView);
        radioButton1 = findViewById(R.id.radio_light);
        radioButton2 = findViewById(R.id.radio_dark);
        settings = (UserSettings) getApplication();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_light:
                        settings.setCustomTheme(UserSettings.LIGHT_THEME);
                        updateView();;
                        break;
                    case R.id.radio_dark:
                        settings.setCustomTheme(UserSettings.DARK_THEME);
                        updateView();
                        break;

                }
            }
        });

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(myViewPagerAdapter);


        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void updateView(){
        final int black = ContextCompat.getColor(this, R.color.black);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(settings.getCustomTheme().equals(UserSettings.DARK_THEME)){
            parentView.setBackgroundColor(black);
            radioButton1.setTextColor(white);
            radioButton2.setTextColor(white);
        }
        else{
            parentView.setBackgroundColor(white);
            radioButton1.setTextColor(black);
            radioButton2.setTextColor(black);
        }
    }
}