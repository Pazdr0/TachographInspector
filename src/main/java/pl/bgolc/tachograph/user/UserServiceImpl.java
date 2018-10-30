package pl.bgolc.tachograph.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/*@Override
	public Boolean login(String login, String passwd) {
		return userRepository.login(login, passwd);
	}*/
	
	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}
	
	@Override
	public boolean register(String username, String email, String passwd) {
		return userRepository.register(username, email, passwd);
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
