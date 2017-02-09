package com.example.psh.myproject.pageadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.psh.myproject.fragment.AlramFragment;
import com.example.psh.myproject.fragment.GlobalTimeFragment;
import com.example.psh.myproject.fragment.SleepTimeFragment;
import com.example.psh.myproject.fragment.StopWatchFragment;
import com.example.psh.myproject.fragment.TimerFragment;

/**
 * Created by LKD on 2017-02-01.
 */

public class TabPageAdapter extends FragmentPagerAdapter {
    int tabcount;
    public TabPageAdapter(FragmentManager fm, int num){
        super(fm);
        this.tabcount = num;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                GlobalTimeFragment tab1 = new GlobalTimeFragment();
                return tab1;
            case 1:
                AlramFragment tab2 = new AlramFragment();
                return tab2;
            case 2:
                SleepTimeFragment tab3 = new SleepTimeFragment();
                return tab3;
            case 3:
                StopWatchFragment tab4 = new StopWatchFragment();
                return tab4;
            case 4:
                TimerFragment tab5 = new TimerFragment();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
