package com.example.shop_fashion.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DistrictDTO {
    private Long id;
    private String name;
//    private Set<WardDTO> wardSet = new HashSet<>();
}
