package com.example.shop_fashion.repository;

import com.example.shop_fashion.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.name like :name")
    Product findProductByName(String name);
    @Query("select p from Product p where p.name like %:name%")
    Page<Product> searchProductByName(String name,Pageable pageable);
    @Query("select p from Product p where p.status.id = 5")
    List<Product> findBS(Pageable pageable);
    @Query("select p from Product p where p.status.id = 1")
    List<Product> findHS(Pageable pageable);
    @Query("select p from Product p where p.status.id = 2")
    List<Product> findNA(Pageable pageable);
}
