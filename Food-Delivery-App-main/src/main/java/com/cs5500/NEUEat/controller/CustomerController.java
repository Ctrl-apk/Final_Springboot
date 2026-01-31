package com.cs5500.NEUEat.controller;

import com.cs5500.NEUEat.dto.response.ApiResponse;
import com.cs5500.NEUEat.dto.response.OrderResponse;
import com.cs5500.NEUEat.exception.OrderNotFinishedException;
import com.cs5500.NEUEat.exception.PasswordNotMatchException;
import com.cs5500.NEUEat.exception.UserAlreadyExistException;
import com.cs5500.NEUEat.exception.UserNotExistException;
import com.cs5500.NEUEat.model.Customer;
import com.cs5500.NEUEat.model.Order;
import com.cs5500.NEUEat.service.CustomerServiceImpl;
import com.cs5500.NEUEat.service.OrderServiceImpl;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

  private final CustomerServiceImpl customerService;
  private final OrderServiceImpl orderService;

  @Autowired
  public CustomerController(CustomerServiceImpl customerService, OrderServiceImpl orderService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

  @GetMapping(path = "{id}")
  public Customer getCustomerById(@PathVariable("id") String id)
      throws UserNotExistException {
    return customerService.getUser(id)
        .orElseThrow(() -> new UserNotExistException("User doesn't exist"));
  }

  @PostMapping(path = "/login")
  public Customer loginCustomer(@RequestBody String jsonUser)
      throws UserNotExistException, PasswordNotMatchException {

    JSONObject user = new JSONObject(jsonUser);
    String userName = user.getString("userName");
    String password = user.getString("password");

    Optional<Customer> customer = customerService.getUserByName(userName);
    if (!customer.isPresent()) {
      throw new UserNotExistException("User doesn't exist");
    }
    if (!customerService.passwordMatch(customer.get().getId(), password)) {
      throw new PasswordNotMatchException("Password doesn't match");
    }
    return customer.get();
  }

  @PostMapping(path = "/register")
  public Customer registerCustomer(@RequestBody String jsonUser)
      throws UserAlreadyExistException {

    JSONObject user = new JSONObject(jsonUser);
    Customer customer = customerService.addUser(
        user.getString("userName"),
        user.getString("password"),
        user.getString("phoneNumber"),
        user.getString("address"),
        user.getString("city"),
        user.getString("state"),
        user.getString("zip")
    );

    if (customer == null) {
      throw new UserAlreadyExistException("User already exists, please login");
    }
    return customer;
  }

  @PostMapping(path = "/logout")
  public int logoutCustomer() {
    return 1;
  }

  @GetMapping(path = "/myCart/{id}")
  public List<Order> getShoppingCart(@PathVariable("id") String id)
      throws UserNotExistException {
    if (!customerService.getUser(id).isPresent()) {
      throw new UserNotExistException("User doesn't exist");
    }
    return orderService.customerCart(id);
  }

  // ✅ PAGINATED ACTIVE ORDERS
  @GetMapping("/myActiveOrders/{id}")
  public ApiResponse<Page<OrderResponse>> getMyActiveOrders(
      @PathVariable("id") String customerId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "createdAt") String sortBy,
      @RequestParam(defaultValue = "desc") String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
        ? Sort.by(sortBy).descending()
        : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return new ApiResponse<>(
        true,
        "Active orders fetched successfully",
        orderService.customerGetActiveOrders(customerId, pageable)
    );
  }

  // ✅ PAGINATED ORDER HISTORY
  @GetMapping("/myOrderHistory/{id}")
  public ApiResponse<Page<OrderResponse>> getOrderHistory(
      @PathVariable("id") String id,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "createdAt") String sortBy,
      @RequestParam(defaultValue = "desc") String direction)
      throws UserNotExistException {

    if (!customerService.getUser(id).isPresent()) {
      throw new UserNotExistException("User doesn't exist");
    }

    Sort sort = direction.equalsIgnoreCase("desc")
        ? Sort.by(sortBy).descending()
        : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return new ApiResponse<>(
        true,
        "Order history fetched successfully",
        orderService.customerFindPastOrders(id, pageable)
    );
  }

  @DeleteMapping(path = "{id}")
  public int deleteCustomer(@PathVariable("id") String id)
      throws UserNotExistException, OrderNotFinishedException {

    if (!orderService.customerGetActiveOrders(id).isEmpty()) {
      throw new OrderNotFinishedException(
          "You still have active orders, please finish them first"
      );
    }

    int res = customerService.deleteUser(id);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist");
    }
    return res;
  }

  @PostMapping(path = "/resetPassword")
  public int resetPassword(@RequestBody String jsonPassword)
      throws UserNotExistException, PasswordNotMatchException {

    JSONObject object = new JSONObject(jsonPassword);
    int res = customerService.updatePassword(
        object.getString("id"),
        object.getString("password"),
        object.getString("newPassword")
    );

    if (res == -1) throw new UserNotExistException("User doesn't exist");
    if (res == 0) throw new PasswordNotMatchException("Password doesn't match");
    return res;
  }

  @PostMapping(path = "/resetPhone")
  public int resetPhoneNumber(@RequestBody String jsonPhone)
      throws UserNotExistException {

    JSONObject object = new JSONObject(jsonPhone);
    int res = customerService.updatePhoneNumber(
        object.getString("id"),
        object.getString("phoneNumber")
    );

    if (res == -1) throw new UserNotExistException("User doesn't exist");
    return res;
  }

  @PostMapping(path = "/resetAddress")
  public int resetAddress(@RequestBody String jsonAddress)
      throws UserNotExistException {

    JSONObject object = new JSONObject(jsonAddress);
    int res = customerService.updateAddress(
        object.getString("id"),
        object.getString("address"),
        object.getString("city"),
        object.getString("state"),
        object.getString("zip")
    );

    if (res == -1) throw new UserNotExistException("User doesn't exist");
    return res;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
      UserNotExistException.class,
      PasswordNotMatchException.class,
      UserAlreadyExistException.class,
      OrderNotFinishedException.class
  })
  public String handleException(Exception e) {
    return e.getMessage();
  }
}
