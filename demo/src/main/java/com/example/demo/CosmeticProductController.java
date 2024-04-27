package com.example.demo;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CosmeticProductController {

    @Autowired
    private CosmeticProductService cosmeticProductService;

    @PostMapping("/cosmetic-product")
    public ResponseEntity<CosmeticProduct> add(@RequestBody CosmeticProduct cosmeticProduct) {
        CosmeticProduct newProduct = cosmeticProductService.createProduct(cosmeticProduct);
        if (newProduct != null) {
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cosmetic-products")
    public ResponseEntity<List<CosmeticProduct>> getAllProducts() {
        List<CosmeticProduct> products = cosmeticProductService.getAllProducts();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cosmetic-product/{productId}")
    public ResponseEntity<CosmeticProduct> updateProduct(@PathVariable int productId, @RequestBody CosmeticProduct cosmeticProduct) {
        boolean updated = cosmeticProductService.updateProduct(productId, cosmeticProduct);
        if (updated) {
            return new ResponseEntity<>(cosmeticProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cosmetic-product/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable int productId) {
        boolean deleted = cosmeticProductService.deleteProduct(productId);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
