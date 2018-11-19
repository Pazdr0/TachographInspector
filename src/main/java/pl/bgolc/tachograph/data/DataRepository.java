package pl.bgolc.tachograph.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer>{

/*    @Query(value="SELECT \"tacho\".", nativeQuery=true)
    boolean saveData(@Param("_username") String username);

    @Query(value="SELECT \"tacho\".", nativeQuery=true)
    boolean retrieveData(@Param("_username") String username);*/

    List<Data> findByDriverId(int driverId);

    @Query(value="SELECT \"tacho\".", nativeQuery=true)
    List<Data> findDataSinceTo(@Param("since") String since, @Param("to") String to);
}
