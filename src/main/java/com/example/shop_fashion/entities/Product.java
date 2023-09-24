package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.ProductDTO;
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
public class Product {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private int price;
    private int old_price;
    private int quantity;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String detail;
    private String img;
    @ManyToOne()
    @JoinColumn(name = "id_status")
    private Status status;
    @ManyToOne()
    @JoinColumn(name = "id_category")
    private Category category;
    @ManyToOne()
    @JoinColumn(name = "id_brand")
    private Brand brand;
    @ManyToOne()
    @JoinColumn(name = "id_gender")
    private Gender gender;
    @ManyToMany(mappedBy = "productSet")
    private Set<Size> sizeSet = new HashSet<>();
    @ManyToMany(mappedBy = "productSet")

    private Set<Color> colorSet = new HashSet<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<DetailsImg> detailsImgs = new HashSet<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .code(productDTO.getCode())
                .price(productDTO.getPrice())
                .old_price(productDTO.getOld_price())
                .quantity(productDTO.getQuantity())
                .detail(productDTO.getDetail())
                .brand(new Brand().toEntity(productDTO.getBrand()))
                .category(new Category().toEntity(productDTO.getCategory()))
                .gender(new Gender().toEntity(productDTO.getGender()))
                .status(new Status().toEntity(productDTO.getStatus()))
                .colorSet(Color.toEntitySet(productDTO.getColors()))
                .sizeSet(Size.toEntitySet(productDTO.getSizes()))
                .img(productDTO.getImg())
                .detailsImgs(DetailsImg.toEntitySet(productDTO.getImgs()))
                .build();
    }
    public Product toEntityAll(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .code(productDTO.getCode())
                .price(productDTO.getPrice())
                .old_price(productDTO.getOld_price())
                .quantity(productDTO.getQuantity())
                .detail(productDTO.getDetail())
                .brand(new Brand().toEntity(productDTO.getBrand()))
                .category(new Category().toEntity(productDTO.getCategory()))
                .gender(new Gender().toEntity(productDTO.getGender()))
                .status(new Status().toEntity(productDTO.getStatus()))
                .colorSet(Color.toEntitySet(productDTO.getColors()))
                .sizeSet(Size.toEntitySet(productDTO.getSizes()))
                .img(productDTO.getImg())
                .build();
    }
    public static Set<Product> toEntitySet(Set<ProductDTO> productDTOSet) {
        Set<Product> productSet = new HashSet<>();
        for(ProductDTO productDTO:productDTOSet){
            productSet.add(new Product().toEntity(productDTO));
        }
        return productSet;
    }

}
