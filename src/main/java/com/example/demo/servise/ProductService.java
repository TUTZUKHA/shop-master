package com.example.demo.servise;

import com.example.demo.entity.Product;
import com.example.demo.entity.create.CreateProduct;
import com.example.demo.entity.dto.ProductDto;
import com.example.demo.entity.update.UpdateProduct;
import com.example.demo.excaption.BadRequest;
import com.example.demo.repo.ProductRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductDto create(CreateProduct createProduct) {
            Product product = new Product();
        product.setCreateAt(LocalDateTime.now());
        product.setName(createProduct.getName());
        product.setDescription(createProduct.getDescription());
        product.setPrice(createProduct.getPrice());
        product.setProductType(createProduct.getProductType());
        productRepo.save(product);
        return returnDto(product);
    }

    private ProductDto returnDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setDescription(product.getDescription());
        dto.setName(product.getName());
        dto.setRate(product.getRate());
        dto.setPrice(product.getPrice());
        dto.setProductType(product.getProductType());
        return dto;
    }

    public Object getAll() {
        List<Product>products = productRepo.findAll();
        List<ProductDto>dtoProducts = new ArrayList<>();
        for (Product p:products) {
            dtoProducts.add(returnDto(p));
        }
        return dtoProducts;
    }
    private Product getProduct(Integer id){
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()){
            throw new BadRequest("Product not found!");
        }
        return product.get();
    }
    public Object update(Integer id, UpdateProduct product) {
        Product oldProduct = getProduct(id);
        if (product.getName() != null || !product.getName().isEmpty()){
            oldProduct.setName(product.getName());
        }
        if (!product.getDescription().isEmpty()){
            oldProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            oldProduct.setPrice(product.getPrice());
        }
        if (product.getRate() != null){
            oldProduct.setRate(product.getRate());
        }
        if (product.getVisible() != null){
            oldProduct.setVisible(product.getVisible());
        }
        if (product.getProductType() != null){
            oldProduct.setProductType(product.getProductType());
        }
        if (product.getProductStatus() != null){
            oldProduct.setProductStatus(product.getProductStatus());
        }
        productRepo.save(oldProduct);
        return returnDto(oldProduct);
    }
    public Object getBuId(Integer id) {
        return returnDto(getProduct(id));
    }

    public Object getAllAdmin() {
        return productRepo.findAll();
    }
}
