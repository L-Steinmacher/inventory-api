package com.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order
        extends Auditable
{

}
