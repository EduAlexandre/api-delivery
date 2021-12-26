package com.factorypro.grammer.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.factorypro.grammer.domain.exception.BusinessException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Client client;	
	
	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
	private List<Occurrence> occurrences = new ArrayList<>();
	
	@Embedded
	private Recipient recipient;
	
	@NotNull
	private BigDecimal rate;
		
	@Enumerated(EnumType.STRING)
	private StatusDelivery statusDelivery;
		
	private OffsetDateTime orderData;
		
	private OffsetDateTime finishDate;
	
	public Occurrence addOccurrence(String description) {
		Occurrence occurrence = new Occurrence();
		occurrence.setDescription(description);
		occurrence.setDataRegister(OffsetDateTime.now());
		occurrence.setDelivery(this);
		
		this.getOccurrences().add(occurrence);
		
		return occurrence;
	}
	
	public void finish () {
		if(canNotBeFinish()) {
			throw new BusinessException("Entrega não pode ser finalizada");
		}
		
		setStatusDelivery(StatusDelivery.FINALIZADA);
		setFinishDate(OffsetDateTime.now());
	}
	
	public boolean canBeFinish() {
		return StatusDelivery.PENDENTE.equals(getStatusDelivery());
	}
	
	public boolean canNotBeFinish() {
		return !canBeFinish();
	}
}
