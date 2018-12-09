package pl.bgolc.tachograph.data.service;

import pl.bgolc.tachograph.data.model.Data;

import java.util.List;

public interface DataService {

	List<Data> findByDriverId(int driverId);

	List<Data> findDataSinceTo(String since, String to, int driverId);

	void saveAll(List<Data> dataList);

}
