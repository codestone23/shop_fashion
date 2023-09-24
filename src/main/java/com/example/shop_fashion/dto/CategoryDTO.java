package com.example.shop_fashion.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private String details;
}
