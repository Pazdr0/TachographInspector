package pl.bgolc.tachograph.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/*Query checking if credentials are correct*/
	@Query(value = "SELECT \"tacho\".login(:_login, :_passwd)", nativeQuery = true)
	boolean login(@Param("_login") String login, @Param("_passwd") String passwd);

	/*Lists all users*/
	@Query(value = "SELECT * FROM \"tacho\".users", nativeQuery = true)
	List<User> getUsers();

	/*Query to register new user*/
	@Query(value="SELECT \"tacho\".register(:_username, :_email, :_passwd)", nativeQuery = true)
	boolean register(@Param("_username") String username, @Param("_email") String email, @Param("_passwd") String passwd);

	@Query(value = "SELECT \"tacho\".enable_user(:_user_id) ", nativeQuery = true)
	boolean enableRegisteredUser(@Param("_user_id") int user_id);

	User findByUserName(String userName);

	User findById(int id);

	User findByEmail(String email);
}
