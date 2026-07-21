package ecommerce.ecommerce_project.controller;

import ecommerce.ecommerce_project.orderClass.Order;
import ecommerce.ecommerce_project.service.OrderService;
import ecommerce.ecommerce_project.userDetails.CustomUserDetails;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping()
    public Order postOrder(
            @AuthenticationPrincipal CustomUserDetails customUserDetails //getting email from Jwt token
    ){
        log.info("Creating Order");
        return orderService.postOrder(customUserDetails.getUserId());
    }
    //user order history
    @GetMapping("/history")
    public Page<Order> orderHistory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(name = "currentPage", required = false) Integer currentPage
    ){
        log.info("user order history");
        return orderService.getOrderHistory(customUserDetails.getUserId(), currentPage);
    }
}
