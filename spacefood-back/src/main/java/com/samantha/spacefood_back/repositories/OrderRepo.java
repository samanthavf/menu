package com.samantha.spacefood_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samantha.spacefood_back.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

}
