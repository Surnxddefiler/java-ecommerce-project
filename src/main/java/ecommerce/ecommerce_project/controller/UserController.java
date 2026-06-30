package ecommerce.ecommerce_project.controller;

import ecommerce.ecommerce_project.service.UserService;
import ecommerce.ecommerce_project.userClass.UserEditRequest;
import ecommerce.ecommerce_project.userDetails.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/me")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PatchMapping()
    public ResponseEntity<String> editProfile(
            @AuthenticationPrincipal CustomUserDetails customUserDetails, //getting user from jwt
            @RequestBody UserEditRequest userEditRequest
            ){
        log.info("trying to edit new user");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.editUser(customUserDetails.getUserId() ,userEditRequest));
    }
}
