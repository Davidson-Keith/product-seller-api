package au.com.keithdavidson.productsellerapi.controller;

import au.com.keithdavidson.productsellerapi.model.Product;
import au.com.keithdavidson.productsellerapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping() // api/product
  public ResponseEntity<?> saveProduct(@RequestBody Product product){
    return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
  }

  @DeleteMapping("delete/{productId}") // api/product/delete/{productId}
  public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
    productService.deleteProduct(productId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("id/{productId}") // api/product/id/{productId}
  public ResponseEntity<?> findById(@PathVariable Long productId){
    return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
  }

  @GetMapping("name/{productName}") // api/product/name/{productName}
  public ResponseEntity<?> findByName(@PathVariable String productName){
    return new ResponseEntity<>(productService.findByName(productName), HttpStatus.OK);
  }

  @GetMapping("all") // api/product/all
  public ResponseEntity<?> findAllProducts(){
    return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
  }
}
