package ecommerce.ecommerce_project.mappers;

import ecommerce.ecommerce_project.cartClass.CartItemRequest;
import ecommerce.ecommerce_project.db.entities.CartItemEntity;
import ecommerce.ecommerce_project.db.entities.ProductEntity;
import ecommerce.ecommerce_project.db.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
     public CartItemEntity toEntity(CartItemRequest cartItem, ProductEntity product, UserEntity userEntity){
         //total price will be in
        return new CartItemEntity(null, cartItem.quantity(), product, userEntity);
    }
}
