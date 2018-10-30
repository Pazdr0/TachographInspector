package pl.bgolc.tachograph.driver;

import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface DriverService {

    @Secured("ROLE_ADMIN")
    List<Driver> findAllDrivers();

    List<Driver> findByUserId(int id);

    boolean save(Driver driver);
}
