package com.shreyans.springmodules.authenticationProvider;

import com.shreyans.springmodules.token.JwtAuthenticationToken;
import com.shreyans.springmodules.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtAuthenticationProvider implements AuthenticationProvider {
    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationProvider(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //extract token from authentication object
        String token = ((JwtAuthenticationToken)authentication).getToken();
        //extract username after validating the token
        String username=jwtUtil.validateAndExtractUsername(token);
        if(username == null){
            throw new BadCredentialsException("Invalid or expired JWT token");
        }

        //get user details object based on username
        UserDetails userDetails= userDetailsService.loadUserByUsername(username);
        //pass an authentication object based on the user details object found
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
