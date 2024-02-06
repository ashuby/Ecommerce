package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Services.ProductService;
import com.example.Ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productServ;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Product save(@RequestBody Product product){
        System.out.println("Check");
        return productServ.saveProduct(product);
    }
    @DeleteMapping("/deleteProduct")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody Product product) {
        productServ.deleteProduct(product);
    }

    @DeleteMapping("/deletebyid/{id}")
    public void deleteProductByid(@PathVariable int id) {
        productServ.deleteProductById(id);
    }

    @GetMapping("/getallproducts")
    public List<Product> getallUsers() {
        List<Product> product = productServ.getallproducts();
        return product;
    }
    @PutMapping("/update")
    public Optional<Product> updateProduct(@RequestBody Product product) {
        Optional<Product> updatedProduct = productServ.updateproduct(product);
        return updatedProduct;
    }
}
