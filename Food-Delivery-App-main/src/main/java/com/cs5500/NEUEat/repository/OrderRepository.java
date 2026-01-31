package com.cs5500.NEUEat.repository;

import com.cs5500.NEUEat.common.enums.OrderStatus;
import com.cs5500.NEUEat.model.Order;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface OrderRepository extends MongoRepository<Order, String> {
Page<Order> findByCustomerIdAndStatusNot(
        String customerId,
        OrderStatus status,
        Pageable pageable
);

Page<Order> findByCustomerIdAndStatus(
        String customerId,
        OrderStatus status,
        Pageable pageable
);

}
