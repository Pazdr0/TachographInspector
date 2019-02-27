package pl.bgolc.tachograph.user.verification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bgolc.tachograph.user.User;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);

//    VerificationToken findByUser(User user);
}
