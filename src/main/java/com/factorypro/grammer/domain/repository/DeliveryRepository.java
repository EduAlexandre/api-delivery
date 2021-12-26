package com.factorypro.grammer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factorypro.grammer.domain.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
