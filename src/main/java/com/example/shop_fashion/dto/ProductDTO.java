package com.example.shop_fashion.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String code;
    private int price;
    private int old_price;
    private int quantity;
    private String detail;
    private BrandDTO brand;
    private CategoryDTO category;
    private GenderDTO gender;
    private StatusDTO status;
    private Set<ColorDTO> colors = new HashSet<>();
    private Set<SizeDTO> sizes = new HashSet<>();
    private String img;
    private MultipartFile file;
    private Set<DetailsImgDTO> imgs = new HashSet<>();
    private Set<MultipartFile> files;


}
