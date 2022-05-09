package com.models;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "procuctname", nullable = false)
    private String productname;

    @Column(name = "count")
    private Long count;

    @Column(name = "discription")
    private String discription;

    public Product() {
    }

    public Product(Long id, String productname, Long count, String discription) {
        this.id = id;
        this.productname = productname;
        this.count = count;
        this.discription = discription;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
