package com.example.Eticaret.product;

import com.example.Eticaret.brand.Brand;
import com.example.Eticaret.category.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128,nullable = false,unique = true)
    private String name;

    private float price;

    @Column(name = "main_image")
    private String mainImage;


    @Column(name = "stock")
    private String stock;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;



    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetails> details=new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }



    public void addDetail(String name, String value){

        this.details.add(new ProductDetails(name,value,this));

    }

    public List<ProductDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetails> details) {
        this.details = details;
    }

    public void setDetail(Integer id, String name, String value){
        this.details.add(new ProductDetails(id, name,value,this));

    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Transient
    public String getMainImagePath(){
        if (mainImage==null||id==null) return null;
        return "/product-images/"+id+"/"+mainImage;
    }
}
