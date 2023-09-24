package com.example.shop_fashion.entities;

import com.example.shop_fashion.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")}
    )
    private List<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Bill> billSet = new HashSet<>();
    private Date createdAt;
    private Date updatedAt;
    public User toEntity(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .date(userDTO.getDate())
                .gender(userDTO.getGender())
                .address(userDTO.getAddress())
                .numberPhone(userDTO.getNumberPhone())
                .status(userDTO.getStatus())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .build();
    }

}
