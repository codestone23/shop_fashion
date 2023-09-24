package com.example.shop_fashion.repository;

import com.example.shop_fashion.entities.DetailsImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsImgRepository extends JpaRepository<DetailsImg,Long> {
}
