package ecommerce.ecommerce_project.service;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.cartClass.CartItem;
import ecommerce.ecommerce_project.cartClass.CartItemRequest;
import ecommerce.ecommerce_project.cartClass.CartResponse;
import ecommerce.ecommerce_project.db.*;
import ecommerce.ecommerce_project.exeptions.ProductNotFoundException;
import ecommerce.ecommerce_project.exeptions.UserNotFoundException;
import ecommerce.ecommerce_project.mappers.CartItemMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {


    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    public CartService(ProductRepository productRepository, UserRepository userRepository, CartItemMapper cartItemMapper, CartItemRepository cartItemRepository){
        this.productRepository=productRepository;
        this.userRepository=userRepository;
        this.cartItemMapper=cartItemMapper;
        this.cartItemRepository=cartItemRepository;
    }

    //adding to cart
    @Transactional
    public String addToCart(@Valid CartItemRequest cart) throws InterruptedException {
        Thread.sleep(5000);
        //checking if the product exists
        ProductEntity productEntity=productRepository.findByProductId(cart.productId()).orElseThrow(()-> new ProductNotFoundException(cart.productId()));
        //searching for a user
        UserEntity userEntity=userRepository.findByUserId(cart.userId()).orElseThrow(UserNotFoundException::new);


//        checking if product is already in cart, if it is just adding new quantity
        if (cartItemRepository.existsByProductEntityProductIdAndUserEntityUserId (productEntity.getProductId(), userEntity.getUserId())){
            //setting new quantity
            cartItemRepository.addQuantity(productEntity.getProductId(), userEntity.getUserId(), cart.quantity());

        }else { //if there is not this type of product just adding it
            CartItemEntity cartItemEntity=cartItemMapper.toEntity(cart, productEntity, userEntity);
            //setting new total price
            cartItemRepository.save(cartItemEntity);
        }
        return "added successfully";
    }

    //getting total price for cart
    public CartResponse getCart(Long userId) {
        //checking if user exists
        if (!userRepository.existsByUserId(userId)){
            throw new UserNotFoundException();
        }
        //getting total cart price
        double cartTotalPrice= cartItemRepository.getCartTotalPrice(userId);
        //getting all products
        List<CartItem> cartItems=cartItemRepository.getAllItems(userId);

        return new CartResponse(cartTotalPrice, cartItems);

    }
}
