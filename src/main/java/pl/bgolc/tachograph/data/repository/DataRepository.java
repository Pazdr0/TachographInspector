package pl.bgolc.tachograph.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.bgolc.tachograph.data.model.Data;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer>{

/*    @Query(value="SELECT \"tacho\".", nativeQuery=true)
    boolean saveData(@Param("_username") String username);

    @Query(value="SELECT \"tacho\".", nativeQuery=true)
    boolean retrieveData(@Param("_username") String username);*/

    List<Data> findByDriverId(int driverId);

    @Query(value="select * from \"tacho\".search_data(:_since, :_to, :_driver_id)", nativeQuery=true)
    List<Data> findDataSinceTo(@Param("_since") LocalDate since, @Param("_to") LocalDate to, @Param("_driver_id") Integer driverId);
}
