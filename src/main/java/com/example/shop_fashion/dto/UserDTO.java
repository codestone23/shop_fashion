package com.example.shop_fashion.dto;

import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Date date;
    private String gender;
    private String address;
    private String numberPhone;
    private int status;
    private Date createdAt;
    private Date updatedAt;
}
