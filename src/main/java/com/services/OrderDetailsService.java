package com.services;

import com.models.OrderDetails;

public interface OrderDetailsService {
    OrderDetails addToOrder(long productid, long orderid, long quantity);

    OrderDetails removeFromOrder(long productid, long orderid, long quantity);

//    OrderDetails findByProductId(long productid);
}
