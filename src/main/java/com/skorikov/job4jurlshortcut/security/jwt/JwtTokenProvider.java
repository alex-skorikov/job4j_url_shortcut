package com.skorikov.job4jurlshortcut.security.jwt;

import com.skorikov.job4jurlshortcut.security.JwtUserDetailsService;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * Token provider.
 */
@Component
public class JwtTokenProvider {
    /**
     * Secret key.
     */
    @Value("${jwt.token.secret}")
    private String secret;
    /**
     * Timevalidate token.
     */
    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;
    /**
     * User detail service.
     */
    @Autowired
    private JwtUserDetailsService userDetailsService;

    /**
     * Password encoder.
     * @return password encoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * Init post constructor.
     */
    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    /**
     * Create token.
     * @param username String user name.
     * @return token.
     */
    public String createToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secret)//
                .compact();
    }

    /**
     * Authentication by token.
     * @param token token.
     * @return Authentication.
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Get user name.
     * @param token token.
     * @return user name.
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Resolve by token.
     * @param req http request.
     * @return resolve dy token.
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    /**
     * Validate token.
     * @param token token.
     * @return valid or no.
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            //TODO add logger.
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }
}
