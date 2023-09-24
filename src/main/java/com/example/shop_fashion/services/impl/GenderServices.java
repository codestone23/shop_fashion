package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.GenderDTO;
import com.example.shop_fashion.entities.Gender;
import com.example.shop_fashion.mapper.GenderMapper;
import com.example.shop_fashion.repository.GenderRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServices implements BaseService<GenderDTO> {
    @Autowired
    public GenderRepository genderRepository;
    @Autowired
    public GenderMapper genderMapper;

    @Override
    public Page<GenderDTO> getAll(Pageable pageable) {
        Page<Gender> genders = genderRepository.findAll(pageable);
        return genders.map(x -> genderMapper.toDto(x));
    }
    public List<GenderDTO> findAll() {
        List<Gender> genders = genderRepository.findAll();
        return genders.stream().map(x -> genderMapper.toDto(x)).toList();
    }

    @Override
    public boolean save(GenderDTO genderDTO) {
        return false;
    }

    @Override
    public boolean update(GenderDTO genderDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
    public GenderDTO findById(Long id){
        return genderRepository.findById(id).stream().map(x -> genderMapper.toDto(x)).toList().get(0);
    }
}
