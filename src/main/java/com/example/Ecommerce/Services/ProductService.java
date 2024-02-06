package com.example.Ecommerce.Services;

import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;

    public Product saveProduct(Product product){
        return productRepo.save(product);
    }

    public void deleteProduct(Product product) { productRepo.delete(product);}
    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }
    public List<Product> getallproducts() {
        return productRepo.findAll();
    }
    public Optional<Product> updateproduct(Product product) {
        Optional<Product> updatedProduct = Optional.of(productRepo.saveAndFlush(product));
        int id = product.getId();
        if(existbyid(id)) return updatedProduct;
        return null;
    }
    public boolean existbyid(int id) {
        boolean existornot = productRepo.existsById(id);
        return existornot;
    }
}
