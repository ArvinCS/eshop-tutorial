package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListingPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/{productId}/edit")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        if (product == null) {
            return "redirect:/product/list";
        }

        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/{productId}/edit")
    public String editProductPost(@PathVariable String productId, @ModelAttribute Product updatedProduct) {
        Product product = service.findById(productId);
        if (product == null) {
            return "redirect:/product/list";
        }

        service.update(productId, updatedProduct);
        return "redirect:/product/list";
    }

    @DeleteMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable String productId) {
        Product product = service.findById(productId);
        if (product == null) {
            return "redirect:/product/list";
        }

        service.delete(productId);
        return "redirect:/product/list";
    }
}

