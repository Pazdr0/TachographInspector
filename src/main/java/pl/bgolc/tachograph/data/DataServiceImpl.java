package pl.bgolc.tachograph.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bgolc.tachograph.data.DataRepository;
import pl.bgolc.tachograph.data.DataService;

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
}
