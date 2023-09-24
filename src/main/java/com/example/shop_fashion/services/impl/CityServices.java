package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.CityDTO;
import com.example.shop_fashion.entities.City;
import com.example.shop_fashion.mapper.CityMapper;
import com.example.shop_fashion.repository.CityRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServices implements BaseService<CityDTO> {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityMapper cityMapper;
    @Override
    public Page<CityDTO> getAll(Pageable pageable) {
        Page<City> cities = cityRepository.findAll(pageable);
        return cities.map(x -> cityMapper.toDto(x));
    }
    public List<CityDTO> findAll(){
        return cityRepository.findAll().stream().map(x -> cityMapper.toDto(x)).toList();
    }
    public CityDTO findById(Long id){
        return cityRepository.findById(id).stream().map(x -> cityMapper.toDto(x)).toList().get(0);
    }

    @Override
    public boolean save(CityDTO cityDTO) {
        return false;
    }

    @Override
    public boolean update(CityDTO cityDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
