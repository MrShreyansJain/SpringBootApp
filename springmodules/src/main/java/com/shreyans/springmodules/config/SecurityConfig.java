package com.shreyans.springmodules.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }


    //in memory password encoding
    /*@Bean
    public UserDetailsService userDetails(){
        UserDetails user1= User.withUsername("user_name")
                .password(new BCryptPasswordEncoder().encode("my_password1"))
                .roles("Admin")
                .build();
        UserDetails user2= User.withUsername("user_name_qa")
                .password(new BCryptPasswordEncoder().encode("my_password2"))
                .roles("QA")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }*/

    //saving password in DB
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth->auth
                .requestMatchers("/auth/saveUserAuth").permitAll()
                .anyRequest().authenticated()
        ).csrf(csrf->csrf.disable()).httpBasic(Customizer.withDefaults());
        return http.build();
    }



}
