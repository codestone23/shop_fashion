package com.example.shop_fashion.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CityDTO {
    private Long id;
    private String name;
//    private Set<DistrictDTO> districts = new HashSet<>();
}
