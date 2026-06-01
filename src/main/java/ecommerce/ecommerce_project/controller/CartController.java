package ecommerce.ecommerce_project.controller;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.cartClass.CartItemRequest;
import ecommerce.ecommerce_project.cartClass.CartResponse;
import ecommerce.ecommerce_project.service.CartService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
            @AuthenticationPrincipal UserDetails userDetails, //getting email from Jwt token
            @RequestBody @Valid CartItemRequest cart
    ) {
        log.info("adding product with id: {} to user cart with email: {}", cart.productId(), userDetails.getUsername());
        return cartService.addToCart(cart, userDetails.getUsername());
    }
    @GetMapping()
    public CartResponse getCart(
            @AuthenticationPrincipal UserDetails userDetails //getting email from Jwt token
    ){
        log.info("outputting cart");
        return cartService.getCart(userDetails.getUsername());
    }
}
