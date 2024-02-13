package id.ac.ui.cs.advprog.eshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService service;

    @Test
    void testListPage() throws Exception {
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreatePage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreatePost() throws Exception {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        mockMvc.perform(post("/product/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isFound());
    }

    @Test
    void testEditPage() throws Exception {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        mockMvc.perform(get("/product/abcde-12345/edit"))
                .andExpect(status().isFound());

        Mockito.when(service.findById("abcde-12345")).thenReturn(product);

        mockMvc.perform(get("/product/abcde-12345/edit"))
                .andExpect(status().isOk());
    }

    @Test
    void testEditPost() throws Exception {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        Mockito.when(service.findById("abcde-12345")).thenReturn(product);

        product.setProductName("Robusta Beans");
        mockMvc.perform(post("/product/abcde-12345/edit")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isFound());
    }

    @Test
    void testEditPostNonExisting() throws Exception {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        mockMvc.perform(post("/product/abcde-12345/edit")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isFound());
    }

    @Test
    void testDeletePost() throws Exception {
        Product product = new Product();
        product.setProductId("abcde-12345");
        product.setProductName("Arabica Beans");
        product.setProductQuantity(100);

        mockMvc.perform(delete("/product/abcde-12345/delete"))
                .andExpect(status().isFound());

        Mockito.when(service.findById("abcde-12345")).thenReturn(product);

        mockMvc.perform(delete("/product/abcde-12345/delete"))
                .andExpect(status().isFound());
    }
}
