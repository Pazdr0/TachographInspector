package pl.bgolc.tachograph.user;

import java.util.List;

public interface UserService {

	/*Checking credentials*//*
	Boolean login(String login, String passwd);
*/
	/*Service for getting all username's id db*/
	List<User> getUsers();

	/*Registering new user*/
	boolean register(String username, String email, String passwd);

	User findByUserName(String userName);
}
