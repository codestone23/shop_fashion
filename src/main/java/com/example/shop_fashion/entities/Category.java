package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.CategoryDTO;
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
public class Category {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String details;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> productSet = new HashSet<>();
    public Category toEntity(CategoryDTO categoryDTO){
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .details(categoryDTO.getDetails())
                .build();
    }
}
