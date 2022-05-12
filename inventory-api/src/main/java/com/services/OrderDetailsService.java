package com.services;

import com.models.OrderDetails;

public interface OrderDetailsService {
    OrderDetails addToOrder(long productid);

    OrderDetails removeFromOrder(long productid);
}
