package ecommerce.ecommerce_project.controller.userController;

import ecommerce.ecommerce_project.cartClass.CartDeleteRequest;
import ecommerce.ecommerce_project.cartClass.CartItemRequest;
import ecommerce.ecommerce_project.cartClass.CartResponse;
import ecommerce.ecommerce_project.service.CartService;
import ecommerce.ecommerce_project.userDetails.CustomUserDetails;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService=cartService;
    }

    //adding product to cart
    @PostMapping("/add-to-cart")
    public String addToCart(
            @AuthenticationPrincipal CustomUserDetails customUserDetails, //getting email from Jwt token
            @RequestBody @Valid CartItemRequest cart
    ) {
        log.info("adding product with id: {} to user cart with email: {}", cart.productId(), customUserDetails.getUsername());
        return cartService.addToCart(cart, customUserDetails.getUserId());
    }
    @GetMapping()
    public CartResponse getCart(
            @AuthenticationPrincipal CustomUserDetails customUserDetails //getting email from Jwt token
    ){
        log.info("outputting cart");
        return cartService.getCart(customUserDetails.getUserId());
    }
    //removing from cart
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteFromCart(
            @PathVariable(name = "id") Long cartItemId,// cart item id
            @AuthenticationPrincipal CustomUserDetails customUserDetails, //getting email from jwt token
            @Valid @RequestBody CartDeleteRequest cartDeleteRequest //quantity to delete
            ){
        log.info("deleting from cart");
        return ResponseEntity.status(HttpStatus.OK).body(cartService.deleteFromCart(customUserDetails.getUserId(), cartItemId, cartDeleteRequest));
    }
}
