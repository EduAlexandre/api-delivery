package com.factorypro.grammer.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.factorypro.grammer.domain.model.Client;
import com.factorypro.grammer.domain.repository.ClientRepository;
import com.factorypro.grammer.domain.service.CatalogClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

	private ClientRepository clientRepository;
	private CatalogClientService catalogClientService;
	
	@GetMapping
	public List<Client> list() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> searchById(@PathVariable Long clientId) {
		return clientRepository.findById(clientId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@Valid @RequestBody Client client) {
		return catalogClientService.save(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> updated(@PathVariable Long clientId,
			@Valid @RequestBody Client client) {
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		 client.setId(clientId);
		 client = catalogClientService.save(client);
		 
		 return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> remove(@PathVariable Long clientId ){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogClientService.remove(clientId);
		
		return ResponseEntity.noContent().build();
	}
}






