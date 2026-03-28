package br.com.wayon.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.NovaContaDto;
import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.exceptions.ChaveDuplicadaException;
import br.com.wayon.repositories.ContaCorrenteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContaCorrenteService {
	
	private ContaCorrenteRepository repository;
	
	public ContaCorrente criarNovaContaCorrente(NovaContaDto contaCorrente) {
		if (repository.findById(contaCorrente.getContaCorrente()).isPresent()) {
			throw new ChaveDuplicadaException("Conta Corrente já cadastrada");
		}
		
		return repository.save(novaContaCorrente(contaCorrente));
	}
	
	private ContaCorrente novaContaCorrente(NovaContaDto contaCorrente) {
		return new ContaCorrente(contaCorrente.getContaCorrente());
	}

}
