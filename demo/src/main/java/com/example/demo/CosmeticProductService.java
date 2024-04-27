package com.example.demo;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CosmeticProductService {

    @Autowired
    private CosmeticProductRepo cosmeticProductRepo;

    public CosmeticProduct createProduct(@NonNull CosmeticProduct product) {
        return cosmeticProductRepo.save(product);
    }

    public List<CosmeticProduct> getAllProducts() {
        return cosmeticProductRepo.findAll();
    }

    public CosmeticProduct getProductById(@NonNull Integer id) {
        return cosmeticProductRepo.findById(id).orElse(null);
    }

    public boolean updateProduct(int id, CosmeticProduct product) {
        if (getProductById(id) == null) {
            return false;
        }
        try {
            product.setId(id); // Ensure the ID is set for proper update
            cosmeticProductRepo.save(product);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteProduct(int id) {
        if (getProductById(id) == null) {
            return false;
        }
        cosmeticProductRepo.deleteById(id);
        return true;
    }
}
