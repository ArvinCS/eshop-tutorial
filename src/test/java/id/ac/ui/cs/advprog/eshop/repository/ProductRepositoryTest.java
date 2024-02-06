package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("e2e32r2-6969");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("e2e32r2-6969");
        product1.setProductName("Arabica Beans");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("e2e32r2-5678");
        product2.setProductName("Robusta Beans");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertTrue(productIterator.hasNext());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("e2e32r2-6969");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById("e2e32r2-6969"));
        assertNull(productRepository.findById("e2e32r2-6789"));
    }

    @Test
    void testEditProductName() {
        Product product = new Product();
        product.setProductId("e2e32r2-6969");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById("e2e32r2-6969"));
        Product savedProduct = productRepository.findById("e2e32r2-6969");
        assertEquals("Arabica Beans", savedProduct.getProductName());

        savedProduct.setProductName("Robusta Beans");
        productRepository.update("e2e32r2-6969", savedProduct);

        assertNotNull(productRepository.findById("e2e32r2-6969"));
        savedProduct = productRepository.findById("e2e32r2-6969");
        assertEquals("Robusta Beans", savedProduct.getProductName());
    }

    @Test
    void testEditProductQuantity() {
        Product product = new Product();
        product.setProductId("e2e32r2-6969");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById("e2e32r2-6969"));
        Product savedProduct = productRepository.findById("e2e32r2-6969");
        assertEquals(100, savedProduct.getProductQuantity());

        savedProduct.setProductQuantity(50);
        productRepository.update("e2e32r2-6969", savedProduct);

        assertNotNull(productRepository.findById("e2e32r2-6969"));
        savedProduct = productRepository.findById("e2e32r2-6969");
        assertEquals(50, savedProduct.getProductQuantity());
    }

    @Test
    void testDeleteExistingProduct() {
        Product product = new Product();
        product.setProductId("e2e32r2-6969");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById("e2e32r2-6969"));
        productRepository.delete("e2e32r2-6969");
        assertNull(productRepository.findById("e2e32r2-6969"));
    }

    @Test
    void testDeleteNonExistingProduct() {
        Product product = new Product();
        product.setProductId("e2e32r2-6969");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById("e2e32r2-6969"));
        productRepository.delete("abcdefg-12345");
        assertNull(productRepository.findById("abcdefg-12345"));
    }
}
