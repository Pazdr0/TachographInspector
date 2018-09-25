package pl.bgolc.tachograph.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.bgolc.tachograph.service.AuthenticationFacadeService;

@Component
public class AuthenticationFacadeServiceImpl implements AuthenticationFacadeService {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
