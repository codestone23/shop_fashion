package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.BillDTO;
import com.example.shop_fashion.entities.Bill;
import com.example.shop_fashion.repository.BillRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillServices implements BaseService<BillDTO> {
    @Autowired
    private BillRepository billRepository;
    @Override
    public Page<BillDTO> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public boolean save(BillDTO billDTO) {
        return false;
    }
    public Bill saveOrReturn(Bill bill){
        return billRepository.save(bill);
    }

    @Override
    public boolean update(BillDTO billDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
