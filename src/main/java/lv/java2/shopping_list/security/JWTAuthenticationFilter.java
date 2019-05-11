package lv.java2.shopping_list.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static lv.java2.shopping_list.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            UserDTO credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), UserDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(),
                            credentials.getPassword(), new ArrayList<>()));
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
