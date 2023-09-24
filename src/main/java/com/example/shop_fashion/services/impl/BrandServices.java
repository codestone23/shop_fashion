package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.BrandDTO;
import com.example.shop_fashion.entities.Brand;
import com.example.shop_fashion.mapper.BrandMapper;
import com.example.shop_fashion.repository.BrandRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServices implements BaseService<BrandDTO> {
    @Autowired
    public BrandRepository brandRepository;
    @Autowired
    public BrandMapper brandMapper;
    @Override
    public Page<BrandDTO> getAll(Pageable pageable) {
        Page<Brand> brands = brandRepository.findAll(pageable);
        return brands.map(x -> brandMapper.toDto(x));
    }
    public List<BrandDTO> findAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream().map(x -> brandMapper.toDto(x)).toList();
    }

    @Override
    public boolean save(BrandDTO brandDTO) {
        return false;
    }

    @Override
    public boolean update(BrandDTO brandDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
    public BrandDTO findById(Long id){
        return brandRepository.findById(id).stream().map(x -> brandMapper.toDto(x)).toList().get(0);
    }
}
