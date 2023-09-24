package com.example.shop_fashion.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartDTO {
    private Long id;
    private ProductDTO productDTO;
    private int quantity;
    private String code_color;
    private String Size;

    public CartDTO(ProductDTO productDTO, int quantity, String code_color, String size) {
        this.productDTO = productDTO;
        this.quantity = quantity;
        this.code_color = code_color;
        Size = size;
    }
}
