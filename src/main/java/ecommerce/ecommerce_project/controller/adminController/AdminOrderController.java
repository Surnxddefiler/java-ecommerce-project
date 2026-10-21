package ecommerce.ecommerce_project.controller.adminController;

import ecommerce.ecommerce_project.orderClass.OrderStatusChange;
import ecommerce.ecommerce_project.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user/{userId}/order/{orderId}")
@PreAuthorize("hasRole('ADMIN')")
public class AdminOrderController {
    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(AdminOrderController.class);

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public String changeOrderStatus(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "orderId") Long orderId,
            @RequestBody @Valid OrderStatusChange orderStatusChange
            ){
        log.info("changing admin role status");
        return orderService.changeOrderStatus(userId,orderId, orderStatusChange);
    }
}
