package pl.bgolc.tachograph.data;

import java.util.List;

public interface DataService {

	void saveData();

	List<Data> findByDriverId(int driverId);

	List<Data> findDataSinceTo(String since, String to);

	void saveAll(List<Data> dataList);


}
