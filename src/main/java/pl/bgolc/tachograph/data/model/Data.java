package pl.bgolc.tachograph.data.model;

import pl.bgolc.tachograph.data.model.misdemeanors.MisdemeanorsDaily;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * Class representing data 
 * from tachograph
 * */
@Entity
@Table(name="data")
public class Data implements Serializable, Comparable<Data>{

    @Transient
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int dataId;

    @Column(name="driver_id")
    private int driverId;

    @Column(name="date")
    private LocalDate localDate;

    @Column(name="activity")
    private String activity;

    @Column(name="start_time")
    private LocalTime startTime;

    @Column(name="end_time")
    private LocalTime endTime;

    @Column(name="time_spent")
    private LocalTime timeSpent;

/*    @Transient
    private MisdemeanorsDaily misdemeanors;*/

    /*
     * Constructors
     * */
    public Data () {
        /*misdemeanors = new MisdemeanorsDaily();*/
    }

    public Data(int driverId) {
        this.driverId = driverId;
        /*misdemeanors = new MisdemeanorsDaily();*/
    }

    @Override
    public int compareTo(Data o) {
        return localDate.compareTo(o.localDate);
    }

    /*
     * Getters
     * */
    public int getDataId() {
        return dataId;
    }

    public int getDriverId() {
        return driverId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getActivity() {
        return activity;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getTimeSpent() {
        return timeSpent;
    }

  /*  public MisdemeanorsDaily getMisdemeanors() {
        return misdemeanors;
    }*/

    /*
     * Setters
     * */
    public void setDataId(int id) {
        this.dataId = id;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setTimeSpent(LocalTime timeSpent) {
        this.timeSpent = timeSpent;
    }

    /*public void setMisdemeanors(MisdemeanorsDaily misdemeanors) {
        this.misdemeanors = misdemeanors;
    }*/
}
