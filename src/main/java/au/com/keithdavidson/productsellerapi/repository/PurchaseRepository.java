package au.com.keithdavidson.productsellerapi.repository;

import au.com.keithdavidson.productsellerapi.model.Purchase;
import au.com.keithdavidson.productsellerapi.repository.projection.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("select " +
    "prd.name as productName, pur.price as price, pur.purchaseTime as purchaseTime " +
    "from Purchase pur left join Product prd on prd.id = pur.productId " +
    "where pur.userId = :userId")
    List<PurchaseItem> findAllPurchaseItemsByUser(@Param("userId") Long userId);
}
