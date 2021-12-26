package com.factorypro.grammer.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.factorypro.grammer.api.assembler.DeliveryAssembler;
import com.factorypro.grammer.api.model.DeliveryRepresentation;
import com.factorypro.grammer.api.model.input.DeliveryRequestModel;
import com.factorypro.grammer.domain.model.Delivery;
import com.factorypro.grammer.domain.repository.DeliveryRepository;
import com.factorypro.grammer.domain.service.CompletionDeliveryService;
import com.factorypro.grammer.domain.service.RequestDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	private DeliveryRepository deliveryRepository;
	private RequestDeliveryService deliveryService;
	private DeliveryAssembler deliveryAssembler;
	private CompletionDeliveryService completionDeliveryService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryRepresentation request(@Valid @RequestBody DeliveryRequestModel deliveryRequestModel) {
		Delivery newDelivery = deliveryAssembler.toEntity(deliveryRequestModel);
		Delivery deliveryRequested = deliveryService.request(newDelivery); 
		return deliveryAssembler.toModel(deliveryRequested);
	}
	
	@GetMapping
	public List<DeliveryRepresentation> listAll(){
		return deliveryAssembler.toCollectionModel(deliveryRepository.findAll());
	}
	
	@GetMapping("/{deliveryId}")
	public ResponseEntity<DeliveryRepresentation> searchDeliveryById(@PathVariable Long deliveryId ){
		return deliveryRepository.findById(deliveryId)
				.map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{deliveryId}/completion")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finishDelivery(@PathVariable Long deliveryId) {
		completionDeliveryService.completion(deliveryId);
	}
}
