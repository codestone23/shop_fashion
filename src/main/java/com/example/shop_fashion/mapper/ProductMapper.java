package com.example.shop_fashion.mapper;

import com.example.shop_fashion.dto.ProductDTO;
import com.example.shop_fashion.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProductMapper {
    public static ProductDTO toDto(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .price(product.getPrice())
                .old_price(product.getOld_price())
                .quantity(product.getQuantity())
                .detail(product.getDetail())
                .brand(BrandMapper.toDto(product.getBrand()))
                .category(CategoryMapper.toDto(product.getCategory()))
                .gender(GenderMapper.toDto(product.getGender()))
                .status(StatusMapper.toDto(product.getStatus()))
                .colors(ColorMapper.toDtoSet(product.getColorSet()))
                .sizes(SizeMapper.toDtoSet(product.getSizeSet()))
                .img(product.getImg())
                .build();
    }
    public static Set<ProductDTO> toDtoSet(Set<Product> productSet){
        Set<ProductDTO> productDTOSet = new HashSet<>();
        for(Product product:productSet){
            productDTOSet.add(toDto(product));
        }
        return productDTOSet;
    }
    public static ProductDTO toDtoAll(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .price(product.getPrice())
                .old_price(product.getOld_price())
                .quantity(product.getQuantity())
                .detail(product.getDetail())
                .brand(BrandMapper.toDto(product.getBrand()))
                .category(CategoryMapper.toDto(product.getCategory()))
                .gender(GenderMapper.toDto(product.getGender()))
                .status(StatusMapper.toDto(product.getStatus()))
                .colors(ColorMapper.toDtoSet(product.getColorSet()))
                .sizes(SizeMapper.toDtoSet(product.getSizeSet()))
                .img(product.getImg())
                .imgs(DetailsImgMapper.toDtoSet(product.getDetailsImgs()))
                .build();
    }
    public static Set<ProductDTO> toDtoSetAll(Set<Product> productSet){
        Set<ProductDTO> productDTOSet = new HashSet<>();
        for(Product product:productSet){
            productDTOSet.add(toDtoAll(product));
        }
        return productDTOSet;
    }
}
