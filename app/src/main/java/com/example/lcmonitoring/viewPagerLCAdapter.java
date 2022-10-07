package com.example.lcmonitoring;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerLCAdapter extends FragmentPagerAdapter {

    public viewPagerLCAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new TakerFragment();
        }else  if(position == 1){
            return new PendingLCFragment();
        }
        else{
            return new GiverFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;// number of tab
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Taker";
        }
        else if(position == 1){
            return "PendingLC";
        }
        else{
            return "Giver";
        }
    }
}
