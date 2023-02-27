package com.castellanos.productservice.services;

import com.castellanos.productservice.models.Product;
import com.castellanos.productservice.models.ProductDTO;
import com.castellanos.productservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    private final String productNotFound = "Product with associated Id does not exist: ";

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Product> findAll(int pageNumber, int pageSize, String sort, Sort.Direction orderBy){
        Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(orderBy,sort));
        return this.productRepo.findAll(pageable);
    }

   public Product findById(String id){
       return this.productRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, productNotFound + id));
   }

   public Product createProduct(ProductDTO productDto){
        Product product = new Product(productDto.getId(), productDto.getName(), productDto.getCost(), productDto.getSize(),
                                        productDto.getDescription(), productDto.getPhoto(), productDto.getAmount(), productDto.getProductType());
        return this.productRepo.save(product);
   }

   public Product updateProduct(ProductDTO productDto, String prodId){
       Product updatedProduct = new Product(productDto.getName(), productDto.getCost(), productDto.getSize(),
               productDto.getDescription(), productDto.getPhoto(), productDto.getAmount(), productDto.getProductType());
       return this.productRepo.save(updatedProduct);
   }

   public void deleteProduct(String id){
        this.productRepo.deleteById(id);
   }
}
