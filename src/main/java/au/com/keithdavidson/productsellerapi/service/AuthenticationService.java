package au.com.keithdavidson.productsellerapi.service;

import au.com.keithdavidson.productsellerapi.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequestUser);
}
