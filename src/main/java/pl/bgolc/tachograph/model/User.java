package pl.bgolc.tachograph.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	@Size(min=5, max=30)
    @Email
	@Column(name="email")	
	private String email;
	@NotBlank
	@Size(min=6, max=30)
	@Column(name="passwd")
	private String password;

	public User() {
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(userName, user.userName) &&
				Objects.equals(email, user.email) &&
				Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userName, email, password);
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

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
