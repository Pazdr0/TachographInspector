package pl.bgolc.tachograph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.bgolc.tachograph.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer>{

}
