package com.factorypro.grammer.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factorypro.grammer.domain.model.Delivery;
import com.factorypro.grammer.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OccurrenceRegisterService {

	private SearchDeliveryService searchDeliveryService;
	
	@Transactional
	public Occurrence register(Long deliveryId, String description) {
		Delivery delivery = searchDeliveryService.search(deliveryId);	
		return delivery.addOccurrence(description);
	}
}
