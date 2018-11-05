package pl.bgolc.tachograph.data.temporary;

import java.io.Serializable;
import java.sql.Timestamp;

class Data implements Serializable {
	
	private static final long serialVersionUID = 2L;
	

	private Integer id;

	private String data;
	
	private Timestamp date;
}
