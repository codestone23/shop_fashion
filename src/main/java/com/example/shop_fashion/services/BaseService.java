package com.example.shop_fashion.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {
    Page<T> getAll(Pageable pageable);
    boolean save(T t);
    boolean update(T t);
    boolean delete(Long id);
}
