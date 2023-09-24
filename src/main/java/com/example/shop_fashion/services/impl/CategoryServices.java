package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.CategoryDTO;
import com.example.shop_fashion.entities.Category;
import com.example.shop_fashion.mapper.CategoryMapper;
import com.example.shop_fashion.repository.CategoryRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices implements BaseService<CategoryDTO> {
    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public CategoryMapper categoryMapper;
    @Override
    public Page<CategoryDTO> getAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(x -> categoryMapper.toDto(x));
    }
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(x -> categoryMapper.toDto(x)).toList();
    }

    @Override
    public boolean save(CategoryDTO categoryDTO) {
        return false;
    }

    @Override
    public boolean update(CategoryDTO categoryDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
    public CategoryDTO findById(Long id){
        return categoryRepository.findById(id).stream().map(x -> categoryMapper.toDto(x)).toList().get(0);
    }
}
