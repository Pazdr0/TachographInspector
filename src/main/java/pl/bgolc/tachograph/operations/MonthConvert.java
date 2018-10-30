package pl.bgolc.tachograph.operations;

public enum MonthConvert {

	JANUARY(1, "sty"),
    FEBRUARY(2, "lut"),
    MARCH(3, "mar"),
    APRIL(4, "kwi"),
    MAY(5, "maj"),
    JUNE(6, "cze"),
    JULY(7, "lip"),
    AUGUST(8, "sie"),
    SEPTEMBER(9, "wrz"),
    OCTOBER(10, "paz"),
    NOVEMBER(11, "lis"),
    DECEMBER(12, "gru");
    
    private final String monthName;
    private int monthNumber;
    
    private MonthConvert(int number, String month) {
    	this.monthName = month;
    	this.monthNumber = number;
    }

    public static int convert(String month) {
    	
    	if (month.equals(JANUARY.monthName)) {
    		return JANUARY.monthNumber;
    	} else if (month.equals(FEBRUARY.monthName)) {
            return FEBRUARY.monthNumber;
        } else if (month.equals(MARCH.monthName)) {
        	return MARCH.monthNumber;
        } else if (month.equals(APRIL.monthName)) {
        	return APRIL.monthNumber;
        } else if (month.equals(MAY.monthName)) {
        	return MAY.monthNumber;
        } else if (month.equals(JUNE.monthName)) {
        	return JUNE.monthNumber;
        } else if (month.equals(JULY.monthName)) {
        	return JULY.monthNumber;
        } else if (month.equals(AUGUST.monthName)) {
        	return AUGUST.monthNumber;
        } else if (month.equals(SEPTEMBER.monthName)) {
        	return SEPTEMBER.monthNumber;
        } else if (month.equals(OCTOBER.monthName)) {
        	return OCTOBER.monthNumber;
        } else if (month.equals(NOVEMBER.monthName)) {
        	return NOVEMBER.monthNumber;
        } else if (month.equals(DECEMBER.monthName)) {
        	return DECEMBER.monthNumber;
        } else {
			return 0;
		}
    }
}
