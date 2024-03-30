package dev.aloysius.SalesManagementSystem.Controllers;

import dev.aloysius.SalesManagementSystem.Domains.Products.CreateProduct;
import dev.aloysius.SalesManagementSystem.Domains.Products.Products;
import dev.aloysius.SalesManagementSystem.Services.ProductServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }
    @PostMapping("/")

    public void createNewProduct(@RequestBody CreateProduct product){
        productServices.createNewProduct(product);

    }
    @PutMapping("/")
    public void updateProduct(@RequestBody CreateProduct product){
        productServices.updateProduct(product);

    }
    @GetMapping("/")
    public List<Products> getAllProducts(){
        return productServices.getAllProduct();

    }
    public void deleteProduct(@RequestBody CreateProduct product){
        productServices.deleteProduct(product);

    }
}
