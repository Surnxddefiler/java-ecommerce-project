package ecommerce.ecommerce_project.controller;

import ecommerce.ecommerce_project.service.UserService;
import ecommerce.ecommerce_project.userClass.UserBalance;
import ecommerce.ecommerce_project.userClass.UserEditRequest;
import ecommerce.ecommerce_project.userClass.UserPasswordRequest;
import ecommerce.ecommerce_project.userDetails.CustomUserDetails;
import jakarta.validation.Valid;
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
            @Valid @RequestBody UserEditRequest userEditRequest
            ){
        log.info("trying to edit user");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.editUser(customUserDetails.getUserId() ,userEditRequest));
    }

    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @AuthenticationPrincipal CustomUserDetails customUserDetails, //getting user jwt
            @Valid @RequestBody UserPasswordRequest userPasswordRequest
            ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.changePassword(customUserDetails.getUserId(), userPasswordRequest));
    }
    @PostMapping("/add-balance")
    public ResponseEntity<String> addBalance(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody UserBalance userBalance
            ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.addBalance(customUserDetails.getUserId(), userBalance));
    }
}
