package pl.bgolc.tachograph.data.service;

import pl.bgolc.tachograph.data.model.Day;

import java.util.List;

public interface DataInspector {

    List<Day> checkDays(List<Day> dayList);
}
