package com.example.shop_fashion.services;

import com.example.shop_fashion.dto.UserDTO;
import com.example.shop_fashion.entities.User;

import java.util.List;

public interface IUserService {
    User findByUsername(String username);
    User findByEmail(String email);
    void saveUser(UserDTO userDTO);
    List<UserDTO> findAllUser();
}
