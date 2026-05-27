package ecommerce.ecommerce_project.service;

import ecommerce.ecommerce_project.db.entities.UserEntity;
import ecommerce.ecommerce_project.db.repositories.UserRepository;
import ecommerce.ecommerce_project.exeptions.EmailException;
import ecommerce.ecommerce_project.mappers.UserMapper;
import ecommerce.ecommerce_project.userClass.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(@Valid UserRequest userRequest) {
        //user check
        if (userRepository.existsByEmail(userRequest.email())){
            throw new EmailException(userRequest.email());
        }
        UserEntity userEntity=userMapper.toEntity(userRequest);
        //encoding password
        userEntity.setPassword(passwordEncoder.encode(userRequest.password()));
        userRepository.save(userEntity);
        return "created successfully";
    }
}
