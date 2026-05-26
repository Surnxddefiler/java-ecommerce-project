package ecommerce.ecommerce_project.helpers;

import ecommerce.ecommerce_project.db.repositories.UserRepository;
import ecommerce.ecommerce_project.exeptions.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHelper {
    private final UserRepository userRepository;
    public ExceptionHelper(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    //checking if user exists
    public void userCheck(Long userId) {
        if (!userRepository.existsByUserId(userId)) {
            throw new UserNotFoundException();
        }
    }
}
