package com.example.shop_fashion.repository;

import com.example.shop_fashion.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward,Long> {
    @Query("select w from Ward w where w.district.id = :id_district")
    List<Ward> findWardByIdDistrict(Long id_district);
}
