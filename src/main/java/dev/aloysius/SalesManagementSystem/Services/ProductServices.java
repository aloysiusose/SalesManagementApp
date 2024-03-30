package dev.aloysius.SalesManagementSystem.Services;

import dev.aloysius.SalesManagementSystem.Domains.Products.CreateProduct;
import dev.aloysius.SalesManagementSystem.Domains.Products.Products;
import dev.aloysius.SalesManagementSystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServices {

    private final ProductRepository productRepository;

    public void createNewProduct(CreateProduct product) {
        if (productRepository.findByProductName(product.name()).isPresent()) {
            //add to it
            //TODO:
        }
        //create a new product
    }

    public List<Products> getAllProduct() {
        return productRepository.findAll();
    }

    public void updateProduct(CreateProduct product) {
        if (productRepository.findByProductName(product.name()).isEmpty()) {
            throw new IllegalStateException();
        }
        Products products = productRepository.findByProductName(product.name()).get();
        products.setProductName(product.name());
        products.setProductDescription(product.description());
        products.setPrice(product.price());
        products.setQuantity(product.quantity());
        productRepository.save(products);
    }

    public void deleteProduct(CreateProduct product) {
        if (productRepository.findByProductName(product.name()).isEmpty()) {
            throw new IllegalStateException();
        }
        productRepository.delete(toProducts(product));
    }

    private Products toProducts(CreateProduct product) {
        Products products = new Products();
        products.setProductName(product.name());
        products.setProductDescription(product.description());
        products.setQuantity(product.quantity());

        return products;
    }
}
