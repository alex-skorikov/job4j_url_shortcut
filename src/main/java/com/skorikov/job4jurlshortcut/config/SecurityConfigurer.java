package com.skorikov.job4jurlshortcut.config;

import com.skorikov.job4jurlshortcut.security.jwt.JwtConfigurer;
import com.skorikov.job4jurlshortcut.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Security configurer.
 */
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    /**
     * Jwt token provaider.
     */
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Security cinfigerer by Jwt.
     * @param jwtTokenProvider jwt tokenprovaider.
     */
    @Autowired
    public SecurityConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .httpBasic().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("/registration", "/login", "/redirect/").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
