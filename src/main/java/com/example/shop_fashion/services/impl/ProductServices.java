package com.example.shop_fashion.services.impl;

import com.example.shop_fashion.dto.ProductDTO;
import com.example.shop_fashion.entities.Product;
import com.example.shop_fashion.mapper.ProductMapper;
import com.example.shop_fashion.repository.ProductRepository;
import com.example.shop_fashion.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices implements BaseService<ProductDTO> {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<ProductDTO> getAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(x -> productMapper.toDtoAll(x));
    }
    @Override
    public boolean save(ProductDTO productDTO) {
        Product product = new Product().toEntity(productDTO);
        productRepository.save(product);
        return true;
    }
    @Override
    public boolean update(ProductDTO productDTO) {
        Product product = new Product().toEntity(productDTO);
        productRepository.save(product);
        return true;
    }
    @Override
    public boolean delete(Long id) {
        productRepository.deleteById(id);
        return true;
    }
    public ProductDTO findProductByName(String name){
        return productMapper.toDto(productRepository.findProductByName(name));

    }
    public Page<ProductDTO> searchProductByName(String name_search,Pageable pageable){
        return productRepository.searchProductByName(name_search,pageable).map(x -> productMapper.toDtoAll(x));
    }
    public ProductDTO findById(Long id){
        return productRepository.findById(id).stream().map(x -> productMapper.toDtoAll(x)).toList().get(0);
    }
    public ProductDTO findProductByNameAll(String name){
        return productMapper.toDtoAll(productRepository.findProductByName(name));
    }
    public List<ProductDTO> findBS(){
        Pageable pageable = PageRequest.of(0,4);
        List<Product> products = productRepository.findBS(pageable);
        return products.stream().map(x -> productMapper.toDtoAll(x)).toList();
    }
    public List<ProductDTO> findNA(){
        Pageable pageable = PageRequest.of(0,4);
        List<Product> products = productRepository.findNA(pageable);
        return products.stream().map(x -> productMapper.toDtoAll(x)).toList();
    }
    public List<ProductDTO> findHS(){
        Pageable pageable = PageRequest.of(0,4);
        List<Product> products = productRepository.findHS(pageable);
        return products.stream().map(x -> productMapper.toDtoAll(x)).toList();
    }
}
