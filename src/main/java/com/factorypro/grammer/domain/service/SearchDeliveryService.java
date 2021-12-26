package com.factorypro.grammer.domain.service;

import org.springframework.stereotype.Service;

import com.factorypro.grammer.domain.exception.NotFoundEntitiyException;
import com.factorypro.grammer.domain.model.Delivery;
import com.factorypro.grammer.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SearchDeliveryService {

	private DeliveryRepository deliveryRepository;
	
	public Delivery search(Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.orElseThrow(() -> new NotFoundEntitiyException("Entrega não encontrada"));
	}
}
