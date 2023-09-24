package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.BrandDTO;
import com.example.shop_fashion.entities.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    public static BrandDTO toDto(Brand brand){
        return BrandDTO.builder().id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
