package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.GenderDTO;
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
public class Gender {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "gender",cascade = CascadeType.ALL)
    private Set<Product> productSet= new HashSet<>();
    public Gender toEntity(GenderDTO genderDTO){
        return Gender.builder()
                .id(genderDTO.getId())
                .name(genderDTO.getName())
                .build();
    }
}
