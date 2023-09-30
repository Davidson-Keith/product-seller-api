package au.com.keithdavidson.productsellerapi.controller;

import au.com.keithdavidson.productsellerapi.model.Purchase;
import au.com.keithdavidson.productsellerapi.security.UserPrincipal;
import au.com.keithdavidson.productsellerapi.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
  @Autowired
  PurchaseService purchaseService;

  @PostMapping() // api/purchase
  public ResponseEntity<?> savePurchase(@RequestBody Purchase purchase){
    return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.CREATED);
  }

  @GetMapping() // api/purchase}
  public ResponseEntity<?> getAllPurchasesByUser(@AuthenticationPrincipal UserPrincipal userPrincipal){
    System.out.println("userPrincipal.getId() = " + userPrincipal.getId());
    return new ResponseEntity<>(purchaseService.findAllPurchaseItemsByUser(userPrincipal.getId()), HttpStatus.OK);
  }
}
