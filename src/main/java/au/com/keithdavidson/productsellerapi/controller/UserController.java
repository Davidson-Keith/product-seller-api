package au.com.keithdavidson.productsellerapi.controller;

import au.com.keithdavidson.productsellerapi.model.Role;
import au.com.keithdavidson.productsellerapi.security.UserPrincipal;
import au.com.keithdavidson.productsellerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
  @Autowired
  private UserService userService;

  public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role){
    userService.changeRole(role, userPrincipal.getUsername());
//    return new ResponseEntity<>(HttpStatus.OK);
    return ResponseEntity.ok(true);
  }
}
