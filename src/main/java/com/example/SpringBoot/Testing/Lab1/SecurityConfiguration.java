package com.example.SpringBoot.Testing.Lab1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated().and().formLogin().and().logout();
        return http.build();}

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();

        String username = "thomas";
        String password = "1234";
        UserDetails thisUser = User.withUsername(username)
                .password(passwordEncoder().encode(password))
                .authorities("read")
                .build();

        userDetailService.createUser(thisUser);

        return userDetailService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
