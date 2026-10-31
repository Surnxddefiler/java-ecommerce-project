package ecommerce.ecommerce_project.controller.adminController;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.ProductClass.ProductEditRequest;
import ecommerce.ecommerce_project.orderClass.OrderStatusChange;
import ecommerce.ecommerce_project.service.OrderService;
import ecommerce.ecommerce_project.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product")
@PreAuthorize("hasRole('ADMIN')")
public class AdminProductController {
    private final ProductService productService;
    private static final Logger log = LoggerFactory.getLogger(AdminProductController.class);

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    //add product
    @PostMapping()
    public ResponseEntity<String> postProduct(
            @RequestBody @Valid Product product
            ){
        log.info("changing admin role status");
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.postProduct(product));
    }
    //delete mapping
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable(name = "id") Long productId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productId));
    }
    //editing
    @PatchMapping("/{id}")
    public ResponseEntity<String> editProduct(
            @PathVariable(name = "id") Long productId,
            @RequestBody @Valid ProductEditRequest request
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.editProduct(productId, request));
    }
}
