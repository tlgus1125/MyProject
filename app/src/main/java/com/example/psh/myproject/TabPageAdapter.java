package com.example.psh.myproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKD on 2017-02-01.
 */

public class TabPageAdapter extends FragmentPagerAdapter {

    private final List<TitleInfo> mFragmentTitleList = new ArrayList<TitleInfo>();

    public TabPageAdapter(FragmentManager fm){
        super(fm);
    }

    public void addFragment(int iconResId, String title, Fragment fragment) {
        TitleInfo info = new TitleInfo(iconResId, title, fragment);
        mFragmentTitleList.add(info);
    }

    public TitleInfo getFragmentInfo(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentTitleList.get(position).getFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position).getTitleText();
    }

    @Override
    public int getCount() {
        return mFragmentTitleList.size();
    }

    public static class TitleInfo {
        private int iconResId;
        private String text;
        private Fragment fragment;

        public TitleInfo(int iconResId, String text, Fragment fragment) {
            this.iconResId = iconResId;
            this.text = text;
            this.fragment = fragment;
        }

        public int getIconResId() {
            return iconResId;
        }

        public String getTitleText() {
            return text;
        }

        public Fragment getFragment() {
            return fragment;
        }
    }
}
