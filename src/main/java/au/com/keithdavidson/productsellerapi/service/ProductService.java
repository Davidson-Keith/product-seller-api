package au.com.keithdavidson.productsellerapi.service;

import au.com.keithdavidson.productsellerapi.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);

    void deleteProduct(Long id);

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    List<Product> findAllProducts();
}
