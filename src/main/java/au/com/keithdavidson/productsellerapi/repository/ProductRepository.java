package au.com.keithdavidson.productsellerapi.repository;

import au.com.keithdavidson.productsellerapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Why don't we annotate this with @Repository, or at least with @Component?
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
