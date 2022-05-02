package com.example.demo.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
//@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends CrudRepository<Order, Long> {

}
