package com.cs5500.NEUEat.repository;

import com.cs5500.NEUEat.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity
 * Extends MongoRepository for MongoDB operations
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Find a user by username
     * @param userName the username to search for
     * @return the User object if found, null otherwise
     */
    User findByUserName(String userName);
}
