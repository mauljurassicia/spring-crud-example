package com.telusko.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.telusko.demo.models.Product;

@Service
public class ProductService {

    List<Product> products = new ArrayList<Product>(
            Arrays.asList(new Product(101, "Product 1", "Description 1", 100.0f, "Image 1"),
                    new Product(102, "Product 2", "Description 2", 200.0f, "Image 2"),
                    new Product(103, "Product 3", "Description 3", 300.0f, "Image 3")));

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().get();
    }

    public Product addProduct(Product product) {
        if (products.stream().anyMatch(p -> p.getId() == product.getId())) {
            throw new RuntimeException("Product with id " + product.getId() + " already exists");
        }
        products.add(product);

        return product;
    }

    public Product updateProduct(int id, Product product) {
        return products.stream().filter(p -> p.getId() == id).findFirst().map(p -> {
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setImage(product.getImage());
            return p;
        }).get();
    }

    public String deleteProduct(int id) {
        products.removeIf(p -> p.getId() == id);
        return "Product with id " + id + " deleted";
    }

}
