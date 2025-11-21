package com.shreyans.springmodules.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetails(){
        UserDetails user1= User.withUsername("user_name")
                .password("my_password1")
                .roles("Admin")
                .build();
        UserDetails user2= User.withUsername("user_name_qa")
                .password("my_password2")
                .roles("QA")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }
}
