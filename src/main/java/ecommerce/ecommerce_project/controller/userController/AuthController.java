package ecommerce.ecommerce_project.controller.userController;

import ecommerce.ecommerce_project.service.UserService;
import ecommerce.ecommerce_project.userClass.UserLogin;
import ecommerce.ecommerce_project.userClass.UserRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //register user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody @Valid UserRequest userRequest
    ){
        log.info("creating new user");
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.registerUser(userRequest));
    }
    //login user
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid UserLogin userLogin){
        log.info("logging user");
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.loginUser(userLogin));
    };
}
