package com.example.Eticaret.brand;

import com.example.Eticaret.category.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length =45,nullable = false,unique = true)
    private String name;

    @Column(length = 45, nullable = true)
    private String logo;

    @OneToMany
    @JoinColumn(name = "brand_id")
    private List<Category> categories=new ArrayList<>();


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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Transient
    public String getLogoImagePath(){
        if (logo==null||id==null) return null;
        return "/brand-logos/"+id+"/"+logo;
    }
}
