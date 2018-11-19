package pl.bgolc.tachograph.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bgolc.tachograph.data.model.Data;
import pl.bgolc.tachograph.data.repository.DataRepository;
import pl.bgolc.tachograph.data.service.DataService;

import java.time.LocalDate;
import java.util.List;

@Service
class DataServiceImpl implements DataService {

	@Autowired
	private DataRepository dataRepository;
	
	@Override
	public void saveData() {

	}

	@Override
	public List<Data> findByDriverId(int driverId) {
		return dataRepository.findByDriverId(driverId);
	}

	@Override
	public List<Data> findDataSinceTo(String since, String to, int driverId) {
		return dataRepository.findDataSinceTo(LocalDate.parse(since), LocalDate.parse(to), driverId);
	}

	@Override
	public void saveAll(List<Data> dataList) {
		dataRepository.saveAll(dataList);
	}
}
