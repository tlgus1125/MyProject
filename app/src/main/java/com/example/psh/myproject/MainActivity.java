package com.example.psh.myproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabPageAdapter adapter = new TabPageAdapter(getSupportFragmentManager());

        adapter.addFragment(R.drawable.global_time, getResources().getString(R.string.global_time), new GlobalTimeFragment());
        adapter.addFragment(R.drawable.alram, getResources().getString(R.string.alram), new AlramFragment());
        adapter.addFragment(R.drawable.sleep_time, getResources().getString(R.string.sleep_time), new SleepTimeFragment());
        adapter.addFragment(R.drawable.stop_watch, getResources().getString(R.string.stop_watch), new StopWatchFragment());
        adapter.addFragment(R.drawable.timer, getResources().getString(R.string.timer), new TimerFragment());

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < viewPager.getAdapter().getCount(); i++) {
            tabLayout.getTabAt(i).setIcon(adapter.getFragmentInfo(i).getIconResId());
        }

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
