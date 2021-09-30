package com.example.Eticaret.Shopping;

import com.example.Eticaret.product.Product;

import javax.persistence.*;

@Entity
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =45,nullable = false)
    private String serino;

    @Column(length =45,nullable = false)
    private String email;

    @Column(length =15,nullable = false,unique = true)
    private String telno;

    @Column(length =225,nullable = false)
    private String address;

    @Column(length =225)
    private String description;

    @Column(length =10,nullable = false)
    private String stocknumber;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStocknumber() {
        return stocknumber;
    }

    public void setStocknumber(String stocknumber) {
        this.stocknumber = stocknumber;
    }

    public String getSerino() {
        return serino;
    }

    public void setSerino(String serino) {
        this.serino = serino;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "id=" + id +
                ", serino='" + serino + '\'' +
                ", email='" + email + '\'' +
                ", telno='" + telno + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", stocknumber='" + stocknumber + '\'' +
                '}';
    }
}
