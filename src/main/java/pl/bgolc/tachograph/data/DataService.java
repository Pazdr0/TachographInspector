package pl.bgolc.tachograph.data;

import java.util.List;

public interface DataService {

	void saveData();

	List<Data> findByDriverId(int driverId);
}
