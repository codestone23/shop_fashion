package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.WardDTO;
import com.example.shop_fashion.entities.Ward;
import com.example.shop_fashion.mapper.WardMapper;
import com.example.shop_fashion.repository.WardRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServices implements BaseService<WardDTO> {
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private WardMapper wardMapper;
    @Override
    public Page<WardDTO> getAll(Pageable pageable) {
        Page<Ward> wards = wardRepository.findAll(pageable);
        return wards.map(x -> wardMapper.toDto(x));
    }
    public List<WardDTO> findAll(){
        return wardRepository.findAll().stream().map(x -> wardMapper.toDto(x)).toList();
    }
    public List<WardDTO> findByIdDistrict(Long id){
        return wardRepository.findWardByIdDistrict(id).stream().map(x -> wardMapper.toDto(x)).toList();
    }
    public Ward findById(Long id){
        return wardRepository.findById(id).get();
    }

    @Override
    public boolean save(WardDTO wardDTO) {
        return false;
    }

    @Override
    public boolean update(WardDTO wardDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
