package com.catlogelastic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catlogelastic.entity.Category;
import com.catlogelastic.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

    public List<Category> getCategories()
    {
    	log.info("Inside getCategories of CategoryService");
        return categoryRepository.findAll();
    }
    
    public List<Category> getCategoryById(int id)
    {
    	log.info("Inside getCategory of CategoryService");
    	return categoryRepository.findById(id);
        
    }
    
    public Category addCategory(Category category)
    {
    	log.info("Inside addCategory of CategoryService");
        return categoryRepository.save(category);
    }
    
    public Category updateCategory(int id,Category category)
    {
    	log.info("Inside updateCategory of CategoryService");
        category.setCategoryId(id);
        return categoryRepository.save(category);
    }
    
    public void deleteCategory(int id)
    {
    	log.info("Inside deleteCategory of CategoryService");
        categoryRepository.deleteById(id);
    }	
}