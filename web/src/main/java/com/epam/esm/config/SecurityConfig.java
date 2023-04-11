package com.epam.esm.config;

import com.epam.esm.service.impl.jwt.JwtConfigurer;
import com.epam.esm.service.impl.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String USER_REGISTER_ENDPOINT = "/users/register";
    private static final String GIFT_CERTIFICATE_READ_ENDPOINT = "/certificates/read/**";
    private static final String TAGS_READ_ENDPOINT = "/tags/read/**";
    private static final String USERS_READ_ENDPOINT = "/users/read/**";
    private static final String ORDER_ENDPOINT = "/orders/**";
    private static final String ADMIN_ENDPOINT = "/admin/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
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
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(USER_REGISTER_ENDPOINT).permitAll()
                .antMatchers(GIFT_CERTIFICATE_READ_ENDPOINT).permitAll()
                .antMatchers(TAGS_READ_ENDPOINT).hasAnyRole(USER, ADMIN)
                .antMatchers(ORDER_ENDPOINT).hasAnyRole(USER, ADMIN)
                .antMatchers(USERS_READ_ENDPOINT).hasAnyRole(USER, ADMIN)
                .antMatchers(ADMIN_ENDPOINT).hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
