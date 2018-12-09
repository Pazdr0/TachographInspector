package pl.bgolc.tachograph.data.model;

import pl.bgolc.tachograph.data.model.misdemeanors.MisdemeanorsDaily;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Day {


    private List<Data> dataList;
    private LocalDate localDate;
    private MisdemeanorsDaily misdemeanorsDaily;

//    private int index;
//    private List<String> activityList;
//    private List<String> timeSpentList;

    /*
     * Constructor
     * */
    public Day() {
        dataList = new ArrayList<Data>();
        misdemeanorsDaily = new MisdemeanorsDaily();
    }

    public String checkMisdemeanors() {
        if (misdemeanorsDaily.isExceededDailyDriveTime() || misdemeanorsDaily.isExceededOneTimeDrive() || misdemeanorsDaily.isInsufficientDailyBreak()) {
            return "tak";
        }
        return "nie";
    }
    /*
     * Getters
     * */
    public List<Data> getDataList() {
        return dataList;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public MisdemeanorsDaily getMisdemeanorsDaily() {
        return misdemeanorsDaily;
    }

    /*
     * Setters
     * */
    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setMisdemeanorsDaily(MisdemeanorsDaily misdemeanorsDaily) {
        this.misdemeanorsDaily = misdemeanorsDaily;
    }
}
