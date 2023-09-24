package com.example.shop_fashion.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColorDTO {
    private Long id;
    private String name;
    private String code;
    public Set<ProductDTO> productDTOSet = new HashSet<>();
}
