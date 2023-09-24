package com.example.shop_fashion.entities;

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
public class District {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "id_city")
    private City city;
    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL)
    private Set<Ward> wardSet = new HashSet<>();
}
