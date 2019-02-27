package pl.bgolc.tachograph.user.verification;

import pl.bgolc.tachograph.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;

    @Column(name = "token")
    private String token;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "expiry_date")
    private LocalDateTime localExpiryDate;

    public LocalDateTime calculateExpiryDate() {
        return LocalDateTime.now().plusDays(7);
    }

    public VerificationToken() {
    }

    public VerificationToken(String token, int userId, LocalDateTime expiryDate) {
        this.token = token;
        this.userId = userId;
        this.localExpiryDate = expiryDate;
    }
    /*
    * Getters
    * */
    public int getId() {
        return tokenId;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getLocalExpiryDate() {
        return localExpiryDate;
    }

    /*
    * Setters
    * */
    public void setId(int id) {
        this.tokenId = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public void setLocalExpiryDate(LocalDateTime localExpiryDate) {
        this.localExpiryDate = localExpiryDate;
    }
}
