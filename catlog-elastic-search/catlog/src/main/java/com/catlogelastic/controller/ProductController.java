
package com.catlogelastic.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catlogelastic.entity.Product;
import com.catlogelastic.service.ProductService;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;
	 
	@GetMapping
	public List<Product> getProducts(){
		log.info("Inside getProducts of ProductController");
		 return productService.getProducts();
	}
	
	@GetMapping("/{id}")
	public List<Product> getProduct(@PathVariable int id)
	{
		log.info("Inside getProduct of ProductController");
		return productService.getProduct(id);
	}
	    
	@PostMapping
	public Product addProduct(@RequestBody Product product)
	{
		log.info("Inside addProduct of ProductController");
		return productService.addProduct(product);
	}
	    
	@PutMapping("/{id}")
	public Product addProduct(@PathVariable int id,@RequestBody Product product)
	{
		log.info("Inside addProduct of ProductController");
        return productService.updateProduct(id,product);
	}
	   
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id)
	{
		log.info("Inside deleteProduct of ProductController");
		productService.deleteProduct(id);
	}
}