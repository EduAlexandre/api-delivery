package com.factorypro.grammer.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factorypro.grammer.domain.exception.BusinessException;
import com.factorypro.grammer.domain.model.Client;
import com.factorypro.grammer.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor 
@Service
public class CatalogClientService {

	private ClientRepository clientRepository;
	
	@Transactional
	public Client save(Client client) {
		boolean emailAlredyExits = clientRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(existsClient -> !existsClient.equals(client));
		
		if(emailAlredyExits) {
			throw new BusinessException("Já existe  um cliente cadastrado com este e-mail");
		}
		return clientRepository.save(client);
	}
	
	@Transactional
	public void remove(Long clientId) {
		clientRepository.deleteById(clientId);
	}
	
	public Client findClient(Long clientId) {
		return clientRepository.findById(clientId)
				.orElseThrow(() -> new BusinessException("Cliente não encontrado"));
	}
	
	
}
