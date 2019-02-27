package pl.bgolc.tachograph.data.constants;

/*
 * Restrictions for professional drivers 
 * Weekly 
 * */
public enum QuantityRestricions {

	MAX_DAILY_BREAK_SHORTENED(3),			//2 x a week
	MAX_EXTENDED_DRIVES_WEEKLY(2);			//3 x a week
	
	private final int restriction;
	
	private QuantityRestricions(int limit) {
		this.restriction = limit;
	}
	
	public int getRestriction() {
		return restriction;
	}
}