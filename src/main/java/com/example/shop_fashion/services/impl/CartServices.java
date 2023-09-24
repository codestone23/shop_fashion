package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.CartDTO;
import com.example.shop_fashion.entities.Cart;
import com.example.shop_fashion.repository.CartRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartServices implements BaseService<CartDTO> {
    @Autowired
    public CartRepository cartRepository;
    @Override
    public Page<CartDTO> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public boolean save(CartDTO cartDTO) {
        return false;
    }
    public Cart saveOrReturn(Cart cart){
        return cartRepository.save(cart);
    }

    @Override
    public boolean update(CartDTO cartDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
