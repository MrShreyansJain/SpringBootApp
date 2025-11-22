package com.shreyans.springmodules.service;

import com.shreyans.springmodules.entity.UserAuthEntity;
import com.shreyans.springmodules.repository.UserAuthRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {
    private UserAuthRepo userAuthRepo;

    public UserAuthService (UserAuthRepo userAuthRepo){
        this.userAuthRepo=userAuthRepo;
    }

    public UserDetails save(UserAuthEntity userAuth){
        return userAuthRepo.save(userAuth);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
    }
}
