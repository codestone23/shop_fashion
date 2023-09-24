package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.ClientDTO;
import com.example.shop_fashion.entities.Client;
import com.example.shop_fashion.repository.ClientRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServices implements BaseService<ClientDTO> {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Page<ClientDTO> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public boolean save(ClientDTO clientDTO) {
        return false;
    }
    public Client saveEntity(Client client){
        return clientRepository.save(client);
    }

    @Override
    public boolean update(ClientDTO clientDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
