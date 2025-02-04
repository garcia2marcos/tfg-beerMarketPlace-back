package com.marcos.BeerMarketPlace.persistance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Enabled;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "beers")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "beername", nullable = false)
    private String beerName;

    @Column(name = "alcoholgrade")
    private Double alcoholGrade; // Cambiado a Double

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Double price; // Cambiado a Double

    @Column(name = "quality")
    private Integer quality; // Cambiado a Integer

    @Column(name = "importation")
    private String importation;

    @Column(name = "description")
    private String description;

    @Column(name = "imagepath")
    private String imagePath;

    @Column(name="origin")
    private String origin ;

    @Column(name="family")
    private String family;

    @Column(name="style")
    private String style;

    @Column(name="substyle")
    private String substyle;

    @Column(name="graduation")
    private String graduation;

    @Column(name="ingredients")
    private String ingredients;

    @Column(name="allergens")
    private String allergens ;

    @Column(name="category")
    private String category;

    @Column(name="color")
    private String color;

    @Column(name="tone")
    private String tone;

    @Column(name="format")
    private String format;

    @Column(name="model")
    private String model;


}