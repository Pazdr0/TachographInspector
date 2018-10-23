package pl.bgolc.tachograph.datamanager.validation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Day {

//    private LocalDate localDate;
//    private List<Data> dataList;
    private int index;
    private List<String> activityList;
    private List<String> timeSpentList;

    /*
     * Constructor
     * */
    protected Day() {
//        dataList = new ArrayList<Data>();
        this.activityList = new ArrayList<String>();
        this.timeSpentList = new ArrayList<String>();
    }

    /*
     * Methods
     * */
    public void addActivity(String activity) {
        activityList.add(activity);
    }

    public void addTimeSpent(String timeSpent) {
        timeSpentList.add(timeSpent);
    }

    /*
     * Getters
     * */

/*     public LocalDate getLocalDate() {
        return localDate;
    }*/

    public int getIndex() {
        return index;
    }

    public List<String> getActivityList() {
        return activityList;
    }

    public List<String> getTimeSpentList() {
        return timeSpentList;
    }



    /*
     * Setters
     * */

/*    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }*/

    public void setIndex(int index) {
        this.index = index;
    }

    public void setActivityList(List<String> activity) {
        this.activityList = activity;
    }

    public void setTimeSpentList(List<String> timeSpent) {
        this.timeSpentList = timeSpent;
    }
}
