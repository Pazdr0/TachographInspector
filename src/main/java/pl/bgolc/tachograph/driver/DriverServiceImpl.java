package pl.bgolc.tachograph.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private Logger log = LoggerFactory.getLogger(DriverServiceImpl.class);
    /*
    * Components
    * */
    private DriverRepository driverRepository;

    /*
    * Constructor
    * */
    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    /*
    * Methods
    * */
    @Override
    @Secured("ROLE_ADMIN")
    public List<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public List<Driver> findByUserId(int id) {
        return driverRepository.findByUserId(id);
    }

    @Override
    public void save(Driver driver) {
        log.debug("Saving driver to db");
        driverRepository.save(driver);
    }
}
