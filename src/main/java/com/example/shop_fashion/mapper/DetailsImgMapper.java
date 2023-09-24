package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.DetailsImgDTO;
import com.example.shop_fashion.entities.DetailsImg;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DetailsImgMapper {
    public static DetailsImgDTO toDto(DetailsImg detailsImg){
        return DetailsImgDTO.builder()
                .id(detailsImg.getId())
                .name(detailsImg.getName())
                .productDTO(ProductMapper.toDto(detailsImg.getProduct()))
                 .build();
    }
    public static Set<DetailsImgDTO> toDtoSet(Set<DetailsImg> detailsImgSet){
        Set<DetailsImgDTO> detailsImgDTOSet = new HashSet<>();
        for(DetailsImg detailsImg: detailsImgSet){
            detailsImgDTOSet.add(toDto(detailsImg));
        }
        return detailsImgDTOSet;
    }
}
