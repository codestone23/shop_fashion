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
public class Client {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone_number;
    private String email;
    @ManyToOne()
    @JoinColumn(name = "id_ward")
    private Ward ward;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private Set<Bill> bills = new HashSet<>();
}
