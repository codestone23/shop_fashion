package com.example.shop_fashion.dto;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BillDTO {
    private Long id;
    private String code;
    private ClientDTO clientDTO;
    private Date date;
    private Set<CartDTO> cartDTOSet = new HashSet<>();
}
