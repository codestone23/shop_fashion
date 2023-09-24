package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.UserDTO;
import com.example.shop_fashion.entities.Role;
import com.example.shop_fashion.entities.User;
import com.example.shop_fashion.repository.RoleRepository;
import com.example.shop_fashion.repository.UserRepository;
import com.example.shop_fashion.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
public class UserServices implements IUserService {
    private final UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        String password_hash = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password_hash);
        userDTO.setStatus(1);
        userDTO.setCreatedAt(new Date());
        userDTO.setUpdatedAt(new Date());
        User user = new User().toEntity(userDTO);
        Role role = roleRepository.findByName("USER");
        if (role == null){
            role = checkExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    public Role checkExist(){
        Role role = new Role();
        role.setName("USER");
        return roleRepository.save(role);
    }

    @Override
    public List<UserDTO> findAllUser() {
        return null;
    }
}
