package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        assertEquals(product, productService.create(product));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        productService.create(product);

        assertEquals(product, productService.findById("abcde-12345"));
    }

    @Test
    void testFindByIdNonExisting() {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        productService.create(product);

        assertNull(productService.findById("abcde-67890"));
    }

    @Test
    void testFindAll() {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        assertTrue(productService.findAll().isEmpty());

        productService.create(product);

        assertEquals(1, productService.findAll().size());
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        productService.create(product);

        product.setProductName("Robusta Beans");
        productService.update("abcde-12345", product);

        assertEquals(product, productService.findById("abcde-12345"));
    }

    @Test
    void testUpdateNonExisting() {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        productService.create(product);

        product.setProductName("Robusta Beans");
        assertNull(productService.update("aaaaaa", product));
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        assertTrue(productService.findAll().isEmpty());

        productService.create(product);
        assertFalse(productService.findAll().isEmpty());

        productService.delete("abcde-12345");
        assertTrue(productService.findAll().isEmpty());
    }
}
