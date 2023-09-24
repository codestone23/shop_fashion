package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.WardDTO;
import com.example.shop_fashion.entities.Ward;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class WardMapper {
    public WardDTO toDto(Ward ward){
        return WardDTO.builder()
                .id(ward.getId())
                .name(ward.getName())
                .build();
    }
    public Set<WardDTO> toDtoAll(Set<Ward> wardSet){
        Set<WardDTO> wardDTOSet = new HashSet<>();
        for (Ward ward:wardSet){
            wardDTOSet.add(toDto(ward));
        }
        return wardDTOSet;
    }
}
