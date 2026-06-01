package ecommerce.ecommerce_project.service;

import ecommerce.ecommerce_project.db.entities.UserEntity;
import ecommerce.ecommerce_project.db.repositories.UserRepository;
import ecommerce.ecommerce_project.exeptions.UserNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService { //implementing UserDetailService for getting user details


    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //finction to get user info
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //trying to find user
        UserEntity userEntity=userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);



        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                Collections.emptyList()
        );
    }
}
