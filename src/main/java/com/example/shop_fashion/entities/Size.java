package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.SizeDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
public class Size {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "size_product",
            joinColumns = {@JoinColumn(name = "id_size")},
            inverseJoinColumns = {@JoinColumn(name = "id_product")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Product> productSet = new HashSet<>();
    public Size toEntity(SizeDTO sizeDTO){
        return Size.builder()
                .id(sizeDTO.getId())
                .name(sizeDTO.getName())
//                .productSet(Product.toEntitySet(sizeDTO.getProductDTOSet()))
                .build();
    }
    public static Set<Size> toEntitySet(Set<SizeDTO> sizeDTOS){
        Set<Size> sizes = new HashSet<>();
        for(SizeDTO sizeDTO:sizeDTOS){
            sizes.add(new Size().toEntity(sizeDTO));
        }
        return sizes;
    }
    public Size toEntityAll(SizeDTO sizeDTO){
        return Size.builder()
                .id(sizeDTO.getId())
                .name(sizeDTO.getName())
                .productSet(Product.toEntitySet(sizeDTO.getProductDTOSet()))
                .build();
    }
    public static Set<Size> toEntitySetAll(Set<SizeDTO> sizeDTOS){
        Set<Size> sizes = new HashSet<>();
        for(SizeDTO sizeDTO:sizeDTOS){
            sizes.add(new Size().toEntityAll(sizeDTO));
        }
        return sizes;
    }

}
