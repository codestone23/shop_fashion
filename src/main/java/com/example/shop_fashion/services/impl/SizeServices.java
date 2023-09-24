package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.SizeDTO;
import com.example.shop_fashion.entities.Size;
import com.example.shop_fashion.mapper.SizeMapper;
import com.example.shop_fashion.repository.SizeRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServices implements BaseService<SizeDTO> {
    @Autowired
    public SizeRepository sizeRepository;
    @Autowired
    public SizeMapper sizeMapper;
    @Override
    public Page<SizeDTO> getAll(Pageable pageable) {
        Page<Size> sizes = sizeRepository.findAll(pageable);
        return sizes.map(SizeMapper::toDto);
    }
    public List<SizeDTO> findAll() {
        List<Size> sizes= sizeRepository.findAll();
        return sizes.stream().map(x -> sizeMapper.toDto(x)).toList();
    }

    @Override
    public boolean save(SizeDTO sizeDTO) {
        return false;
    }

    @Override
    public boolean update(SizeDTO sizeDTO) {
        sizeRepository.save(new Size().toEntityAll(sizeDTO));
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
    public SizeDTO findById(Long id){
        return sizeRepository.findById(id).stream().map(SizeMapper::toDtoAll).toList().get(0);
    }
}
