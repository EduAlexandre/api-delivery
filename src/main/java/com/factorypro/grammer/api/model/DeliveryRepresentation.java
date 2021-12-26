package com.factorypro.grammer.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.factorypro.grammer.domain.model.StatusDelivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRepresentation {

	private Long id;
	private ClientAbstractModel client;
	private RecipientRepresentation recipient;
	private BigDecimal rate;
	private StatusDelivery status;
    private OffsetDateTime orderData;	
	private OffsetDateTime finishDate;
}
