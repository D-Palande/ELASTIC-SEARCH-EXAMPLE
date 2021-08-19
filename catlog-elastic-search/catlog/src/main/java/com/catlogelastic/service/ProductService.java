package com.catlogelastic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catlogelastic.entity.Product;
import com.catlogelastic.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getProducts()
	{
		log.info("Inside getProducts of ProductService");
        return productRepository.findAll();
    }
    
	public List<Product> getProduct(int id)
	{
		log.info("Inside getProduct of ProductService");
		return productRepository.findById(id);
    }
    
	public Product addProduct(Product product)
	{
		log.info("Inside addProduct of ProductService");
        return productRepository.save(product);
    }
    
	public Product updateProduct(int id,Product product)
	{
		log.info("Inside updateProduct of ProductService");
        product.setProductId(id);
        return productRepository.save(product);
    }
    
	public void deleteProduct(int id)
	{
		log.info("Inside deleteProduct of ProductService");
        productRepository.deleteById(id);
    }
}