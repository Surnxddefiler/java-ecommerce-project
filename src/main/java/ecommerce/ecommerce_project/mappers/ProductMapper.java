package ecommerce.ecommerce_project.mappers;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.db.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductEntity productEntity){
        return new Product(productEntity.getProductId(), productEntity.getName(), productEntity.getPrice(), productEntity.getStock());
    }
}
