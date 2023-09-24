package com.example.shop_fashion.security;

import com.example.shop_fashion.entities.Role;
import com.example.shop_fashion.entities.User;
import com.example.shop_fashion.services.impl.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserServices userServices;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServices.findByUsername(username);
        if (user != null){
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }
        return null;
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities (Collection<Role> roles){
        Collection<? extends GrantedAuthority > mapRole = roles.stream().
                map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return mapRole;
    }
}
