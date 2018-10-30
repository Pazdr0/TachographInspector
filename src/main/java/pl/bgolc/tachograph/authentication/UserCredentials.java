package pl.bgolc.tachograph.authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.bgolc.tachograph.user.User;
import pl.bgolc.tachograph.user.UserService;

@Component
public class UserCredentials {

    private UserService userService;
    private AuthenticationFacadeService authenticationFacadeService;

    @Autowired
    public UserCredentials(UserService userService, AuthenticationFacadeService authenticationFacadeService) {
        this.userService = userService;
        this.authenticationFacadeService = authenticationFacadeService;
    }

    public String getName() {
        return authenticationFacadeService.getAuthentication().getName();
    }

    public int getUserId() {
        return userService.findByUserName(getName()).getId();
    }
}
