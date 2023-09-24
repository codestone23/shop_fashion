package com.example.shop_fashion.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bill {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @ManyToOne()
    @JoinColumn(name = "id_client")
    private Client client;
    private Date date;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "bill_product",
//            joinColumns = {@JoinColumn(name = "id_bill")},
//            inverseJoinColumns = {@JoinColumn(name = "id_product")}
//    )
//    private Set<Product> productSet = new HashSet<>();
    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();
    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;

}
