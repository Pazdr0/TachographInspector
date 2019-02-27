package pl.bgolc.tachograph.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.bgolc.tachograph.data.constants.Activities;
import pl.bgolc.tachograph.data.constants.TimeRestrictions;
import pl.bgolc.tachograph.data.model.Data;
import pl.bgolc.tachograph.data.model.Day;
import pl.bgolc.tachograph.data.model.WorkWeek;
import pl.bgolc.tachograph.operations.DurationManager;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Class validating drivers working time
 * */
@Service
public class DataInspectorImpl implements DataInspector {



/*    private static final String TIME_FORMATTER = "H:m";		//Time formatter for DateTimeFormatter
    private static final int TWO_WEEKS = 14;
    private static final int ONE_WEEK = 7;
    private List<Data> dataInput;						    //Input data from constructor, set as unmodifiable and final so the data doesn't get mixed up
    private List<WorkWeek> workWeeksList;		*/			//Input data sorted into weeks of work are saved here

    private Logger log = LoggerFactory.getLogger(DataInspectorImpl.class);

    private List<Day> dayList;

    /*
     * Constructor
     * */
    public DataInspectorImpl() {
        /*workWeeksList = new ArrayList<WorkWeek>();*/
    }
    
    /*
    * Methods
    * */
    @Override
    public List<Day> checkDays(List<Day> dayList) {

        for (Day day : dayList) {
            for (Data data : day.getDataList()) {
                if (data.getActivity().equals(Activities.DRIVE_TIME.getActivity())) {
                    if (DurationManager.compareDuration(DurationManager.transformLocalTimeToDuration(data.getTimeSpent()), TimeRestrictions.MAX_ONE_TIME_DRIVE)) {
                        day.getMisdemeanorsDaily().setExceededOneTimeDrive(true);
                        log.info("Max one time drive has been exceeded");
                    }
                }
            }
        }

        for (Day day : dayList) {
            Duration dailyDriveTimeDuration = Duration.ZERO;
            for (Data data : day.getDataList()) {
                if (data.getActivity().equals(Activities.DRIVE_TIME.getActivity()) || data.getActivity().equals(Activities.WORK.getActivity())) {
                    dailyDriveTimeDuration = DurationManager.addLocalTime(dailyDriveTimeDuration, data.getTimeSpent());
                }
            }
            if (DurationManager.compareDuration(dailyDriveTimeDuration, TimeRestrictions.MAX_DAILY_DRIVE_TIME_EXTENDED)) {
                day.getMisdemeanorsDaily().setExceededDailyDriveTime(true);
                log.info("Daily drive time has been exceeded");
            }
        }

        for (Day day : dayList) {
            Duration dailyBreakTimeDuration = Duration.ZERO;
            for (Data data : day.getDataList()) {
                if (data.getActivity().equals(Activities.BREAK.getActivity())) {
                    dailyBreakTimeDuration = DurationManager.addLocalTime(dailyBreakTimeDuration, data.getTimeSpent());
                }
            }
            if (!DurationManager.compareDuration(dailyBreakTimeDuration, TimeRestrictions.DAILY_BREAK_SHORTENED)) {
                day.getMisdemeanorsDaily().setInsufficientDailyBreak(true);
                log.info("Daily break time has been insufficient");
            }
        }
        return dayList;
    }







    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }




