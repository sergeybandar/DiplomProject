package by.tms.strore.service;

import by.tms.strore.entity.Category;

import by.tms.strore.entity.Order;
import by.tms.strore.entity.User;
import by.tms.strore.exception.OrderAlreadyExistsException;
import by.tms.strore.exception.OrderNotFoundException;

import by.tms.strore.repository.OrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(Order order) {
            orderRepository.save(order);
    }
    public List<Order> getAllOrdersByUser(User user) {
        List<Order> orders = orderRepository.findOrdersByUser(user);
        if (orders != null) {
            return orders;
        } else {
            throw new OrderNotFoundException("Orders by " +user.getUserName()+"not found");
        }
    }
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders != null) {
            return orders;
        } else {
            throw new OrderNotFoundException("Orders not found");
        }
    }

    public void updateOrder(Order order) {
        if (orderRepository.existsOrderById(order.getId())) {
             orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }
    public Order findOrderById(Long id) {
        if (orderRepository.existsOrderById(id)) {
            return orderRepository.findOrderById(id);
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }
}
