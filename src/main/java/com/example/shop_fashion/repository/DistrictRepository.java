package com.example.shop_fashion.repository;

import com.example.shop_fashion.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    @Query("select d from District d where d.city.id = :id_city")
    List<District> findDistrictByIdCity(Long id_city);
}
