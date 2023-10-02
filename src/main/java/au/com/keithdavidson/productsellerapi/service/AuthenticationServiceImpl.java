package au.com.keithdavidson.productsellerapi.service;

import au.com.keithdavidson.productsellerapi.model.User;
import au.com.keithdavidson.productsellerapi.security.UserPrincipal;
import au.com.keithdavidson.productsellerapi.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User signInAndReturnJWT(User signInRequestUser){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestUser.getUsername(), signInRequestUser.getPassword())
        );
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);
        User signInUser = userPrincipal.getUser();
        signInUser.setToken(jwt);
        return signInUser;
    }
}
