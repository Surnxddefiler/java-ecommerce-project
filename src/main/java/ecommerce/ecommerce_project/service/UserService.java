package ecommerce.ecommerce_project.service;

import ecommerce.ecommerce_project.db.entities.UserEntity;
import ecommerce.ecommerce_project.db.repositories.UserRepository;
import ecommerce.ecommerce_project.exeptions.EmailException;
import ecommerce.ecommerce_project.exeptions.UserNotFoundException;
import ecommerce.ecommerce_project.exeptions.UsernameException;
import ecommerce.ecommerce_project.exeptions.WrondPasswordException;
import ecommerce.ecommerce_project.mappers.UserMapper;
import ecommerce.ecommerce_project.userClass.UserEditRequest;
import ecommerce.ecommerce_project.userClass.UserLogin;
import ecommerce.ecommerce_project.userClass.UserRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String registerUser(@Valid UserRequest userRequest) {

        UserEntity userEntity=userMapper.toEntity(userRequest);
        //encoding password
        userEntity.setPassword(passwordEncoder.encode(userRequest.password()));
        try {
            userRepository.saveAndFlush(userEntity);
            //race condition fix
        } catch (DataIntegrityViolationException e){
            DataIntegrityHandler(e, userRequest.username(), userRequest.email());
        }

        return "created successfully";
    }

    //editing user info

    @Transactional
    public String editUser(Long userId, UserEditRequest userEditRequest) {
        UserEntity userEntity=userRepository.findByUserIdForUpdate(userId).orElseThrow(UserNotFoundException::new);
        boolean isEmailEdited=false;
        boolean isUsernameEdited=false;
        //changing username
        if (userEditRequest.username()!=null){
            if (!userEditRequest.username().equals(userEntity.getUsername())){
                userEntity.setUsername(userEditRequest.username());
                isUsernameEdited=true;
            }
        }
        //changing username
        if (userEditRequest.email()!=null){
            if (!userEditRequest.email().equals(userEntity.getEmail())){
                userEntity.setEmail(userEditRequest.email());
                isEmailEdited=true;
            }
        }
        //trying to push new data to db
        try {
            userRepository.saveAndFlush(userEntity);
        }catch (DataIntegrityViolationException e){
            DataIntegrityHandler(e, userEditRequest.username(), userEditRequest.email());
        }

        return "successfully edited "+(isEmailEdited ? "email" : "") + (isEmailEdited && isUsernameEdited ? " and" : "")+ (isUsernameEdited ? " username" : "");
    }

    //checking error
    private  void DataIntegrityHandler(DataIntegrityViolationException e, String username, String email){
        Throwable current=e;
        while (current!=null){
            if (current instanceof ConstraintViolationException cve){
                //checking if constraintName is the same as uk_users_email
                if ("uk_users_email".equals(cve.getConstraintName())) {
                    throw new EmailException(email);
                }
                //checking if constraintName is the same as uk_users_username
                if ("uk_users_username".equals(cve.getConstraintName())){
                    throw new UsernameException(username);
                }
            }
            current=current.getCause();
        }
        throw e;

    }

    public String loginUser(@Valid UserLogin userLogin) {
        //USER
        UserEntity userEntity=userRepository.findByEmail(userLogin.email()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(userLogin.password(), userEntity.getPassword())){
           throw new WrondPasswordException();
        }

        return jwtService.generateToken(userEntity.getUserId(), userLogin.email());
    }

}
