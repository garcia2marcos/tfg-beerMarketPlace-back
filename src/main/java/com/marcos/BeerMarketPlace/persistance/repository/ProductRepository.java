package com.marcos.BeerMarketPlace.persistance.repository;

import com.marcos.BeerMarketPlace.persistance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from beers",nativeQuery = true)
    List<Product> findProducts();

    @Query("SELECT p FROM Product p WHERE p.importation = :importation")
    List<Product> findByImportation(@Param("importation") String importation);

    @Query("SELECT p FROM Product p WHERE p.family = :family")
    List<Product> findByFamily(@Param("family") String family);



}
