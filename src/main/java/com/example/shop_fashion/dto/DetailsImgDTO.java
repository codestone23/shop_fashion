package com.example.shop_fashion.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailsImgDTO {
    private Long id;
    private String name;
    ProductDTO productDTO;

    public DetailsImgDTO(String name, ProductDTO productDTO) {
        this.name = name;
        this.productDTO = productDTO;
    }
}
