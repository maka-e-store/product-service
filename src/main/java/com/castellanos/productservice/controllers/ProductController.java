package com.castellanos.productservice.controllers;

import com.castellanos.productservice.models.Product;
import com.castellanos.productservice.models.ProductDTO;
import com.castellanos.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-quizzes")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getAllPruducts(@RequestParam(defaultValue = "${page.default.number}") Integer pageNumber,
                                        @RequestParam(defaultValue = "${page.default.size}") Integer pageSize,
                                        @RequestParam(defaultValue = "${page.default.sort}") String sort,
                                        @RequestParam(defaultValue = "${page.default.orderBy}") String orderBy){
        return this.productService.findAll(pageNumber,pageSize,sort, Sort.Direction.fromString(orderBy));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") final String id){
        return this.productService.findById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody final ProductDTO request){
        return this.productService.createProduct(request);
    }

    @PatchMapping
    public Product updateProduct(@RequestBody final ProductDTO update, @RequestParam final String id){
        return this.productService.updateProduct(update, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") final String id){
        this.productService.deleteProduct(id);
    }


}
