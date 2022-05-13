package com.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderDetailsId
        implements Serializable {
    private  long order;

    private long product;

    public OrderDetailsId() {
    }

    public OrderDetailsId(long productid, long orderId) {
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public long getProduct() {
        return product;
    }

    public void setProduct(long product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsId that = (OrderDetailsId) o;
        return product == that.product &&
                order == that.order;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
