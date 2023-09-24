package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.ColorDTO;
import com.example.shop_fashion.entities.Color;
import com.example.shop_fashion.mapper.ColorMapper;
import com.example.shop_fashion.repository.ColorRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServices implements BaseService<ColorDTO> {
    @Autowired
    public ColorRepository colorRepository;
    @Autowired
    public ColorMapper colorMapper;
    @Override
    public Page<ColorDTO> getAll(Pageable pageable) {
        Page<Color> colors = colorRepository.findAll(pageable);
        return colors.map(x -> colorMapper.toDto(x));
    }
    public List<ColorDTO> findAll() {
        List<Color> colors = colorRepository.findAll();
        return colors.stream().map(x -> colorMapper.toDto(x)).toList();
    }

    @Override
    public boolean save(ColorDTO colorDTO) {
        return false;
    }

    @Override
    public boolean update(ColorDTO colorDTO) {
        colorRepository.save(new Color().toEntityAll(colorDTO));
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public ColorDTO findById(Long id){
        return colorRepository.findById(id).stream().map(ColorMapper::toDtoAll).toList().get(0);
    }
}
