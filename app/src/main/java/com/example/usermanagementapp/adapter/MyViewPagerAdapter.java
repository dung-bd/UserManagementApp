package com.example.usermanagementapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.usermanagementapp.fragment.AddFragment;
import com.example.usermanagementapp.fragment.ListFragment;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {


    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AddFragment();
            case 1:
                return new ListFragment();
            default:
                return new ListFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Add person";
                break;

            case 1:
                title = "List person";
                break;
        }
        return title;
    }
}
