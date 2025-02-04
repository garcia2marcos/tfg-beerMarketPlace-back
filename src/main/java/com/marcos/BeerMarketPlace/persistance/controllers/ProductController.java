package com.marcos.BeerMarketPlace.persistance.controllers;

import com.marcos.BeerMarketPlace.persistance.model.Product;
import com.marcos.BeerMarketPlace.persistance.model.User;
import com.marcos.BeerMarketPlace.persistance.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping()
    public List<Product> beerList(){
        return productService.getBeers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);

        if (productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/importation/{importation}")
    public ResponseEntity<?> findProductByCountry(@PathVariable String importation){
        List<Product> productImportation = productService.findProductByImportation(importation);

        if (productImportation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productImportation);
    }

    @GetMapping("/family/{family}")
    public ResponseEntity<?> findProductByFamily(@PathVariable String family){
        List<Product> productImportation = productService.findProductByFamily(family);

        if (productImportation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productImportation);
    }

    @PostMapping
    public ResponseEntity<?> create( @RequestBody Product product, BindingResult result){
        System.out.println("Producto recibido: " + product);
        if (result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @RequestBody Product product, BindingResult result,@PathVariable Long id){
        if (result.hasErrors()){
            return validation(result);
        }
        Optional<Product> o= productService.update(product,id);
        if(o.isPresent()){

            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Product> o= productService.findById(id);

        if(o.isPresent()) {

            productService.remove(id);
            return ResponseEntity.noContent().build();//204
        }

        return ResponseEntity.noContent().build();//204

    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(er ->{
            errors.put(er.getField(),"El campo "+ er.getField()+" "+er.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
