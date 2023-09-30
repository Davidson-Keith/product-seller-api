package au.com.keithdavidson.productsellerapi.repository;

import au.com.keithdavidson.productsellerapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Why don't we annotate this with @Repository, or at least with @Component? And then @Autowire it into ProductServiceImpl?
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findByName(String name);
}
