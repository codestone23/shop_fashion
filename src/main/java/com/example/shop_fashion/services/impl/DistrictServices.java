package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.DistrictDTO;
import com.example.shop_fashion.entities.District;
import com.example.shop_fashion.mapper.DistrictMapper;
import com.example.shop_fashion.repository.DistrictRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServices implements BaseService<DistrictDTO> {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public Page<DistrictDTO> getAll(Pageable pageable) {
        Page<District> districts = districtRepository.findAll(pageable);
        return districts.map(x -> districtMapper.toDto(x));
    }
    public List<DistrictDTO> findAll(){
        return districtRepository.findAll().stream().map(x -> districtMapper.toDto(x)).toList();
    }
    public DistrictDTO findById(Long id){
        return districtRepository.findById(id).stream().map(x -> districtMapper.toDto(x)).toList().get(0);
    }
    public List<DistrictDTO> findByIdCity(Long id){
        return districtRepository.findDistrictByIdCity(id).stream().map(x -> districtMapper.toDto(x)).toList();
    }

    @Override
    public boolean save(DistrictDTO districtDTO) {
        return false;
    }

    @Override
    public boolean update(DistrictDTO districtDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
