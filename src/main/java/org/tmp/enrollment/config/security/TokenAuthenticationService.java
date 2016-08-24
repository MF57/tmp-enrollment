package org.tmp.enrollment.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private String secret;


    public TokenAuthenticationService(String secret) {
        this.secret = secret;
    }

    public Authentication getAuthentication(HttpServletRequest request){
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if(token != null) {
            final User user = parseUserFromToken(token);
            if(user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

    private User parseUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Base64.encode(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody();

        String username = claims.get("username", String.class);
        List<String> privileges = (List<String>) claims.get("privileges");

        User user = new User(
                username,
                privileges);
        return user;
    }
}
