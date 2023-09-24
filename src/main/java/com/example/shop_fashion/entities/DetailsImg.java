package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.DetailsImgDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailsImg {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "id_product")
    private Product product;

    public DetailsImg(String name) {
        this.name = name;
    }
    public DetailsImg toEntity(DetailsImgDTO detailsImgDTO){
        return DetailsImg.builder()
                .id(detailsImgDTO.getId())
                .name(detailsImgDTO.getName())
                .product(new Product().toEntityAll(detailsImgDTO.getProductDTO()))
                .build();
    }
    public static Set<DetailsImg> toEntitySet(Set<DetailsImgDTO> detailsImgDTOSet){
        Set<DetailsImg> detailsImgs = new HashSet<>();
        for(DetailsImgDTO detailsImgDTO: detailsImgDTOSet){
            detailsImgs.add(new DetailsImg().toEntity(detailsImgDTO));
        }
        return detailsImgs;
    }
}
