package com.example.psh.myproject.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psh.myproject.R;
import com.example.psh.myproject.iteminfo.GlobalTimeInfomation;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopWatchFragment extends Fragment implements View.OnClickListener{

    private TextView myOutput;
    private Button myBtnStart;
    private Button myBtnRec;

    final static int Init =0;
    final static int Run =1;
    final static int Pause =2;

    private int cur_Status = Init;
    private int myCount=1;
    private long myBaseTime;
    private long myPauseTime;

    private ListView mList = null;
    private ArrayList<String> mRecord = new ArrayList<>();
    private Stopwatchadapter mAdapter = null;

    public StopWatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.stopwatchfragment, container, false);
        myOutput = (TextView) v.findViewById(R.id.time_out);
        myBtnStart = (Button) v.findViewById(R.id.btn_start);
        if(myBtnStart != null)
            myBtnStart.setOnClickListener(this);
        myBtnRec = (Button) v.findViewById(R.id.btn_rec);
        if(myBtnRec != null)
            myBtnRec.setOnClickListener(this);

        mList = (ListView) v.findViewById(R.id.stopwatch_list);
        mAdapter = new Stopwatchadapter(getActivity());
        mList.setAdapter(mAdapter);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", getActivity().MODE_PRIVATE);
        int cur_state = pref.getInt("state", 0);
        if(cur_state == Run){
            myBtnStart.setText("멈춤");
            myBtnRec.setEnabled(true);
        }
        return v;
    }

    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){
            myOutput.setText(getTimeOut());
            myTimer.sendEmptyMessage(0);
        }
    };

    public String getTimeOut(){
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myBaseTime;
        String easy_outTime = String.format("%02d:%02d:%02d", outTime/1000 / 60, (outTime/1000)%60,(outTime%1000)/10);
        return easy_outTime;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_start:
                switch(cur_Status){
                    case Init:
                        myBaseTime = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBtnStart.setText("멈춤");
                        myBtnRec.setEnabled(true);
                        cur_Status = Run;
                        break;
                    case Run:
                        myTimer.removeMessages(0);
                        myPauseTime = SystemClock.elapsedRealtime();
                        myBtnStart.setText("시작");
                        myBtnRec.setText("리셋");
                        cur_Status = Pause;
                        break;
                    case Pause:
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += (now- myPauseTime);
                        myBtnStart.setText("멈춤");
                        myBtnRec.setText("기록");
                        cur_Status = Run;
                        break;
                }
                break;
            case R.id.btn_rec:
                switch(cur_Status){
                    case Run:
                        mRecord.add(String.format("%d. %s\n",myCount,getTimeOut()));
                        mAdapter.notifyDataSetChanged();
                        mList.setSelection(mAdapter.getCount() - 1);
                        myCount++;
                        break;
                    case Pause:
                        myTimer.removeMessages(0);
                        myBtnStart.setText("시작");
                        myBtnRec.setText("기록");
                        myOutput.setText("00:00:00");
                        cur_Status = Init;
                        myCount = 1;
                        mRecord.clear();
                        mAdapter.notifyDataSetChanged();
                        myBtnRec.setEnabled(false);
                        break;
                }
                break;

        }
    }

    public class Stopwatchadapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private ViewHolder mHolder = null;

        public Stopwatchadapter(Context c){
            mInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount() {
            return mRecord.size();
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
                v = mInflater.inflate(R.layout.stopwatch_item, null);
                mHolder.recordedText = (TextView) v.findViewById(R.id.recordedText);
                v.setTag(mHolder);
            }
            else{
                mHolder = (ViewHolder)v.getTag();
            }

            mHolder.recordedText.setText(mRecord.get(position));
            return v;
        }

        class ViewHolder{
            TextView recordedText;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("state",cur_Status);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences pref = getActivity().getSharedPreferences("pref", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("state", cur_Status);
        editor.commit();
    }
}