/*    *//*
     * Setting data list
     * *//*
    public void setData(List<Data> data) {
    	this.dataInput = Collections.unmodifiableList(data);
    }
    
    *//*
     * Main method (and only one public) for checking data
     * It calls other methods in right order
     * *//*
    public void checkData() {
//    	sortWeeks();
//    	checkWeek();
        checkDays();
    }

    private void checkDays() {
        List<Data> dataOutPut = dataInput;


    }*/

    /*
     * Method checking weekly restrictions
     * */
   /* private void checkWeek() {
        for (int i = 0; i < workWeeksList.size(); i++*//*WorkWeek week : workWeeksList*//*) {

            Duration weeklyDriveTimeDuration = Duration.ZERO;

            *//* Adding working time spent in a week *//*
            for (pl.bgolc.tachograph.data.model.Data data : workWeeksList.get(i).getDataList()) {
                if (data.getActivity().equals(Activities.DRIVE_TIME.getActivity()) || data.getActivity().equals(Activities.WORK.getActivity())) {
                    weeklyDriveTimeDuration = DurationManager.addTime(weeklyDriveTimeDuration, data.getTimeSpent());
                }
            }
            *//* Checking if working time for a week is greater than restriction, if so Misdemeanor is added *//*
            if (DurationManager.compareDuration(weeklyDriveTimeDuration, TimeRestrictions.MAX_WEEKLY_DRIVE_TIME_ONE_WEEK)) {
                workWeeksList.get(i).getMisdemeanorsWeekly().setExceededMaxWeeklyDriveTime(true);
            }
            *//* TODO delete when no longer needed
             * Checking if above condition is working properly *//*
 *//*           if (workWeeksList.get(i).getMisdemeanorsWeekly().isExceededMaxWeeklyDriveTime()) {
                System.out.println("przekroczono");
            }*//*

            for (Day day : workWeeksList.get(i).getDayList()) {

                *//* Work time *//*
                Duration extendedDailyDriveTimeDuration = Duration.ZERO;
                *//* Break *//*
                Duration dailyBreakDurationDuration = Duration.ZERO;

                *//* Adds drive time for one day*//*
                for (int j=0; j<day.getActivityList().size(); j++) {
                    if (day.getActivityList().get(j).equals(Activities.DRIVE_TIME.getActivity()) || day.getActivityList().get(j).equals(Activities.WORK.getActivity())) {
                        extendedDailyDriveTimeDuration = DurationManager.addTime(extendedDailyDriveTimeDuration, day.getTimeSpentList().get(j));
                    }

                    //TODO zaimplementowac tutaj jeszcze sprawdanie ilosci skroconych przerw w tygodniu
*//*                    if (day.getActivityList().get(j).equals(Activities.BREAK.getActivity())) {
                        if (DurationManager.compareDuration(DurationManager.transformToDuration(day.getTimeSpentList().get(j)), TimeRestrictions.DAILY_BREAK)) {
                            dailyBreakDurationDuration = DurationManager.addTime(dailyBreakDurationDuration, day.getTimeSpentList().get(j));
                        } else if ((DurationManager.compareDuration(DurationManager.transformToDuration(day.getTimeSpentList().get(j)), TimeRestrictions.DAILY_BREAK_DIVIDED_3)
                                && !DurationManager.compareDuration(DurationManager.transformToDuration(day.getTimeSpentList().get(j)), TimeRestrictions.DAILY_BREAK_DIVIDED_9))
                                || (DurationManager.compareDuration(DurationManager.transformToDuration(day.getTimeSpentList().get(j)), TimeRestrictions.DAILY_BREAK_DIVIDED_9)
                                && !DurationManager.compareDuration(DurationManager.transformToDuration(day.getTimeSpentList().get(j)), TimeRestrictions.DAILY_BREAK))) {
                            dailyBreakDurationDuration = DurationManager.addTime(dailyBreakDurationDuration, day.getTimeSpentList().get(j));
                        }
                    }*//*
                }
                *//* If working time duration for one day is greater than 9h and smaller than 10h exceededWeeklyExtendedDriveTime is incremented *//*
                if (DurationManager.compareDuration(extendedDailyDriveTimeDuration, TimeRestrictions.MAX_DAILY_DRIVE_TIME)
                        && !DurationManager.compareDuration(extendedDailyDriveTimeDuration, TimeRestrictions.MAX_DAILY_DRIVE_TIME_EXTENDED)) {
                    workWeeksList.get(i).setExceededWeeklyExtendedDriveTimes(workWeeksList.get(i).getExceededWeeklyExtendedDriveTimes() + 1);
                }

*//*                if (DurationManager.compareDuration(dailyBreakDurationDuration, TimeRestrictions.DAILY_BREAK_SHORTENED)
                        && !DurationManager.compareDuration(dailyBreakDurationDuration, TimeRestrictions.DAILY_BREAK)) {
                    System.out.println(dailyBreakDurationDuration);
                }*//*
            }

            *//* If number of weekly number of exceededDriveTimes is greater than restriction Misdemeanor is added *//*
            if (workWeeksList.get(i).getExceededWeeklyExtendedDriveTimes() > QuantityRestricions.MAX_EXTENDED_DRIVES_WEEKLY.getRestriction()) {
                workWeeksList.get(i).getMisdemeanorsWeekly().setExceededWeeklyExtendedDriveTimes(true);
            }
            //TODO sprawdzic, czy ustawianie tego ograniczenia dziala poprawnie
        }
    }*/
    
    //TODO zaimplementowac sprawdzanie dniowych ograniczen czasowych
    //TODO prawdopodobnie trzba jescze stwortrzyc enum MisdemandorsDaily i dodac je do klasy Day, zeby to tez sprawdzalo
    //TODO no i jeszcze zaimplementowac sprawdzanie limitow na najprostsze ograniczenia

    //TODO zaimplementowac, ale chyba na sam koniec ogranieczenia dla 2 tygodni, bedzie troche przy tym roboty, reszta powinna byc prosta
    
    
    
    
    

    /*
     * Dividing data into separate work weeks
     * */
  /*  private void sortWeeks() {
        Duration breakDuration;                             //Duration of weekly break time
        WorkWeek tempWorkWeek;      						//Temp WeekOfWork to be added into weekOfWorkList
        List<pl.bgolc.tachograph.data.model.Data> dataOutput;								//Data to be put in tempWeekOfWork
        List<pl.bgolc.tachograph.data.model.Data> tempData = new ArrayList<pl.bgolc.tachograph.data.model.Data>();        //Temp data list to work with input data

*//*        for (Data temp : dataInput) {						//Filling tempData with inputData
            tempData.add(temp);
        }*//*
        //TODO pozniej zmienic ta petle u gory na ktoras z ponizszych opcji
//        tempData = dataInput;
        // Replaced above loop
        tempData.addAll(dataInput);

        while (!tempData.isEmpty()){						//To sort data into weeks, until tempData isn's empty method removes elements from tempData and ads them to dataOutput
        	
            tempWorkWeek = new WorkWeek();
            dataOutput = new ArrayList<pl.bgolc.tachograph.data.model.Data>();
            int tempDataSize = tempData.size();
 
            for (int i=0; i<tempDataSize; i++) {
 
                breakDuration = Duration.ZERO;
            	dataOutput.add(tempData.get(0));
            	
            	*//* First it check if a driver is having break in 3 following days (if he is, the duration is being added to breakDuration),
            	 * because it's the maximum number of days one weekly break can take.
            	 * If driver takes a 5 day brake it doesn't matter, because it's going to add those breaks to next week, which will contain only break (empty like)
            	 * If the condition returns false it begins to check if data from 2 following days is time of break
            	 * As follows it check check if activity is break and adds it's time to breakDuration
            	 * If the next days are also breaks duration is summed up and Objects are removed from list
            	 * *//*
                if (tempData.size() >= 3 && tempData.get(2).getActivity().equals(Activities.BREAK.getActivity()) && tempData.get(1).getActivity().equals(Activities.BREAK.getActivity())
                		&& tempData.get(0).getActivity().equals(Activities.BREAK.getActivity())) {
                    breakDuration = DurationManager.addTime(breakDuration, tempData.get(0).getTimeSpent());
                    breakDuration = DurationManager.addTime(breakDuration, tempData.get(1).getTimeSpent());
                    breakDuration = DurationManager.addTime(breakDuration, tempData.get(2).getTimeSpent());  
                    dataOutput.add(tempData.get(1));
                    dataOutput.add(tempData.get(2));
                    tempData.remove(2);
                    tempData.remove(1);
                } else if (tempData.size() >= 2 && tempData.get(1).getActivity().equals(Activities.BREAK.getActivity())
                		&& tempData.get(0).getActivity().equals(Activities.BREAK.getActivity())) {
                    breakDuration = DurationManager.addTime(breakDuration, tempData.get(0).getTimeSpent());
                    breakDuration = DurationManager.addTime(breakDuration, tempData.get(1).getTimeSpent());
                    dataOutput.add(tempData.get(1));
                    tempData.remove(1);
                } else if (tempData.get(0).getActivity().equals(Activities.BREAK.getActivity())) {
                    breakDuration = DurationManager.addTime(breakDuration, tempData.get(0).getTimeSpent());
                }

                tempData.remove(0);
                
                *//* All restrictions are set in Restrictions class.
                 * Conditions are checked, if breakDuration is greater than restriction, 
                 * loop ends and tempWorkWeek is being added to main week list
                 * or if there is no more data about week is being added.
                 * *//*
                if (DurationManager.compareDuration(breakDuration, TimeRestrictions.WEEKLY_BREAK)) {
                    tempWorkWeek.setDataList(dataOutput);
                    tempWorkWeek.setWeekStart(tempWorkWeek.getDataList().get(0).getLocalDate());
                    tempWorkWeek.setWeekEnd(tempWorkWeek.getDataList().get(tempWorkWeek.getDataList().size() - 1).getLocalDate());
                    break;
                } else if (DurationManager.compareDuration(breakDuration, TimeRestrictions.WEEKLY_BREAK_SHORTENED)) {
                    tempWorkWeek.setDataList(dataOutput);
//                	tempWorkWeek.setExceededWeeklyInsufficientBreakTimes(tempWorkWeek.getExceededWeeklyInsufficientBreakTimes() + 1);
                    tempWorkWeek.setWeekStart(tempWorkWeek.getDataList().get(0).getLocalDate());
                    tempWorkWeek.setWeekEnd(tempWorkWeek.getDataList().get(tempWorkWeek.getDataList().size() - 1).getLocalDate());
                	break;
                } else if (tempData.size() == 0) {
                	tempWorkWeek.setDataList(dataOutput);
                    tempWorkWeek.setWeekStart(tempWorkWeek.getDataList().get(0).getLocalDate());
                    tempWorkWeek.setWeekEnd(tempWorkWeek.getDataList().get(tempWorkWeek.getDataList().size() - 1).getLocalDate());
                	break;
                }

            }
            *//* Sorting data into days, could be done in WorkWeek class, I guess... *//*
            tempWorkWeek.sortIntoDays();
            workWeeksList.add(tempWorkWeek);
        }
    }*/


    /*
     * temp method
     * TODO to be deleted
     * */
