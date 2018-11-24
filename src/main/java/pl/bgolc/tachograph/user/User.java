package pl.bgolc.tachograph.user;

import pl.bgolc.tachograph.user.annotation.FieldsMatch;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
@FieldsMatch.List( {@FieldsMatch(field = "password", fieldMatch = "confirmPassword", message = "Hasla muszą myc identyczne")} )
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Pole nazwa użytkwnika jest wymagane")
    @Size(min = 3, max = 15, message = "Nazwa użytkownika musi zawierac od 3 do 15 znakow")
    @Column(name = "username")
    private String userName;

    @NotBlank(message = "Pole email jest wymagane")
    @Email(message = "Nie poprawny adres email")
    @Column(name = "email")
    private String email;


    @NotBlank(message = "Pole haslo jest wymagane")
    @Size(min = 8, message = "Pole haslo musi zawierac minimum 8 znakow")
    @Column(name = "passwd")
    private String password;

    @NotBlank(message = "Pole potwierdz haslo jest wymagane")
    @Size(min = 8, message = "Pole potwierd haslo musi zawierac minimum 8 znakow")
    @Transient
    private String confirmPassword;

    @Column(name = "enabled")
    private boolean enabled;

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

    public boolean getEnabled() {
        return enabled;
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
