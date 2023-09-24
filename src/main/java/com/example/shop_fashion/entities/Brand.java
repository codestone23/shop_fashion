package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.BrandDTO;
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
public class Brand {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    private Set<Product> productSet = new HashSet<>();
    public Brand toEntity(BrandDTO brandDTO){
        return Brand.builder().id(brandDTO.getId()).name(brandDTO.getName()).build();
    }
}
