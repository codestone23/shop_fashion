package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.StatusDTO;
import com.example.shop_fashion.entities.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public static StatusDTO toDto(Status status){
        return StatusDTO.builder().id(status.getId()).name(status.getName()).build();
    }
}
