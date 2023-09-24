package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.StatusDTO;
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
public class Status {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL)
    private Set<Product> productSet = new HashSet<>();
    public Status toEntity(StatusDTO statusDTO){
        return Status.builder()
                .id(statusDTO.getId())
                .name(statusDTO.getName())
                .build();
    }

}
