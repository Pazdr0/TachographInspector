package pl.bgolc.tachograph.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacadeService  {

    Authentication getAuthentication();
}
