package com.example.psh.myproject.iteminfo;

/**
 * Created by LKD on 2017-02-09.
 */

public class GlobalTimeInfomation {
    private String locationText;
    private String dateText;
    private String timeText;
    public GlobalTimeInfomation(String locationText, String dateText, String timeText){
        this.locationText = locationText;
        this.dateText = dateText;
        this.timeText = timeText;
    }

    public String getLocationText() {
        return locationText;
    }

    public String getDateText() {
        return dateText;
    }

    public String getTimeText() {
        return timeText;
    }
}
