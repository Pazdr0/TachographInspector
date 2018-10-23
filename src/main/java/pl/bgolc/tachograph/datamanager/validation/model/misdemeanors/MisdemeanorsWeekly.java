package pl.bgolc.tachograph.datamanager.validation.model.misdemeanors;

/*
 * Broken weekly restrictions
 * */
public class MisdemeanorsWeekly {

    private boolean exceededMaxWeeklyDriveTime;                 //checked
    private boolean exceededMaxWeeklyDriveTimeTwoWeeks;         //condition to be implemented in future
    private boolean insufficientWeaklyBreak;                    //condition to be implemented in future
    private boolean exceededWeeklyExtendedDriveTimes;           //checked
    private boolean exceededWeeklyInsufficientDayBreakTimes;    //

    /*
    * Checkers
    * */
    public boolean isExceededMaxWeeklyDriveTime() {
        return exceededMaxWeeklyDriveTime;
    }

    public boolean isExceededMaxWeeklyDriveTimeTwoWeeks() {
        return exceededMaxWeeklyDriveTimeTwoWeeks;
    }

    public boolean isExceededWeeklyExtendedDriveTimes() {
        return exceededWeeklyExtendedDriveTimes;
    }

    public boolean isInsufficientWeaklyBreak() {
        return insufficientWeaklyBreak;
    }

    public boolean isExceededWeeklyInsufficientBreakTimes() {
        return exceededWeeklyInsufficientDayBreakTimes;
    }

    /*
    * Setters
    * */
    public void setExceededMaxWeeklyDriveTime(boolean exceededMaxWeeklyDriveTime) {
        this.exceededMaxWeeklyDriveTime = exceededMaxWeeklyDriveTime;
    }

    public void setExceededMaxWeeklyDriveTimeTwoWeeks(boolean exceededMaxWeeklyDriveTimeTwoWeeks) {
        this.exceededMaxWeeklyDriveTimeTwoWeeks = exceededMaxWeeklyDriveTimeTwoWeeks;
    }

    public void setExceededWeeklyExtendedDriveTimes(boolean exceededWeeklyExtendedDriveTimes) {
        this.exceededWeeklyExtendedDriveTimes = exceededWeeklyExtendedDriveTimes;
    }

    public void setInsufficientWeaklyBreak(boolean insufficientWeaklyBreak) {
        this.insufficientWeaklyBreak = insufficientWeaklyBreak;
    }

    public void setExceededWeeklyInsufficientBreakTimes(boolean exceededWeeklyInsufficientDayBreakTimes) {
        this.exceededWeeklyInsufficientDayBreakTimes = exceededWeeklyInsufficientDayBreakTimes;
    }
}
