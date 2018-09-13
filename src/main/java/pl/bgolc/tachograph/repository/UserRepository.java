package pl.bgolc.tachograph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.bgolc.tachograph.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