/*    public void displayDayList() {
        WorkWeek week = workWeeksList.get(0);

        for (int i=0; i<week.getDayList().size(); i++) {
            System.out.println("_____________Dzien tygodnia_____________" + week.getDayList().get(i).getIndex());
            for (int j=0; j<week.getDayList().get(i).getActivityList().size(); j++) {
                System.out.print(j + " " + week.getDayList().get(i).getActivityList().get(j));
                System.out.print("\t");
                System.out.println(week.getDayList().get(i).getTimeSpentList().get(j));
            }
        }
    }*/

/*    *//*
     * TODO delete if it becomes useless
     * *//*
    public void displayWeeksOfWork() {
//        int i = 1;
        int j = 1;
        *//*for (WorkWeek temp : workWeeksList) {
            System.out.print(i++ + " tydzien pracy");
            for (Data tempData : temp.getDataList()) {
                System.out.println(j++ + " " + tempData.getTimeSpent());
            }
        }*//*
        
        System.out.println(workWeeksList.size() + ", list 1 size: " + workWeeksList.get(0).getDataList().size() + ", list 2 size: " + workWeeksList.get(1).getDataList().size() 
        		+ ", list 3 size: " + workWeeksList.get(2).getDataList().size() + ", list 4 size: " + workWeeksList.get(3).getDataList().size());
    	for (j=0; j<workWeeksList.get(0).getDataList().size(); j++) {
    		System.out.print("\n" + (j + 1) + " \t");
    		if (workWeeksList.get(0).getDataList().size() > j) {
    			System.out.print(workWeeksList.get(0).getDataList().get(j).getTimeSpent());
    		}
    		System.out.print(" \t");
    		if (workWeeksList.get(1).getDataList().size() > j) {
    			System.out.print(workWeeksList.get(1).getDataList().get(j).getTimeSpent());
    		}
    		System.out.print(" \t");
    		if (workWeeksList.get(2).getDataList().size() > j) {
    			System.out.print(workWeeksList.get(2).getDataList().get(j).getTimeSpent()); 

    		}
    		System.out.print(" \t");
    		if (workWeeksList.get(3).getDataList().size() > j) {
    	 		System.out.print(workWeeksList.get(3).getDataList().get(j).getTimeSpent());
    		}
        }
    }*/

