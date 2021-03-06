package com.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order
        extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderid;

    @Column(nullable = false, unique = false)
    private long customerid;

    private String comments;

    @OneToMany(mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "order",
        allowSetters = true)
    private Set<OrderDetails> products = new HashSet<>();

    public Order() {
    }

    public Order(long customerid, String comments) {
        this.customerid = customerid;
        this.comments = comments;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public Set<OrderDetails> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderDetails> products) {
        this.products = products;
    }

    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
