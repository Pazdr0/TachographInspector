package pl.bgolc.tachograph.data.model;

import java.util.Objects;

/*Klasa reprezentująca dane z tachografu*/
public class Data {

    private String date;
    private String activity;
    private String from;
    private String to;
    private String timeSpent;

    public Data() {

    }

    public Data(String date, String activity, String since, String to, String timeSpent) {
        this.date = date;
        this.activity = activity;
        this.from = since;
        this.to = to;
        this.timeSpent = timeSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data1 = (Data) o;
        return Objects.equals(date, data1.date) &&
                Objects.equals(activity, data1.activity) &&
                Objects.equals(from, data1.from) &&
                Objects.equals(to, data1.to) &&
                Objects.equals(timeSpent, data1.timeSpent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, activity, from, to, timeSpent);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }
}
