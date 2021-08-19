package com.catlogelastic.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.catlogelastic.entity.Product;

@Repository
public interface ProductRepository {

	List<Product> findAll();

	List<Product> findById(int id);

	Product save(Product product);

	void deleteById(int id);

}