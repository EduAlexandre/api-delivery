package com.factorypro.grammer.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.factorypro.grammer.api.model.DeliveryRepresentation;
import com.factorypro.grammer.api.model.input.DeliveryRequestModel;
import com.factorypro.grammer.domain.model.Delivery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliveryAssembler {

	private ModelMapper modelMapper;
	
	public DeliveryRepresentation toModel(Delivery delivery) {
		return modelMapper.map(delivery, DeliveryRepresentation.class);
	}
	
	public List<DeliveryRepresentation> toCollectionModel(List<Delivery> deliverys){
		return deliverys.stream()
		          .map(this::toModel)
		          .collect(Collectors.toList());
	}
	
	public Delivery toEntity(DeliveryRequestModel deliveryRequestModel) {
		return modelMapper.map(deliveryRequestModel, Delivery.class);
	}
}
