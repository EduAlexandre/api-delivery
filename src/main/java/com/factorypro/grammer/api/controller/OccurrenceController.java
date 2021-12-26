package com.factorypro.grammer.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.factorypro.grammer.api.assembler.OccurrenceAssembler;
import com.factorypro.grammer.api.model.OccurrenceModel;
import com.factorypro.grammer.api.model.input.OccurrenceInput;
import com.factorypro.grammer.domain.model.Delivery;
import com.factorypro.grammer.domain.model.Occurrence;
import com.factorypro.grammer.domain.service.OccurrenceRegisterService;
import com.factorypro.grammer.domain.service.SearchDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery/{deliveryId}/occurrences")
public class OccurrenceController {

	private SearchDeliveryService deliveryService;
	private OccurrenceRegisterService occurrenceRegisterService;
	private OccurrenceAssembler occurrenceAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OccurrenceModel register(@PathVariable Long deliveryId,
			@Valid @RequestBody OccurrenceInput occurrenceInput) {
		
		Occurrence registredOccurrence = occurrenceRegisterService
		               .register(deliveryId, occurrenceInput.getDescription());
		
		return occurrenceAssembler.toModel(registredOccurrence);
	}
	
	@GetMapping
	public List<OccurrenceModel> list(@PathVariable Long deliveryId){
		Delivery delivery = deliveryService.search(deliveryId);
		
		return occurrenceAssembler.toCollectionModel(delivery.getOccurrences());
	}
}
