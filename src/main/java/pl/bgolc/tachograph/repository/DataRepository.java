package pl.bgolc.tachograph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.bgolc.tachograph.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer>{

    @Query(value="SELECT \"tacho\".", nativeQuery=true)
    public boolean saveData(@Param("_username") String username);

    @Query(value="SELECT \"tacho\".", nativeQuery=true)
    public boolean retrieveData(@Param("_username") String username);

}
