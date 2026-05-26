package ecommerce.ecommerce_project.service;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.ProductClass.ProductFilter;
import ecommerce.ecommerce_project.db.entities.ProductEntity;
import ecommerce.ecommerce_project.db.repositories.ProductRepository;
import ecommerce.ecommerce_project.exeptions.InvalidPageSizeException;
import ecommerce.ecommerce_project.mappers.ProductMapper;
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

}
