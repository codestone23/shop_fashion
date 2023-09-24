package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.CartDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cart {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "id_product")
    private Product product;
    private int quantity;
    private String code_color;
    private String size;

    @ManyToOne()
    @JoinColumn(name = "id_bill")
    private Bill bill;

    public Cart(Product product, int quantity, String code_color, String size, Bill bill) {
        this.product = product;
        this.quantity = quantity;
        this.code_color = code_color;
        this.size = size;
        this.bill = bill;
    }
    public Cart toEntity(CartDTO cartDTO){
        return Cart.builder()
                .id(null)
                .bill(null)
                .product(new Product().toEntity(cartDTO.getProductDTO()))
                .code_color(cartDTO.getCode_color())
                .quantity(cartDTO.getQuantity())
                .size(cartDTO.getSize()).build();
    }
}
