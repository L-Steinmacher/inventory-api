package com.models;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product
    extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productid;

    @Column(name = "productname",
            nullable = false,
            unique = true)
    private String productname;

    @Column(name = "count")
    private long count;

    @Column(name = "discription", nullable = false)
    private String discription;

    public Product() {
    }

    public Product(String productname, String discription) {
        this.productname = productname;
        this.discription = discription;
    }

    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
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

    public void setCount(long count) {
        this.count = count;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
