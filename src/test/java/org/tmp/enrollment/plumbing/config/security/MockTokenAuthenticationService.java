package org.tmp.enrollment.plumbing.config.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Component
@Profile({"integration", "test"})
public class MockTokenAuthenticationService extends TokenAuthenticationService {

    public MockTokenAuthenticationService() {
        super("NULL");
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        return new UserAuthentication(new User("integration-test-user", Collections.singletonList("ROLE_USER")));
    }

}
