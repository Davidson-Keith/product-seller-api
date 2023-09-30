package au.com.keithdavidson.productsellerapi.service;

import au.com.keithdavidson.productsellerapi.model.Purchase;
import au.com.keithdavidson.productsellerapi.repository.PurchaseRepository;
import au.com.keithdavidson.productsellerapi.repository.projection.PurchaseItem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase savePurchase(Purchase purchase){
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<PurchaseItem> findAllPurchaseItemsByUser(Long userId){
        return purchaseRepository.findAllPurchaseItemsByUser(userId);
    }
}
