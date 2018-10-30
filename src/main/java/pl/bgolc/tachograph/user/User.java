package pl.bgolc.tachograph.user;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@NotBlank
	@Size(min=5, max=30)
	@Column(name="username")
	private String userName;

	@NotBlank
    @Email
	@Column(name="email")	
	private String email;

	@NotBlank
	@Size(min=8, max=30)
	@Column(name="passwd")
	private String password;

	@NotBlank
	@Size(min=8, max=30)
	@Transient
	private String confirmPassword;

	/*
	* Constructors
	* */
	public User() {
	}

	public User(String login, String password) {
		super();
		this.userName = login;
		this.email = login;
		this.password = password;
	}
	
	public User(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	/*
	* Getters
	* */
	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	/*
	* Setters
	* */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
