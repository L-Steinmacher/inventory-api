package com.repository;

import com.models.OrderDetails;
import com.models.OrderDetailsId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, OrderDetailsId> {
}
