package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.GenderDTO;
import com.example.shop_fashion.entities.Gender;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {
    public static GenderDTO toDto(Gender gender){
        return GenderDTO.builder().id(gender.getId()).name(gender.getName()).build();
    }
}
