package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.ColorDTO;
import com.example.shop_fashion.entities.Color;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ColorMapper {
    public ColorDTO toDto(Color color){
        return ColorDTO.builder()
                .id(color.getId())
                .code(color.getCode())
                .name(color.getName())
                .build();
    }
    public static ColorDTO toDtoAll(Color color){
        return ColorDTO.builder()
                .id(color.getId())
                .code(color.getCode())
                .name(color.getName())
                .productDTOSet(ProductMapper.toDtoSetAll(color.getProductSet()))
                .build();
    }
    public static Set<ColorDTO> toDtoSet(Set<Color> colorSet){
        Set<ColorDTO> colorDTOSet = new HashSet<>();
        for(Color color:colorSet){
            colorDTOSet.add(new ColorMapper().toDto(color));
        }
        return colorDTOSet;
    }
    public static Set<ColorDTO> toDtoSetAll(Set<Color> colorSet){
        Set<ColorDTO> colorDTOSet = new HashSet<>();
        for(Color color:colorSet){
            colorDTOSet.add(toDtoAll(color));
        }
        return colorDTOSet;
    }
}
