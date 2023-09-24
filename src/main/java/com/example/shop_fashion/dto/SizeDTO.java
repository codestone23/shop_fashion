package com.example.shop_fashion.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SizeDTO {
    private Long id;
    private String name;
    private Set<ProductDTO> productDTOSet = new HashSet<>();
}
