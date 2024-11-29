package com.springboot.jpaTutorial.JPATuts.controller;

import com.springboot.jpaTutorial.JPATuts.entities.ProductEntity;
import com.springboot.jpaTutorial.JPATuts.repositories.ProductRepository;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final int PAGE_SIZE = 5;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//    @GetMapping
//    public List<ProductEntity> getAllProducts(@RequestParam(defaultValue = "id") String sortBy){
////        return productRepository.findByOrderByPrice();
////        return productRepository.findBy(Sort.by(sortBy));    // By default Accending
////        return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy)); // decending
////        return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price", "quantity"));
////        return productRepository.findBy(Sort.by(
//        Sort.Order.asc(sortBy),
//                Sort.Order.desc("title")
////        ));    ///////------------------------ Sorting

    @GetMapping
    public List<ProductEntity> getAllProducts(@RequestParam(defaultValue = "id") String sortBy,
                                              @RequestParam(defaultValue = "0") Integer pageNumber){
//        return productRepository.findByOrderByPrice();
//        return productRepository.findBy(Sort.by(sortBy));    // By default Accending
//        return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy)); // decending
//        return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price", "quantity"))
//        return productRepository.findBy(Sort.by(
//                        Sort.Order.asc(sortBy),
//                        Sort.Order.desc("title")
//        ));
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy));
        return productRepository.findAll(pageable).getContent();
    }


}
