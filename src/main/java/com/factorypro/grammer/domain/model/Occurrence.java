package com.factorypro.grammer.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Occurrence {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Delivery delivery;
	
	private String description;
    private OffsetDateTime dataRegister;
	
}
