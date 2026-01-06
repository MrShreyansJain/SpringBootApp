package com.shreyans.springmodules.config;

import com.shreyans.springmodules.authenticationProvider.JwtAuthenticationProvider;
import com.shreyans.springmodules.filters.JWTAuthenticationFilter;
import com.shreyans.springmodules.filters.JWTValidationFilter;
import com.shreyans.springmodules.util.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;


    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(UserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    JwtAuthenticationProvider jwtAuthenticationProvider(){
        return  new JwtAuthenticationProvider(jwtUtil,userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider(),jwtAuthenticationProvider()));
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
      //single login the every thing works
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(auth->auth
//                .requestMatchers("/auth/saveUserAuth").permitAll()
//                  .anyRequest().authenticated()
//        ).csrf(csrf->csrf.disable())
//        .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

    //saving password in DB
    // single login the every thing works
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(auth->auth
//                .requestMatchers("/auth/saveUserAuth").permitAll()
//                  .anyRequest().authenticated()
//        ).csrf(csrf->csrf.disable())
//        .formLogin(Customizer.withDefaults());
//        return http.build();
//    }


    //saving password in DB
    //single login the every thing works
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //authentication filter for generating token
        JWTAuthenticationFilter jwtAuthenticationFilter= new JWTAuthenticationFilter(authenticationManager(),jwtUtil);


        //authentication filter for validating the token supplied
        JWTValidationFilter jwtValidationFilter= new JWTValidationFilter(authenticationManager());
        http.authorizeHttpRequests(auth->auth
                        .requestMatchers("/auth/saveUserAuth").permitAll()
                        .anyRequest().authenticated()
                ).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                //generate token filter before other filter is added
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtValidationFilter,JWTAuthenticationFilter.class);
               // .formLogin(Customizer.withDefaults());
        return http.build();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/auth/saveUserAuth", // registration
//                                "/login.html",        // login page (static)
//                                "/css/**",
//                                "/js/**",
//                                "/images/**"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login.html")          // URL of our static login page
//                        .loginProcessingUrl("/doLogin")    // form action URL (POST)
//                        .defaultSuccessUrl("/index.html", true) // after successful login
//                        .failureUrl("/login.html?error")   // optional: show error msg
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .deleteCookies("JSESSIONID")
//                        .invalidateHttpSession(true)
//                        .logoutSuccessUrl("/login.html")
//                );
//
//        return http.build();
//    }

}
