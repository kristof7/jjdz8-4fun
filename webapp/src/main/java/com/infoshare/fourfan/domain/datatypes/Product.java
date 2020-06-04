package com.infoshare.fourfan.domain.datatypes;

public class Product {
    String name;
    String brand;
    //Cena reprezentowana w groszach
    Integer price;
    Integer calories;
    Shop shop;
    ProductCategory productCategory;
    Long id;

    public Product(String name, String brand, Integer price, Integer calories, Shop shop, ProductCategory productCategory) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.calories = calories;
        this.shop = shop;
        this.productCategory = productCategory;
    }

    public Product(){

    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getCalories() {
        return calories;
    }

    public Shop getShop() {
        return shop;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString(){
        return name + " " + brand + " " + price + " " + calories + " " + productCategory;

    }
}
