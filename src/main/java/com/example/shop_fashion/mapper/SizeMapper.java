package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.SizeDTO;
import com.example.shop_fashion.entities.Size;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SizeMapper {
    public static SizeDTO toDto(Size size){
        return SizeDTO.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }
    public static SizeDTO toDtoAll(Size size){
        return SizeDTO.builder()
                .id(size.getId())
                .name(size.getName())
                .productDTOSet(ProductMapper.toDtoSetAll(size.getProductSet()))
                .build();
    }
    public static Set<SizeDTO> toDtoSet(Set<Size> sizeSet){
        Set<SizeDTO> sizeDTOSet = new HashSet<>();
        for(Size size:sizeSet){
            sizeDTOSet.add(toDto(size));
        }
        return sizeDTOSet;
    }
    public static Set<SizeDTO> toDtoSetAll(Set<Size> sizeSet){
        Set<SizeDTO> sizeDTOSet = new HashSet<>();
        for(Size size:sizeSet){
            sizeDTOSet.add(toDtoAll(size));
        }
        return sizeDTOSet;
    }
}
