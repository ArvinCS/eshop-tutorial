package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("e2e32r2-6969");
        this.product.setProductName("Arabica Beans");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("e2e32r2-6969", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Arabica Beans", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }
}
