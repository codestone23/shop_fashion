package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.ColorDTO;
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
public class Color {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_color",
            joinColumns = {@JoinColumn(name = "id_color")},
            inverseJoinColumns = {@JoinColumn(name = "id_product")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Product> productSet= new HashSet<>();

    public Color toEntity(ColorDTO colorDTO){
        return Color.builder()
                .id(colorDTO.getId())
                .name(colorDTO.getName())
                .code(colorDTO.getCode())
//                .productSet(Product.toEntitySet(colorDTO.productDTOSet))
                .build();
    }
    public Color toEntityAll(ColorDTO colorDTO){
        return Color.builder()
                .id(colorDTO.getId())
                .name(colorDTO.getName())
                .code(colorDTO.getCode())
                .productSet(Product.toEntitySet(colorDTO.productDTOSet))
                .build();
    }
    public static Set<Color> toEntitySet(Set<ColorDTO> colors) {
        Set<Color> colorSet = new HashSet<>();
        for (ColorDTO colorDTO: colors){
            colorSet.add(new Color().toEntity(colorDTO));
        }
        return colorSet;
    }
    public static Set<Color> toEntitySetAll(Set<ColorDTO> colors) {
        Set<Color> colorSet = new HashSet<>();
        for (ColorDTO colorDTO: colors){
            colorSet.add(new Color().toEntityAll(colorDTO));
        }
        return colorSet;
    }
}
