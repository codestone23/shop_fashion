package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.DistrictDTO;
import com.example.shop_fashion.entities.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DistrictMapper {
    @Autowired
    private WardMapper wardMapper;
    public DistrictDTO toDto(District district){
        return DistrictDTO.builder()
                .id(district.getId())
                .name(district.getName())
//                .wardSet(wardMapper.toDtoAll(district.getWardSet()))
                .build();
    }
    public Set<DistrictDTO> toDtoAll(Set<District> districts){
        Set<DistrictDTO> districtDTOS = new HashSet<>();
        for (District district:districts){
            districtDTOS.add(toDto(district));
        }
        return districtDTOS;
    }
}
