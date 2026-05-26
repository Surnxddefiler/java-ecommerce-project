package ecommerce.ecommerce_project.controller;

import ecommerce.ecommerce_project.orderClass.Order;
import ecommerce.ecommerce_project.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestBody @Valid Long userId
    ){
        log.info("Creating Order");
        return orderService.postOrder(userId);
    }
}
