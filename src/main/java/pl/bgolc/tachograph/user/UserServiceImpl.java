package pl.bgolc.tachograph.user;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bgolc.tachograph.user.verification.VerificationToken;
import pl.bgolc.tachograph.user.verification.VerificationTokenRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository userRepository;
	private VerificationTokenRepository tokenRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, VerificationTokenRepository tokenRepository) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
	}

	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}
	
	@Override
	public boolean register(String username, String email, String passwd) {
		return userRepository.register(username, email, passwd);
	}

	/*
	* Find user methods
	* */
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public void enableRegisteredUser(User user) {
		userRepository.enableRegisteredUser(user.getId());
	}

	@Override
	public VerificationToken getVerificationToken(String verificationToken) {
		return tokenRepository.findByToken(verificationToken);
	}

	@Override
	public void createVerificationToken(User user, String token, LocalDateTime expiryDate) {
		VerificationToken myToken = new VerificationToken(token, user.getId(), expiryDate);
		tokenRepository.save(myToken);
		log.debug("Token has been saved");
	}

	@Override
	public int getUser(String verificationToken) {
		return tokenRepository.findByToken(verificationToken).getUserId();
	}

	@Override
	public boolean checkIfEmailExists(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return false;
		}
		log.debug("Email already exists");
		return true;
	}

	@Override
	public boolean checkIfUserNameExists(String userName) {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			return false;
		}
		log.debug("Username already exists");
		return true;
	}
}
