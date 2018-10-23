package pl.bgolc.tachograph.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacadeService  {

    Authentication getAuthentication();
}
