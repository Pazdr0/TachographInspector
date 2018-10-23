package pl.bgolc.tachograph.datamanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.bgolc.tachograph.datamanager.db.DataRepository;
import pl.bgolc.tachograph.datamanager.service.DataService;

@Service
class DataServiceImpl implements DataService {

	@Autowired
	private DataRepository dataRepository;
	
	@Override
	public void saveData() {

	}
}
