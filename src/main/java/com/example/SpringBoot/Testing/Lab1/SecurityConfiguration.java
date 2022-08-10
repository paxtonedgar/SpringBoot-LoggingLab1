package com.example.SpringBoot.Testing.Lab1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    Logger logger = LoggerFactory.getLogger(BitCoinService.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.trace("Entering SecurityConfiguration.filterChain");

        http.authorizeRequests()
                .antMatchers()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();

        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();

        logger.trace("Leaving SecurityConfiguration.filterChain");

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        logger.trace("Entering SecurityConfiguration.userDetailsService");

        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();

        String username = "paxton";
        String password = "1234";
        UserDetails thisUser = User.withUsername(username)
                .password(passwordEncoder().encode(password))
                .authorities("admin")
                .build();

        userDetailService.createUser(thisUser);
        logger.trace("Leaving SecurityConfiguration.userDetailsService");

        return userDetailService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        logger.trace("Entering SecurityConfiguration.passwordEncoder");
        logger.trace("Leaving SecurityConfiguration.passwordEncoder");

        return new BCryptPasswordEncoder();
    }

}
