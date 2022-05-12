package com.repository;

import com.models.OrderDetails;
import com.models.OrderDetailsId;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, OrderDetailsId> {
}
