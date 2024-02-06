package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private static int productIdCounter = 0;
    private String productId;
    private String productName;
    private int productQuantity;

    public Product() {
        productIdCounter++;
        this.productId = String.valueOf(productIdCounter);
    }
}
