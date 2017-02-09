package com.example.psh.myproject.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psh.myproject.R;
import com.example.psh.myproject.iteminfo.GlobalTimeInfomation;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalTimeFragment extends Fragment {
    private ListView mList = null;
    public GlobalTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.globaltime_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.globaltimefragment, container, false);
        mList = (ListView) view.findViewById(R.id.globaltime_list);
        ArrayList<GlobalTimeInfomation> temp = new ArrayList<>();
        temp.add(new GlobalTimeInfomation("서울","2월9일 목요일","오후 3:30"));
        temp.add(new GlobalTimeInfomation("도쿄","2월9일 목요일","오후 2:30"));
        temp.add(new GlobalTimeInfomation("런던","2월8일 수요일","오후 6:30"));
        GlobalTimeAdapter adapter = new GlobalTimeAdapter(getActivity(), temp);
        mList.setAdapter(adapter);
        setHasOptionsMenu(true);
        return view;
    }

    public class GlobalTimeAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private ViewHolder mHolder = null;
        private ArrayList<GlobalTimeInfomation> mList = null;

        public GlobalTimeAdapter(Context c, ArrayList<GlobalTimeInfomation> list){
            mInflater = LayoutInflater.from(c);
            mList = list;
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if(v == null){
                mHolder = new ViewHolder();
                v = mInflater.inflate(R.layout.globaltime_item, null);
                mHolder.locationText = (TextView) v.findViewById(R.id.locationText);
                mHolder.dateText = (TextView) v.findViewById(R.id.dateText);
                mHolder.timeText = (TextView) v.findViewById(R.id.timeText);
                v.setTag(mHolder);
            }
            else{
                mHolder = (ViewHolder)v.getTag();
            }

            mHolder.locationText.setText(mList.get(position).getLocationText());
            mHolder.dateText.setText(mList.get(position).getDateText());
            mHolder.timeText.setText(mList.get(position).getTimeText());

            return v;
        }

        class ViewHolder{
            TextView locationText;
            TextView dateText;
            TextView timeText;
        }
    }
}
