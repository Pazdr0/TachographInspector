package pl.bgolc.tachograph.data.temporary;


import pl.bgolc.tachograph.data.constants.Activities;
import pl.bgolc.tachograph.data.constants.TimeRestrictions;
import pl.bgolc.tachograph.operations.DurationManager;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkWeek {

    private List<pl.bgolc.tachograph.data.Data> dataList;
    private List<Day> dayList;
    private LocalDate weekStart;
    private LocalDate weekEnd;
    private MisdemeanorsWeekly misdemeanorsWeekly;
    private int exceededWeeklyExtendedDriveTimes;
    private int exceededWeeklyInsufficientBreakTimes;

    /*
     * Constructor
     * */
	public WorkWeek() {
        dataList = new ArrayList<pl.bgolc.tachograph.data.Data>();
        dayList = new ArrayList<Day>();
        misdemeanorsWeekly = new MisdemeanorsWeekly();
    }

    public void sortIntoDays() {
    	
	    List<pl.bgolc.tachograph.data.Data> temp = new ArrayList<pl.bgolc.tachograph.data.Data>();
	    temp.addAll(dataList);

	    for (int i=0; i<temp.size(); i++) {

	        Day day = new Day();
//	        day.setLocalDate(temp.get(i).getLocalDate());
            day.setIndex(i);

            Duration breakDuration = Duration.ZERO;

	        for (int j=0; j<temp.size(); j++) {
                day.addActivity(temp.get(0).getActivity());
                day.addTimeSpent(temp.get(0).getTimeSpent());
	            if (temp.get(0).getActivity().equals(Activities.BREAK.getActivity())) {
                    if (DurationManager.compareDuration(DurationManager.transformToDuration(temp.get(0).getTimeSpent()), TimeRestrictions.DAILY_BREAK)) {
                        breakDuration = DurationManager.addTime(breakDuration, temp.get(0).getTimeSpent());
                        temp.remove(0);
                        break;
                    } /*else if ((DurationManager.compareDuration(DurationManager.transformToDuration(temp.get(0).getTimeSpent()), TimeRestrictions.DAILY_BREAK_DIVIDED_3)
                                && !DurationManager.compareDuration(DurationManager.transformToDuration(temp.get(0).getTimeSpent()), TimeRestrictions.DAILY_BREAK_DIVIDED_9))
                                || (DurationManager.compareDuration(DurationManager.transformToDuration(temp.get(0).getTimeSpent()), TimeRestrictions.DAILY_BREAK_DIVIDED_9)
                                && !DurationManager.compareDuration(DurationManager.transformToDuration(temp.get(0).getTimeSpent()), TimeRestrictions.DAILY_BREAK))) {
                        breakDuration = DurationManager.addTime(breakDuration, temp.get(0).getTimeSpent());
                    }*/ else if (DurationManager.compareDuration(DurationManager.transformToDuration(temp.get(0).getTimeSpent()), TimeRestrictions.DAILY_BREAK_SHORTENED)
                    		&& !DurationManager.compareDuration(DurationManager.transformToDuration(temp.get(0).getTimeSpent()), TimeRestrictions.DAILY_BREAK)) {
                    	breakDuration = DurationManager.addTime(breakDuration, temp.get(0).getTimeSpent());
                    	exceededWeeklyInsufficientBreakTimes++;
                    	temp.remove(0);
                    	break;
                    }
                }
                temp.remove(0);

/*
	            if (day.getLocalDate().equals(temp.get(0).getLocalDate())) {
                    day.addActivity(temp.get(0).getActivity());
                    day.addTimeSpent(temp.get(0).getTimeSpent());
                    temp.remove(0);
                }
*/
            }
            System.out.println(day.getActivityList());
	        System.out.println(day.getTimeSpentList() + "\n");
            dayList.add(day);
        }
	    System.out.println("Koniec tygodnia............................");
  /*      for (int i=0; i<dayList.size(); i++) {
            for (int j=0; j<dayList.get(i).getActivityList().size(); j++) {
                if (dayList.get(i).getActivityList().get(j).equals(Activities.BREAK.getActivity())) {
                    if (DurationManager.compareDuration(DurationManager.transformToDuration(dayList.get(i).getTimeSpentList().get(j)), TimeRestrictions.DAILY_BREAK_DIVIDED_9)
                            && !DurationManager.compareDuration(DurationManager.transformToDuration(dayList.get(i).getTimeSpentList().get(j)), TimeRestrictions.DAILY_BREAK)) {
                        Day dayOne = new Day();
                        Day dayTwo = new Day();

                        dayOne.setIndex(dayList.get(i).getIndex());
                        dayOne.setActivityList(dayList.get(i).getActivityList().subList(0, j));
                        dayOne.setTimeSpentList(dayList.get(i).getTimeSpentList().subList(0, j));

                        dayTwo.setIndex(dayList.get(i).getIndex() + 1);
                        dayTwo.setActivityList(dayList.get(i).getActivityList().subList(j + 1, dayList.get(i).getActivityList().size()));
                        dayTwo.setTimeSpentList(dayList.get(i).getTimeSpentList().subList(j + 1, dayList.get(i).getActivityList().size()));

                        dayList.remove(i);

                        dayList.add(i, dayOne);
                        dayList.add(i + 1, dayTwo);
                    }
                }
            }
        }*/
    }

    /*
     * Getters
     * */
    public List<pl.bgolc.tachograph.data.Data> getDataList() {
        return dataList;
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public LocalDate getWeekEnd() {
        return weekEnd;
    }

    public MisdemeanorsWeekly getMisdemeanorsWeekly() {
        return misdemeanorsWeekly;
    }

    public int getExceededWeeklyExtendedDriveTimes() {
        return exceededWeeklyExtendedDriveTimes;
    }

    public int getExceededWeeklyInsufficientBreakTimes() {
        return exceededWeeklyInsufficientBreakTimes;
    }

    /*
     * Setters
     * */
    public void setDataList(List<pl.bgolc.tachograph.data.Data> dataList) {
        this.dataList = dataList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }

    public void setWeekStart(LocalDate start) {
        this.weekStart = start;
    }

    public void setWeekEnd(LocalDate end) {
        this.weekEnd = end;
    }

    public void setMisdemeanorsWeekly(MisdemeanorsWeekly misdemeanorsWeekly) {
        this.misdemeanorsWeekly = misdemeanorsWeekly;
    }

	public void setExceededWeeklyExtendedDriveTimes(int exceededWeeklyExtendedDriveTimes) {
		this.exceededWeeklyExtendedDriveTimes = exceededWeeklyExtendedDriveTimes;
	}

	public void setExceededWeeklyInsufficientBreakTimes(int exceededWeeklyInsufficientBreakTimes) {
        this.exceededWeeklyInsufficientBreakTimes = exceededWeeklyInsufficientBreakTimes;
    }
}
