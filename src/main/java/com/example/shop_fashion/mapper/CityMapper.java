package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.CityDTO;
import com.example.shop_fashion.entities.City;
import org.springframework.stereotype.Component;


@Component
public class CityMapper {
    public CityDTO toDto(City city){
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
//                .districts(districtMapper.toDtoAll(city.getDistricts()))
                .build();
    }
}
