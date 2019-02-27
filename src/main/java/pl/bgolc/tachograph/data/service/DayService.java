package pl.bgolc.tachograph.data.service;

import pl.bgolc.tachograph.data.model.Day;

import java.util.List;

public interface DayService {

    public List<Day> getDayList(int id);

    public List<Day> getDayList(String since, String to, int id);
}
