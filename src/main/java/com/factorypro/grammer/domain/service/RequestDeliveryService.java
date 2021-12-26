package com.factorypro.grammer.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factorypro.grammer.domain.model.Client;
import com.factorypro.grammer.domain.model.Delivery;
import com.factorypro.grammer.domain.model.StatusDelivery;
import com.factorypro.grammer.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RequestDeliveryService {

	private DeliveryRepository deliveryRepository;
	private CatalogClientService catalogClientService;
	
	@Transactional
	public Delivery request(Delivery delivery) {
		Client client = catalogClientService.findClient(delivery.getClient().getId());
		
		
		delivery.setClient(client);
		delivery.setStatusDelivery(StatusDelivery.PENDENTE);
		delivery.setOrderData(OffsetDateTime.now());
		return deliveryRepository.save(delivery);
	}
}
