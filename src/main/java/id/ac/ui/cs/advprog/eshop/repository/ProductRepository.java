package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product findById(String productId) {
        Optional<Product> productOptional = productData.stream()
                .filter((currentProduct) -> currentProduct.getProductId().equals(productId))
                .findAny();

        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            return null;
        }
    }
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product update(String productId, Product productDetail) {
        Product product = findById(productId);

        if (product != null) {
            product.setProductName(productDetail.getProductName());
            product.setProductQuantity(productDetail.getProductQuantity());
            return product;
        } else {
            return null;
        }
    }

    public Product delete(String productId) {
        Product product = findById(productId);
        if (product != null) {
            productData.remove(product);
            return product;
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
