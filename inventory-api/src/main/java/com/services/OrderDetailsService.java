package com.services;

import com.models.OrderDetails;

public interface OrderDetailsService {
    OrderDetails addToOrder(long productid, long orderid);

    OrderDetails removeFromOrder(long productid, long orderid);
}
