package ecommerce.ecommerce_project.service;

import ecommerce.ecommerce_project.db.entities.*;
import ecommerce.ecommerce_project.db.repositories.*;
import ecommerce.ecommerce_project.exeptions.*;
import ecommerce.ecommerce_project.mappers.OrderMapper;
import ecommerce.ecommerce_project.orderClass.Order;
import ecommerce.ecommerce_project.orderClass.OrderStatus;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    public OrderService(UserRepository userRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, OrderMapper orderMapper, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order postOrder(@Valid Long userId) {
        //search user
        UserEntity userEntity=userRepository.findByIdForUpdate(userId).orElseThrow(UserNotFoundException::new);
        //getting total balance
        Double totalPrice=cartItemRepository.getCartTotalPrice(userId).orElseThrow(EmptyCartException::new);
        //checking balance
        if(userEntity.getBalance()<totalPrice){
            throw new BalanceException();
        }
        else {
            userEntity.setBalance(userEntity.getBalance()-totalPrice);
            userRepository.save(userEntity);
        }
        //creating Order Entity
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderStatus(OrderStatus.CREATED);
        //refer it to user
        orderEntity.setUserEntity(userEntity);
        //grabbing all item entities
        List<CartItemEntity> cartItemEntities=cartItemRepository.findByUserIdForUpdate(userId);
        //creating new array for order Items
        List<OrderItemEntity> orderItemEntities=new ArrayList<>();
        cartItemEntities.forEach(cartItem -> {
            orderItemEntities.add(new OrderItemEntity(null, orderEntity, cartItem.getProductEntity(), cartItem.getQuantity(),cartItem.getProductEntity().getPrice()));
            //checking quantity
            //making it locking
            ProductEntity productEntity=productRepository.findByIdForUpdate(cartItem.getProductEntity().getProductId()).orElseThrow();
            if (productEntity.getStock()<cartItem.getQuantity()){
                throw new QuantityException();
            }
            else {
                productRepository.decrementStock(productEntity.getProductId(), cartItem.getQuantity());
            }
        });
        orderRepository.save(orderEntity);
        orderEntity.setOrderItemEntities(orderItemEntities);
        orderItemRepository.saveAll(orderItemEntities);
        //deleting everything from a cart
        cartItemRepository.deleteAll(cartItemEntities);
        return orderMapper.toOrder(orderEntity);
        //create orderItem response
    };


}
