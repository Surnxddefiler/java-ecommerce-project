package ecommerce.ecommerce_project.userDetails;

import ecommerce.ecommerce_project.db.entities.UserEntity;
import ecommerce.ecommerce_project.db.repositories.UserRepository;
import ecommerce.ecommerce_project.exeptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailService implements UserDetailsService { //implementing UserDetailService for getting user details


    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //finction to get user info
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //replacing username string to long userId
        Long userId= Long.valueOf(username);

        //trying to find user
        UserEntity userEntity=userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);



        //returning custom userDetails
        return new CustomUserDetails(userId, userEntity.getUserRole());
    }
}
