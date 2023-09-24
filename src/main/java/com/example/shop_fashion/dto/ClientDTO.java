package com.example.shop_fashion.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientDTO {
    private Long id;
    private String name;
    private String address;
    private String phone_number;
    private String email;
}
