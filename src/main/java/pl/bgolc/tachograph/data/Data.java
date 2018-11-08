package pl.bgolc.tachograph.data;

import pl.bgolc.tachograph.data.temporary.MisdemeanorsDaily;

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
public class Data implements Serializable {

    @Transient
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int dataId;

    @Column(name="driver_id")
    private int driverId;

    @Column(name="activity")
    private String activity;

    /*
     *String date converted to LocalDate object
     * */
    @Column(name="date")
    private LocalDate localDate;

    @Column(name="start_time")
    private LocalTime startTime;

    @Column(name="end_time")
    private LocalTime endTime;

    @Column(name="time_spent")
    private LocalTime timeSpent;

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int id) {
        this.dataId = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(LocalTime timeSpent) {
        this.timeSpent = timeSpent;
    }

    @Transient
    private String date;
    @Transient
    private String from;
    @Transient
    private String to;
    @Transient
    private MisdemeanorsDaily misdemeanors;
/*    @Transient
    private String timeSpent;*/
    /*
     * Constructors
     * */
    public Data() {
        misdemeanors = new MisdemeanorsDaily();
    }

/*    public Data(String date, String activity, String since, String to, String timeSpent) {
        this.date = date;
        this.activity = activity;
        this.from = since;
        this.to = to;
        this.timeSpent = timeSpent;
        misdemeanors = new MisdemeanorsDaily();
    }*/

    /*
     * Getters
     * */
    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getDate() {
        return date;
    }

    public String getActivity() {
        return activity;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

/*    public String getTimeSpent() {
        return timeSpent;
    }*/

    public MisdemeanorsDaily getMisdemeanors() {
        return misdemeanors;
    }

    /*
     * Setters
     * */
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

/*    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }*/

    public void setMisdemeanors(MisdemeanorsDaily misdemeanors) {
        this.misdemeanors = misdemeanors;
    }
}
