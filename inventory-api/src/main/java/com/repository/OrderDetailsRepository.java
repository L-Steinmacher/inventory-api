package com.repository;

import com.models.OrderDetails;
import com.models.OrderDetailsId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, OrderDetailsId> {

    @Query(value = "SELECT * FROM ORDERDETAILS \n" +
            "WHERE productid = :productId")
    OrderDetails findByProductId(Long productId);
}
