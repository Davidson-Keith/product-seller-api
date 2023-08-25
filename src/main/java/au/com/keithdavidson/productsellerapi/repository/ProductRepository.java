package au.com.keithdavidson.productsellerapi.repository;

import au.com.keithdavidson.productsellerapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
