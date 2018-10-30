package pl.bgolc.tachograph.data.constants;

/* 
 * All actions than can be taken by a driver,
 * Only polish language version
 * */
public enum Activities {
	
    DRIVE_TIME("jazda"),
    WORK("praca"),
    BREAK("przerwa/odpoczynek"),
    BREAK_SHORT("krótka przerwa"),
    UNKNOWN("nieznany");
    
    private final String activity;
    
    private Activities(String activity) {
    	this.activity = activity;
    }
    
    public String getActivity() {
    	return activity;
    }
}

