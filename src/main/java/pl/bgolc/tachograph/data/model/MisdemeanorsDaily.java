package pl.bgolc.tachograph.data.model;

/*
 * Broken daily restrictions
 * */
public class MisdemeanorsDaily {

    private boolean exceededOneTimeDrive;
    private boolean exceededDailyDriveTime;
    private boolean insufficientDailyBreak;
    private boolean insufficientShortBreak45;
    private boolean insufficientShortBreak30;
    private boolean insufficientShortBreak15;


    public MisdemeanorsDaily() {
    }

    public boolean isExceededOneTimeDrive() {
        return exceededOneTimeDrive;
    }

    public void setExceededOneTimeDrive(boolean exceededOneTimeDrive) {
        this.exceededOneTimeDrive = exceededOneTimeDrive;
    }

    public boolean isExceededDailyDriveTime() {
        return exceededDailyDriveTime;
    }

    public void setExceededDailyDriveTime(boolean exceededDailyDriveTime) {
        this.exceededDailyDriveTime = exceededDailyDriveTime;
    }

    public boolean isInsufficientDailyBreak() {
        return insufficientDailyBreak;
    }

    public void setInsufficientDailyBreak(boolean insufficientDailyBreak) {
        this.insufficientDailyBreak = insufficientDailyBreak;
    }

    public boolean isInsufficientShortBreak45() {
        return insufficientShortBreak45;
    }

    public void setInsufficientShortBreak45(boolean insufficientShortBreak45) {
        this.insufficientShortBreak45 = insufficientShortBreak45;
    }

    public boolean isInsufficientShortBreak30() {
        return insufficientShortBreak30;
    }

    public void setInsufficientShortBreak30(boolean insufficientShortBreak30) {
        this.insufficientShortBreak30 = insufficientShortBreak30;
    }

    public boolean isInsufficientShortBreak15() {
        return insufficientShortBreak15;
    }

    public void setInsufficientShortBreak15(boolean insufficientShortBreak15) {
        this.insufficientShortBreak15 = insufficientShortBreak15;
    }
}
