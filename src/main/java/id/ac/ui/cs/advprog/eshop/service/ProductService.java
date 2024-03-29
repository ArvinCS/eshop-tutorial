package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    public Product findById(String productId);

    public Product create(Product product);

    public Product update(String productId, Product product);

    public Product delete(String productId);

    public List<Product> findAll();
}
