package com.cs5500.NEUEat.service;

import com.cs5500.NEUEat.dto.response.OrderResponse;
import com.cs5500.NEUEat.model.Comment;
import com.cs5500.NEUEat.model.Dish;
import com.cs5500.NEUEat.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

  int addOrderToCart(String customerId, String restaurantId, List<Dish> content);

  // Order can only be canceled when in cart or not in delivery
  int checkoutOrder(String id);

  // checkout all orders in the shopping cart
  int checkoutAll(List<Order> orders);

  int cancelOrder(String id);

  Optional<Order> getOrder(String id);

  // Driver accept the order, the order can only be accepted if it already started and not in delivery
  int acceptOrder(String id, String driverId);

  // Driver finish the order, the order can only be finished if it is in delivery and not finished
  int finishOrder(String id);

  // Customer check orders in the cart
  List<Order> customerCart(String customerId);

  // Customer check all active orders, no matter there is or isn't a driver and the order isn't finished
  List<Order> customerGetActiveOrders(String customerId);

  // Customer check order history
  List<Order> customerFindPastOrders(String customerId);

  // Driver check all orders that waiting for a driver
  List<Order> getAllPendingOrders();

  // Driver get current order
  Order driverGetActiveOrder(String driverId);

  // Driver check order history
  List<Order> driverFindPastOrders(String driverId);

    // Restaurant check all active orders, paginated and sortable
    org.springframework.data.domain.Page<com.cs5500.NEUEat.dto.response.OrderResponse> restaurantGetActiveOrders(
      String restaurantId, int page, int size, String sortBy, String direction);

    // Restaurant check order history, paginated and sortable
    org.springframework.data.domain.Page<com.cs5500.NEUEat.dto.response.OrderResponse> restaurantFindPastOrders(
      String restaurantId, int page, int size, String sortBy, String direction);

  int addComment(String id, int rating, String content);

  int deleteComment(String id);

  List<Comment> restaurantGetComments(String restaurantId);
  Page<OrderResponse> customerGetActiveOrders(String customerId, Pageable pageable);

  Page<OrderResponse> customerFindPastOrders(String customerId, Pageable pageable);
}
