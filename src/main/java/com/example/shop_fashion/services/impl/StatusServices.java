package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.StatusDTO;
import com.example.shop_fashion.entities.Status;
import com.example.shop_fashion.mapper.StatusMapper;
import com.example.shop_fashion.repository.StatusRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServices implements BaseService<StatusDTO> {
    @Autowired
    public StatusRepository statusRepository;
    @Autowired
    public StatusMapper statusMapper;
    @Override
    public Page<StatusDTO> getAll(Pageable pageable) {
        Page<Status> statuses = statusRepository.findAll(pageable);
        return statuses.map(x -> statusMapper.toDto(x));
    }
    public List<StatusDTO> findAll() {
        List<Status> statuses = statusRepository.findAll();
        return statuses.stream().map(x -> statusMapper.toDto(x)).toList();
    }

    @Override
    public boolean save(StatusDTO statusDTO) {
        return false;
    }

    @Override
    public boolean update(StatusDTO statusDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
    public StatusDTO findById(Long id){
        return statusRepository.findById(id).stream().map(x -> statusMapper.toDto(x)).toList().get(0);
    }
}
