package com.marcos.BeerMarketPlace.persistance.services;

import com.marcos.BeerMarketPlace.persistance.model.Product;
import com.marcos.BeerMarketPlace.persistance.repository.ProductRepository;
import com.marcos.BeerMarketPlace.persistance.services.iservice.IProductService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    @Transactional
    public List<Product> getBeers(){

        return productRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {

        Optional<Product> beer = productRepository.findById(id);

        beer.ifPresent(System.out::println);

        return beer;
    }

    @Override
    @Transactional
    public List<Product> findProductByImportation(String importation){

        return productRepository.findByImportation(importation);

    }
    @Override
    @Transactional
    public List<Product> findProductByFamily(String family){

        return productRepository.findByFamily(family);

    }

    @Override
    @Transactional
    public Product save(Product product){  return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Product product, Long id) {
        Optional<Product> o= this.findById(id);
        if(o.isPresent()){
            Product productBdd= o.orElseThrow();
            productBdd.setBeerName(product.getBeerName());
            productBdd.setAlcoholGrade(product.getAlcoholGrade());
            productBdd.setDescription(product.getDescription());
            productBdd.setPrice(product.getPrice());
            productBdd.setQuality(product.getQuality());
            productBdd.setType(product.getType());
            productBdd.setImagePath(product.getImagePath());
            productBdd.setImportation(product.getImportation());
            return Optional.of(this.save(productBdd));
        }
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }


}
