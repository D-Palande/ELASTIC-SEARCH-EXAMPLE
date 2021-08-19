package com.catlogelastic.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.catlogelastic.entity.Category;

@Repository
public interface CategoryRepository {

	List<Category> findAll();

	List<Category> findById(int id);

	Category save(Category category);

	void deleteById(int id);
}
