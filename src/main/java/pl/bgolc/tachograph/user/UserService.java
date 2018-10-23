package pl.bgolc.tachograph.user;

import java.util.List;

public interface UserService {

	/*Checking credentials*/
	public Boolean login(String login, String passwd);

	/*Service for getting all username's id db*/
	public List<User> getUsers();

	/*Registering new user*/
	public boolean register(String username, String email, String passwd);
}
