package pl.bgolc.tachograph.data;

import java.util.Objects;

public class Data {

    private String data;
    private String activity;
    private String from;
    private String to;
    private String timeSpent;

    public Data() {

    }

    public Data(String data, String activity, String since, String to, String timeSpent) {
        this.data = data;
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
        return Objects.equals(data, data1.data) &&
                Objects.equals(activity, data1.activity) &&
                Objects.equals(from, data1.from) &&
                Objects.equals(to, data1.to) &&
                Objects.equals(timeSpent, data1.timeSpent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, activity, from, to, timeSpent);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