/*    *//*
     * TODO to be rewritten
     * *//*
    private boolean checkOneTimeDrive(String timeSpent) {
        LocalTime time = LocalTime.parse(timeSpent, DateTimeFormatter.ofPattern(TIME_FORMATTER));
      *//*  LocalTime maxOneTimeDrive = LocalTime.parse(Restrictions.MAX_ONE_TIME_DRIVE, DateTimeFormatter.ofPattern(TIME_FORMATTER));

        if (time.isBefore(maxOneTimeDrive) || time.equals(maxOneTimeDrive)) {
//            System.out.println("Przepisowo, czas jazdy: " + time.toString());
            return false;
        } else {
//            System.out.println("Wykroczenie");
*//*            return true;
       
    }*/

    /*
     * TODO to be rewritten
     * */
  /*  private void checkTwoNextWeeks() {
        List<pl.bgolc.tachograph.data.model.Data> twoWeeksData = new ArrayList<pl.bgolc.tachograph.data.model.Data>();
        Duration duration;

        for (pl.bgolc.tachograph.data.model.Data temp : dataInput) {
            twoWeeksData.add(temp);
        }

        int j = 0;
        while (!twoWeeksData.isEmpty()) {
            duration = Duration.ZERO;
            List<pl.bgolc.tachograph.data.model.Data> tempData = new ArrayList<pl.bgolc.tachograph.data.model.Data>();
            for (pl.bgolc.tachograph.data.model.Data temp : twoWeeksData) {
                if (temp.getLocalDate().isBefore(twoWeeksData.get(0).getLocalDate().plusDays(ONE_WEEK)) || temp.getLocalDate().equals(twoWeeksData.get(0).getLocalDate().plusDays(ONE_WEEK))) {
                    tempData.add(temp);
                }
            }

            for (int i = 0; i < tempData.size(); i++) {
                if (!twoWeeksData.isEmpty()) {
                    twoWeeksData.remove(0);
                } else {
                    break;
                }
            }

            for (pl.bgolc.tachograph.data.model.Data temp : tempData) {
                if (temp.getActivity().equals(Activities.DRIVE_TIME) || temp.getActivity().equals(Activities.WORK)) {
                    //                timeSum = Adder.addTime(timeSum, temp.getTimeSpent());
                    duration = DurationManager.addTime(duration, temp.getTimeSpent());
                    //                System.out.println(timeSum.toString());
                    //                System.out.println(duration);
                }
            }
            System.out.println(duration);
            System.out.println(tempData.size());

 *//*           if (Adder.compareDuration(duration, 2520)) {
                for (; j < dataOutput.size()-(dataOutput.size()-tempData.size()); j++) {
                    dataOutput.get(j).getMisdemeanors().setExceededMaxWeeklyDriveTimeTwoWeeks(true);
                }
            } else {
                for (; j < dataOutput.size(); j++) {
                    dataOutput.get(j).getMisdemeanors().setExceededMaxWeeklyDriveTimeTwoWeeks(false);
                }
            }*//*
        }
    }*/

}
