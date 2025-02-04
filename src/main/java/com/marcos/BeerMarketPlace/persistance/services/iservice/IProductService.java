package com.marcos.BeerMarketPlace.persistance.services.iservice;

import com.marcos.BeerMarketPlace.persistance.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    public List<Product> getBeers();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Product product,Long id);

    void remove (Long id);

    public List<Product> findProductByImportation(String importation);

    public List<Product> findProductByFamily(String family);
}
