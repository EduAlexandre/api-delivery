package com.factorypro.grammer.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factorypro.grammer.domain.model.Delivery;
import com.factorypro.grammer.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CompletionDeliveryService {

	private DeliveryRepository deliveryRepository;
	private SearchDeliveryService searchDeliveryService;
	
	@Transactional
	public void completion(Long deliveryId) {
		Delivery delivery = searchDeliveryService.search(deliveryId);		
		
		delivery.finish();
		
		deliveryRepository.save(delivery);
	}
	
}
