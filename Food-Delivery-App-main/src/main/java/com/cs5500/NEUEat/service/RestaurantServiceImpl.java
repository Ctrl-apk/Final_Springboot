package com.cs5500.NEUEat.service;

import com.cs5500.NEUEat.dto.response.RestaurantResponse;
import com.cs5500.NEUEat.model.Dish;
import com.cs5500.NEUEat.model.Restaurant;
import com.cs5500.NEUEat.model.RestaurantInfo;
import com.cs5500.NEUEat.repository.RestaurantRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService, UserService<Restaurant> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    private final PasswordService passwordService = new PasswordService();

    /* ===================== DISH APIs ===================== */

    @Override
    public int addDish(String id, Dish dish) {
        Optional<Restaurant> restaurant = this.getUser(id);
        if (restaurant.isPresent()) {
            Set<Dish> set = restaurant.get().getMenu() == null
                    ? new HashSet<>()
                    : new HashSet<>(restaurant.get().getMenu());

            set.add(dish);
            restaurant.get().setMenu(new ArrayList<>(set));
            restaurantRepository.save(restaurant.get());
            return 1;
        }
        return -1;
    }

    @Override
    public int removeDish(String id, Dish dish) {
        Optional<Restaurant> restaurant = this.getUser(id);
        if (restaurant.isPresent()) {
            List<Dish> menu = restaurant.get().getMenu();
            if (menu != null && menu.remove(dish)) {
                restaurantRepository.save(restaurant.get());
                return 1;
            }
            return 0;
        }
        return -1;
    }

    @Override
    public List<Dish> getAllDishes(String id) {
        return getUser(id).map(Restaurant::getMenu).orElse(null);
    }

    /* ===================== INFO APIs ===================== */

    @Override
    public RestaurantInfo getInformation(String id) {
        return getUser(id)
                .map(r -> r.getInformation() == null ? new RestaurantInfo() : r.getInformation())
                .orElse(null);
    }

    @Override
    public int updateInfo(String id, RestaurantInfo info) {
        Optional<Restaurant> restaurant = getUser(id);
        if (restaurant.isPresent()) {
            restaurant.get().setInformation(info);
            restaurantRepository.save(restaurant.get());
            return 1;
        }
        return -1;
    }

    /* ===================== USER APIs ===================== */

    @Override
    public Restaurant addUser(String userName, String password, String phoneNumber,
                              String address, String city, String state, String zip) {

        if (getUserIdByName(userName) == null) {
            String hashedPassword = passwordService.generatePassword(password);
            Restaurant restaurant = new Restaurant(
                    userName, hashedPassword, phoneNumber, address, city, state, zip
            );
            restaurantRepository.insert(restaurant);
            return restaurant;
        }
        return null;
    }

    @Override
    public int deleteUser(String id) {
        if (getUser(id).isPresent()) {
            restaurantRepository.deleteById(id);
            return 1;
        }
        return -1;
    }

    @Override
    public Optional<Restaurant> getUser(String id) {
        return id == null ? Optional.empty() : restaurantRepository.findById(id);
    }

    @Override
    public String getUserIdByName(String userName) {
        return restaurantRepository.findAll().stream()
                .filter(r -> r.getUserName().equals(userName))
                .map(Restaurant::getId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Optional<Restaurant> getUserByName(String userName) {
        return getUser(getUserIdByName(userName));
    }

    @Override
    public List<Restaurant> getUsers() {
        return restaurantRepository.findAll();
    }

    @Override
    public boolean passwordMatch(String id, String password) {
        return getUser(id)
                .map(r -> passwordService.passwordMatch(password, r.getPassword()))
                .orElse(false);
    }

    @Override
    public int updatePassword(String id, String oldPassword, String newPassword) {
        Optional<Restaurant> restaurant = getUser(id);
        if (restaurant.isPresent() && passwordMatch(id, oldPassword)) {
            restaurant.get().setPassword(passwordService.generatePassword(newPassword));
            restaurantRepository.save(restaurant.get());
            return 1;
        }
        return -1;
    }

    @Override
    public int updatePhoneNumber(String id, String newNumber) {
        Optional<Restaurant> restaurant = getUser(id);
        if (restaurant.isPresent()) {
            restaurant.get().setPhoneNumber(newNumber);
            restaurantRepository.save(restaurant.get());
            return 1;
        }
        return -1;
    }

    @Override
    public int updateAddress(String id, String address, String city, String state, String zip) {
        Optional<Restaurant> restaurant = getUser(id);
        if (restaurant.isPresent()) {
            Restaurant r = restaurant.get();
            r.setAddress(address);
            r.setCity(city);
            r.setState(state);
            r.setZip(zip);
            restaurantRepository.save(r);
            return 1;
        }
        return -1;
    }

    /* ===================== PAGINATION + FILTERING ===================== */

    @Override
    public Page<RestaurantResponse> getAllRestaurants(
            int page,
            int size,
            String sortBy,
            String direction,
            String city,
            String tag) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Restaurant> restaurants;

        // Apply city filter if provided
        if (city != null && !city.isEmpty()) {
            restaurants = restaurantRepository.findByCityIgnoreCase(city, pageable);
        } else {
            restaurants = restaurantRepository.findAll(pageable);
        }

        // Apply tag filter if provided
        if (tag != null && !tag.isEmpty()) {
            final String filterTag = tag.toLowerCase();
            List<Restaurant> filteredContent = restaurants.getContent().stream()
                    .filter(restaurant -> 
                        restaurant.getInformation() != null && (
                            (restaurant.getInformation().getTag1() != null && 
                             restaurant.getInformation().getTag1().toLowerCase().contains(filterTag)) ||
                            (restaurant.getInformation().getTag2() != null && 
                             restaurant.getInformation().getTag2().toLowerCase().contains(filterTag)) ||
                            (restaurant.getInformation().getTag3() != null && 
                             restaurant.getInformation().getTag3().toLowerCase().contains(filterTag))
                        )
                    )
                    .collect(java.util.stream.Collectors.toList());
            
            restaurants = new org.springframework.data.domain.PageImpl<>(
                    filteredContent, 
                    restaurants.getPageable(), 
                    filteredContent.size()
            );
        }

        return restaurants.map(this::mapToRestaurantResponse);
    }

    /* ===================== MAPPER ===================== */

    private RestaurantResponse mapToRestaurantResponse(Restaurant restaurant) {
        List<String> tags = null;
        if (restaurant.getInformation() != null) {
            tags = new ArrayList<>();
            if (restaurant.getInformation().getTag1() != null) {
                tags.add(restaurant.getInformation().getTag1());
            }
            if (restaurant.getInformation().getTag2() != null) {
                tags.add(restaurant.getInformation().getTag2());
            }
            if (restaurant.getInformation().getTag3() != null) {
                tags.add(restaurant.getInformation().getTag3());
            }
        }
        
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getUserName(),
                restaurant.getInformation() != null
                        ? restaurant.getInformation().getDescription()
                        : null,
                restaurant.getAddress(),
                restaurant.getCity(),
                restaurant.getState(),
                restaurant.getPhoneNumber(),
                tags,
                null
        );
    }
}
