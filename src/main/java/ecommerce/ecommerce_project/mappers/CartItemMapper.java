package ecommerce.ecommerce_project.mappers;

import ecommerce.ecommerce_project.cartClass.CartItemRequest;
import ecommerce.ecommerce_project.db.CartItemEntity;
import ecommerce.ecommerce_project.db.ProductEntity;
import ecommerce.ecommerce_project.db.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
     public CartItemEntity toEntity(CartItemRequest cartItem, ProductEntity product, UserEntity userEntity){
         //total price will be in
        return new CartItemEntity(null, cartItem.quantity(), product, userEntity);
    }
}
