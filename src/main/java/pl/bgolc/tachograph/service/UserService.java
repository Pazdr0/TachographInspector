package pl.bgolc.tachograph.service;

import java.util.List;

import pl.bgolc.tachograph.model.User;

public interface UserService {

	public Boolean login(String login, String passwd);
	
	public List<User> getUsers();
	
	public boolean register(String username, String email, String passwd);
}
