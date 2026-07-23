package ecommerce.ecommerce_project.controller.userController;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.ProductClass.ProductFilter;
import ecommerce.ecommerce_project.ProductClass.ProductOrderBy;
import ecommerce.ecommerce_project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Page<Product> getAllProducts(
            @RequestParam(name = "startPrice", required = false) Double startPrice,
            @RequestParam(name = "endPrice", required = false) Double endPrice,
            @RequestParam(name = "currentPage", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "orderBy", required = false) ProductOrderBy productOrderBy
    ) {
        log.info("getting product");
        return productService.getAllProducts(new ProductFilter(startPrice, endPrice, currentPage, pageSize, productOrderBy));
    }
}
