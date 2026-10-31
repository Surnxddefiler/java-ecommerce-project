package ecommerce.ecommerce_project.service;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.ProductClass.ProductEditRequest;
import ecommerce.ecommerce_project.ProductClass.ProductFilter;
import ecommerce.ecommerce_project.db.entities.ProductEntity;
import ecommerce.ecommerce_project.db.repositories.ProductRepository;
import ecommerce.ecommerce_project.exeptions.InvalidPageSizeException;
import ecommerce.ecommerce_project.exeptions.ProductNotFoundException;
import ecommerce.ecommerce_project.mappers.ProductMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    //passing repository
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository=productRepository;
        this.productMapper=productMapper;

    }

    //getting all products
    public Page<Product> getAllProducts(ProductFilter productFilter) {
        //creating paging
        int currentPage= productFilter.currentPage()!=null ? productFilter.currentPage() : 0;
        int pageSize= productFilter.pageSize()!=null ? productFilter.pageSize() : 10;
        if (pageSize<=0 || pageSize>50){
            throw new InvalidPageSizeException(pageSize);
        }
        Sort sort= Sort.unsorted();
        if (productFilter.productOrderBy()!=null){
            sort=Sort.by(productFilter.productOrderBy().getField());
        };
        Pageable pageable= PageRequest.of(currentPage, pageSize, sort);
        //getting items from repo
        Page<ProductEntity> productEntities=productRepository.getByFilters(productFilter.startPrice(), productFilter.endPrice(), pageable);
        return productEntities.map(productMapper::toProduct);
    }


    //posting product
    public String postProduct(@Valid Product product) {
        //creating entity from a product
        ProductEntity productEntity=productMapper.toProductEntity(product);
        //saving product
        productRepository.save(productEntity);
        return "product: "+ product.name()+" created";
    };

    //deleting product
    @Transactional
    public String deleteProduct(@Valid Long productId) {
        //deleting product
        productRepository.deleteById(productId);//??? if someone have this product in cart it won't be deleted
        return "deleted Successfully";
    }

    //

    @Transactional
    public String editProduct(Long productId, @Valid ProductEditRequest request) {
        //searching for product
        ProductEntity productEntity=productRepository.findByIdForUpdate(productId).orElseThrow(()-> new ProductNotFoundException(productId));

        if (request.name()!=null){
            productEntity.setName(request.name());
        }
        if (request.price()!=null){
            productEntity.setPrice(request.price());
        }
        if (request.stock()!=null){
            productEntity.setStock(request.stock());
        }

        return "product" + request.name() + " edited successfully";
    }
}
