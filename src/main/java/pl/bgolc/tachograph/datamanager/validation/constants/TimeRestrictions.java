package pl.bgolc.tachograph.datamanager.validation.constants;

/*
 * Restrictions for professional drivers 
 * in minutes
 * */
public enum TimeRestrictions {

	MAX_ONE_TIME_DRIVE(270),						//4.5h
	MAX_DAILY_DRIVE_TIME(540),						//9h
	MAX_DAILY_DRIVE_TIME_EXTENDED(600),				//10h
	MAX_WEEKLY_DRIVE_TIME_ONE_WEEK(3360), 			//56h
    MAX_WEEKLY_DRIVE_TIME_TWO_WEEKS(5400), 			//90h
    SHORT_BREAK_15(15),
    SHORT_BREAK_30(30),
    SHORT_BREAK_45(45),
    DAILY_BREAK(660), 								//11h
	DAILY_BREAK_SHORTENED(660), 					//9h
    DAILY_BREAK_DIVIDED_3(180), 					//3h
    DAILY_BREAK_DIVIDED_9(540), 					//9h
    WEEKLY_BREAK(2700),					 			//45h
    WEEKLY_BREAK_SHORTENED(1440); 					//24h
    
	private final int restriction;
	
	private TimeRestrictions(int limit) {
		this.restriction = limit;
	}
	
	public int getRestriction() {
		return restriction;
	}
}
