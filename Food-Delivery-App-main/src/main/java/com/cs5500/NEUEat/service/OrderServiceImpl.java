package com.cs5500.NEUEat.service;

import com.cs5500.NEUEat.dto.response.DishResponse;
import com.cs5500.NEUEat.dto.response.OrderResponse;
import com.cs5500.NEUEat.model.Comment;
import com.cs5500.NEUEat.model.Dish;
import com.cs5500.NEUEat.model.Order;
import com.cs5500.NEUEat.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderRepository orderRepository;

  // =========================
  // CUSTOMER – PAGINATED ACTIVE ORDERS
  // =========================
  @Override
  public Page<OrderResponse> customerGetActiveOrders(String customerId, Pageable pageable) {

    List<Order> filtered = orderRepository.findAll().stream()
        .filter(o ->
            o.getCustomerId().equals(customerId)
            && o.getStartTime() != null
            && o.getEndTime() == null
        )
        .collect(Collectors.toList());

    Comparator<Order> comparator = Comparator.comparing(
        Order::getStartTime,
        Comparator.nullsLast(Comparator.naturalOrder())
    );

    if (pageable.getSort().isSorted()) {
      Sort.Order sortOrder = pageable.getSort().iterator().next();
      if ("price".equalsIgnoreCase(sortOrder.getProperty())) {
        comparator = Comparator.comparingDouble(Order::getPrice);
      }
      if (sortOrder.getDirection() == Sort.Direction.DESC) {
        comparator = comparator.reversed();
      }
    }

    filtered.sort(comparator);

    int start = (int) pageable.getOffset();
    int end = Math.min(start + pageable.getPageSize(), filtered.size());

    List<OrderResponse> content = filtered.subList(start, end)
        .stream()
        .map(this::mapToOrderResponse)
        .collect(Collectors.toList());

    return new PageImpl<>(content, pageable, filtered.size());
  }

  // =========================
  // CUSTOMER – PAGINATED ORDER HISTORY
  // =========================
  @Override
  public Page<OrderResponse> customerFindPastOrders(String customerId, Pageable pageable) {

    List<Order> filtered = orderRepository.findAll().stream()
        .filter(o ->
            o.getCustomerId().equals(customerId)
            && o.getEndTime() != null
        )
        .collect(Collectors.toList());

    Comparator<Order> comparator = Comparator.comparing(
        Order::getEndTime,
        Comparator.nullsLast(Comparator.naturalOrder())
    );

    if (pageable.getSort().isSorted()) {
      Sort.Order sortOrder = pageable.getSort().iterator().next();
      if ("price".equalsIgnoreCase(sortOrder.getProperty())) {
        comparator = Comparator.comparingDouble(Order::getPrice);
      }
      if (sortOrder.getDirection() == Sort.Direction.DESC) {
        comparator = comparator.reversed();
      }
    }

    filtered.sort(comparator);

    int start = (int) pageable.getOffset();
    int end = Math.min(start + pageable.getPageSize(), filtered.size());

    List<OrderResponse> content = filtered.subList(start, end)
        .stream()
        .map(this::mapToOrderResponse)
        .collect(Collectors.toList());

    return new PageImpl<>(content, pageable, filtered.size());
  }

  // =========================
  // MAPPER
  // =========================
  private OrderResponse mapToOrderResponse(Order order) {

    List<DishResponse> dishes = new ArrayList<>();
    if (order.getContent() != null) {
      dishes = order.getContent().stream().map(d -> {
        DishResponse dr = new DishResponse();
        dr.setDishName(d.getDishName());
        dr.setPrice(d.getPrice());
        return dr;
      }).collect(Collectors.toList());
    }

    String status = "PENDING";
    if (order.getEndTime() != null) status = "FINISHED";
    else if (order.isDelivery()) status = "DELIVERING";
    else if (order.getStartTime() != null) status = "CONFIRMED";

    String createdAt =
        order.getStartTime() != null ? order.getStartTime().toString() : "";

    return new OrderResponse(
        order.getId(),
        order.getCustomerId(),
        order.getRestaurantId(),
        dishes,
        order.getPrice(),
        status,
        createdAt
    );
  }

  // ===== (all your other existing methods remain unchanged) =====

  @Override
  public int addOrderToCart(String customerId, String restaurantId, List<Dish> content) {
    List<Order> currentCart = this.customerCart(customerId);
    for (Order o : currentCart) {
      if (o.getRestaurantId().equals(restaurantId)) {
        List<Dish> menu = o.getContent();
        menu.addAll(content);
        double price = 0;
        for (Dish dish : menu) {
          price += dish.getPrice();
        }
        o.setPrice(price);
        orderRepository.save(o);
        System.out.println("Order added to the cart");
        return 1;
      }
    }
    Order order = new Order();
    order.setCustomerId(customerId);
    order.setRestaurantId(restaurantId);
    order.setContent(content);
    double price = 0;
    for (Dish dish : content) {
      price += dish.getPrice();
    }
    order.setPrice(price);
    orderRepository.save(order);
    System.out.println("Order added to the cart");
    return 1;
  }

  @Override
  public int checkoutOrder(String id) {
    Optional<Order> order = getOrder(id);
    if (!order.isPresent()) {
      System.out.println("Order doesn't exist");
      return 0;
    }
    if (order.get().getStartTime() == null) {
      order.get().setStartTime(LocalDateTime.now());
      orderRepository.save(order.get());
      System.out.println("Order checkouts");
      return 1;
    }
    System.out.println("Order already checkout");
    return -1;
  }

  @Override
  public int checkoutAll(List<Order> orders) {
    for (Order order : orders) {
      String id = order.getId();
      int res = checkoutOrder(id);
      if (res == 0) {
        return 0;
      }
      if (res == -1) {
        return -1;
      }
    }
    return 1;
  }

  @Override
  public int cancelOrder(String id) {
    Optional<Order> order = getOrder(id);
    if (!order.isPresent()) {
      System.out.println("Order doesn't exist");
      return 0;
    }
    if (!order.get().isDelivery()) {
      orderRepository.deleteById(id);
      System.out.println("Order canceled");
      return 1;
    }
    System.out.println("Can't cancel order. It is either in delivery or finished");
    return -1;
  }

  @Override
  public Optional<Order> getOrder(String id) {
    if (id != null) {
      return orderRepository.findById(id);
    }
    return Optional.empty();
  }

  @Override
  public int acceptOrder(String id, String driverId) {
    Optional<Order> orderOrNot = getOrder(id);
    if (orderOrNot.isPresent()) {
      Order order = orderOrNot.get();
      if (order.getStartTime() != null && !order.isDelivery()) {
        order.setDelivery(true);
        order.setDriverId(driverId);
        orderRepository.save(order);
        System.out.println("Driver accepts the order");
        return 1;
      } else {
        System.out.println("The order can't be accepted");
        return 0;
      }
    }
    System.out.println("The order doesn't exist");
    return -1;
  }

  @Override
  public int finishOrder(String id) {
    Optional<Order> orderOrNot = getOrder(id);
    if (orderOrNot.isPresent()) {
      Order order = orderOrNot.get();
      if (order.isDelivery() && order.getEndTime() == null) {
        order.setEndTime(LocalDateTime.now());
        orderRepository.save(order);
        System.out.println("Driver finishes the order");
        return 1;
      } else {
        System.out.println("The order can't be finished");
        return 0;
      }
    }
    System.out.println("The order can't be finished");
    return -1;
  }

  @Override
  public List<Order> customerCart(String customerId) {
    return orderRepository.findAll().stream()
        .filter(order -> order.getCustomerId().equals(customerId) && order.getStartTime() == null)
        .collect(
            Collectors.toList());
  }

  @Override
  public List<Order> customerGetActiveOrders(String customerId) {
    return orderRepository.findAll().stream().filter(
        order -> order.getCustomerId().equals(customerId) && order.getStartTime() != null
            && order.getEndTime() == null).collect(
        Collectors.toList());
  }

  @Override
  public List<Order> customerFindPastOrders(String customerId) {
    return orderRepository.findAll().stream()
        .filter(order -> order.getCustomerId().equals(customerId) && order.getEndTime() != null)
        .collect(
            Collectors.toList());
  }

  @Override
  public List<Order> getAllPendingOrders() {
    return orderRepository.findAll().stream()
        .filter(order -> order.getStartTime() != null && !order.isDelivery()).collect(
            Collectors.toList());
  }

  @Override
  public Order driverGetActiveOrder(String driverId) {
    for (Order order : orderRepository.findAll()) {
      if (order.getDriverId() != null && order.getDriverId().equals(driverId)
          && order.getEndTime() == null) {
        return order;
      }
    }
    return null;
  }

  @Override
  public List<Order> driverFindPastOrders(String driverId) {
    return orderRepository.findAll().stream()
        .filter(order -> order.getDriverId() != null && order.getDriverId().equals(driverId)
            && order.getEndTime() != null)
        .collect(
            Collectors.toList());
  }

  @Override
  public Page<OrderResponse> restaurantGetActiveOrders(String restaurantId, int page, int size,
      String sortBy, String direction) {
    List<Order> filtered = orderRepository.findAll().stream().filter(
        order -> order.getRestaurantId().equals(restaurantId) && order.getStartTime() != null
            && order.getEndTime() == null).collect(
        Collectors.toList());

    Comparator<Order> comparator = Comparator.comparing(Order::getStartTime,
        Comparator.nullsLast(Comparator.naturalOrder()));
    if ("price".equalsIgnoreCase(sortBy)) {
      comparator = Comparator.comparingDouble(Order::getPrice);
    }
    if ("desc".equalsIgnoreCase(direction)) comparator = comparator.reversed();
    filtered.sort(comparator);

    int total = filtered.size();
    int start = page * size;
    if (start > total) start = total;
    int end = Math.min(start + size, total);
    List<OrderResponse> content = filtered.subList(start, end).stream()
        .map(this::mapToOrderResponse)
        .collect(Collectors.toList());
    return new PageImpl<>(content, PageRequest.of(page, size), total);
  }

  @Override
  public Page<OrderResponse> restaurantFindPastOrders(String restaurantId, int page, int size,
      String sortBy, String direction) {
    List<Order> filtered = orderRepository.findAll().stream()
        .filter(order -> order.getRestaurantId().equals(restaurantId) && order.getEndTime() != null)
        .collect(Collectors.toList());

    Comparator<Order> comparator = Comparator.comparing(Order::getEndTime,
        Comparator.nullsLast(Comparator.naturalOrder()));
    if ("price".equalsIgnoreCase(sortBy)) {
      comparator = Comparator.comparingDouble(Order::getPrice);
    }
    if ("desc".equalsIgnoreCase(direction)) comparator = comparator.reversed();
    filtered.sort(comparator);

    int total = filtered.size();
    int start = page * size;
    if (start > total) start = total;
    int end = Math.min(start + size, total);
    List<OrderResponse> content = filtered.subList(start, end).stream()
        .map(this::mapToOrderResponse)
        .collect(Collectors.toList());
    return new PageImpl<>(content, PageRequest.of(page, size, Sort.unsorted()), total);
  }

  @Override
  public int addComment(String id, int rating, String content) {
    Optional<Order> orderOrNot = getOrder(id);
    if (orderOrNot.isPresent()) {
      Order order = orderOrNot.get();
      if (order.getComment() == null) {
        Comment newComment = new Comment(rating, content);
        order.setComment(newComment);
        orderRepository.save(order);
        System.out.println("Add a comment");
        return 1;
      } else {
        System.out.println("The order already has a comment");
        return 0;
      }
    }
    System.out.println("The order doesn't exist");
    return -1;
  }

  @Override
  public int deleteComment(String id) {
    Optional<Order> orderOrNot = getOrder(id);
    if (orderOrNot.isPresent()) {
      Order order = orderOrNot.get();
      order.setComment(null);
      orderRepository.save(order);
      System.out.println("Delete a comment");
      return 1;
    }
    System.out.println("The order doesn't exist");
    return -1;
  }

  @Override
  public List<Comment> restaurantGetComments(String restaurantId) {
    List<Order> temp = orderRepository.findAll().stream()
        .filter(order -> order.getRestaurantId().equals(restaurantId) && order.getEndTime() != null)
        .collect(Collectors.toList());
    List<Comment> res = new ArrayList<>();
    for (Order o : temp) {
      if (o.getComment() != null) {
        res.add(o.getComment());
      }
    }
    return res;
  }
}
