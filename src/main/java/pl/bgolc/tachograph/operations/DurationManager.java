package pl.bgolc.tachograph.operations;

import java.time.Duration;

import pl.bgolc.tachograph.data.constants.TimeRestrictions;

/*
 * Final class managing duration variables
 * Constructor is private so that an object of this class can't be created
 * Class is only being used for calculations
 * */
public final class DurationManager {
	
    private DurationManager() {}

    /*
     * Addition of duration variable and duration saved as string
     * Works only if format of string is 'H:m'.
     * Method should throw an custom exception (to be added)
     * TODO create exception like 'WrongFormatException' to be thrown by this method
     * */
    public static Duration addTime(Duration timeSum, String timeToAdd) {
        String[] timeComponents = timeToAdd.split(":");
//        timeSum = timeSum.plus(Duration.ofHours(Integer.parseInt(timeComponents[0])).plusMinutes(Integer.parseInt(timeComponents[1])));
        return timeSum.plus(Duration.ofHours(Integer.parseInt(timeComponents[0])).plusMinutes(Integer.parseInt(timeComponents[1])));
    }

    public static Duration transformToDuration(String timeToTransform) {
        String[] timeComponents = timeToTransform.split(":");
        Duration duration = Duration.ZERO;

        duration = duration.plus(Duration.ofHours(Integer.parseInt(timeComponents[0]))).plusMinutes(Integer.parseInt(timeComponents[1]));

        return duration;
    }

    /*
     * Method to compare duration with restriction
     * */
/*    public static boolean compareDuration(Duration duration, int restriction) {
        if (duration.toMinutes() >= restriction) {
            return true;
        } else {
            return false;
        }
    }*/
    
    /*
     * Method to compare duration with restriction (using Enums) updated
     * */
    public static boolean compareDuration(Duration duration, TimeRestrictions restriction) {
    	if (duration.toMinutes() >= restriction.getRestriction()) {
            return true;
        } else {
            return false;
        }
    }

}
