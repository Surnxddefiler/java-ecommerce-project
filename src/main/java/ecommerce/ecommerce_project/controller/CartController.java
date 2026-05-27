package ecommerce.ecommerce_project.controller;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.cartClass.CartItemRequest;
import ecommerce.ecommerce_project.cartClass.CartResponse;
import ecommerce.ecommerce_project.service.CartService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            @RequestBody @Valid CartItemRequest cart
    ) {
        log.info("adding product with id: {} to user cart with id: {}", cart.productId(), cart.userId());
        return cartService.addToCart(cart);
    }
    @PostMapping()
    public CartResponse getCart(
            @RequestBody @Valid Long userId
    ){
        log.info("outputting cart");
        return cartService.getCart(userId);
    }
}
