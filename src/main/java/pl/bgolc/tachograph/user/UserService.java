package pl.bgolc.tachograph.user;

import pl.bgolc.tachograph.user.verification.VerificationToken;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

	/*
	* Service for getting all user's id db
	* */
	List<User> getUsers();

	/*
	* Registering new user
	* */
	boolean register(String username, String email, String passwd);

	User findByUserName(String userName);

	User findById(int id);

	User findByEmail(String email);

	/*
	* Mail activation
	* */
	int getUser(String verificationToken);

	void enableRegisteredUser(User user);

	void createVerificationToken(User user, String token, LocalDateTime expiryDate);

	VerificationToken getVerificationToken(String verificationToken);

	boolean checkIfEmailExists(String email);

	boolean checkIfUserNameExists(String userName);
}
