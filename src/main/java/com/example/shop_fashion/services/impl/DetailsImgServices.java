package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.DetailsImgDTO;
import com.example.shop_fashion.entities.DetailsImg;
import com.example.shop_fashion.repository.DetailsImgRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DetailsImgServices implements BaseService<DetailsImgDTO> {
    @Autowired
    public DetailsImgRepository detailsImgRepository;
    @Override
    public Page<DetailsImgDTO> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public boolean save(DetailsImgDTO detailsImgDTO) {
        detailsImgRepository.save(new DetailsImg().toEntity(detailsImgDTO));
        return true;
    }

    @Override
    public boolean update(DetailsImgDTO detailsImgDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        detailsImgRepository.deleteById(id);
        return true;
    }
}
