package au.com.keithdavidson.productsellerapi.service;

import au.com.keithdavidson.productsellerapi.model.Role;
import au.com.keithdavidson.productsellerapi.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUserName(String userName);

    void changeRole(Role newRole, String userName);
}
