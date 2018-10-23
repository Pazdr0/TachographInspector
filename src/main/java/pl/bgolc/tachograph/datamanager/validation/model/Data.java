package pl.bgolc.tachograph.datamanager.validation.model;

import pl.bgolc.tachograph.datamanager.validation.model.misdemeanors.MisdemeanorsDaily;

import java.time.LocalDate;

/*
 * Class representing data 
 * from tachograph
 * */
public class Data {

    /*String date converted to LocalDate object*/
    private LocalDate localDate;
    private String date;
    private String activity;
    private String from;
    private String to;
    private String timeSpent;
    private MisdemeanorsDaily misdemeanors;

    /*
     * Constructors
     * */
    public Data() {
        misdemeanors = new MisdemeanorsDaily();
    }

    public Data(String date, String activity, String since, String to, String timeSpent) {
        this.date = date;
        this.activity = activity;
        this.from = since;
        this.to = to;
        this.timeSpent = timeSpent;
        misdemeanors = new MisdemeanorsDaily();
    }

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

    public String getTimeSpent() {
        return timeSpent;
    }

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

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public void setMisdemeanors(MisdemeanorsDaily misdemeanors) {
        this.misdemeanors = misdemeanors;
    }
}
