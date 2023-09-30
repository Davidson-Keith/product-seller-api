package au.com.keithdavidson.productsellerapi.repository.projection;

import java.time.LocalDateTime;

public interface PurchaseItem {
    String getProductName();
    Double getPrice();
    LocalDateTime getPurchaseTime();
}