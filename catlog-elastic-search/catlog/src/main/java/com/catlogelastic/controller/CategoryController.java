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

import com.catlogelastic.entity.Category;
import com.catlogelastic.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

	@Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories()
    {
    	log.info("Inside getCategories of CategoryController");
        return categoryService.getCategories();
    }
    
    @GetMapping("/{id}")
    public List<Category>  getCategory(@PathVariable int id)
    {
    	log.info("Inside getCategory of CategoryController");
        return categoryService.getCategoryById(id);
    }
    
    @PostMapping
    public Category addCategory(@RequestBody Category category)
    {
    	log.info("Inside addCategory of CategoryController");
        return categoryService.addCategory(category);
    }
    
    @PutMapping("/{id}")
    public Category addCategory(@PathVariable int id,@RequestBody Category category)
    {
    	log.info("Inside addCategory of CategoryController");
        return categoryService.updateCategory(id,category);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id)
    {
    	log.info("Inside deleteCategory of CategoryController");
        categoryService.deleteCategory(id);
    }
}