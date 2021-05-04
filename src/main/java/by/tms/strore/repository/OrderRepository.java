package by.tms.strore.repository;

import by.tms.strore.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(long id);
    List<Order> findOrdersByUser(User user);
    boolean existsOrderById(long id);
}
