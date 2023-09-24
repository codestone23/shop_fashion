package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.CategoryDTO;
import com.example.shop_fashion.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public static CategoryDTO toDto(Category category){
        return CategoryDTO.builder().id(category.getId()).name(category.getName()).details(category.getDetails()).build();
    }
}
