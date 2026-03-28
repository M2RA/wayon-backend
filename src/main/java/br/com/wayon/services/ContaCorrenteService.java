package br.com.wayon.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.ContaCorrenteDto;
import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.exceptions.ChaveDuplicadaException;
import br.com.wayon.exceptions.ContaCorrenteInexistenteException;
import br.com.wayon.repositories.ContaCorrenteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContaCorrenteService {
	
	private ContaCorrenteRepository repository;
	
	public ContaCorrente criarNovaContaCorrente(ContaCorrenteDto contaCorrente) {
		if (repository.findById(contaCorrente.getContaCorrente()).isPresent()) {
			throw new ChaveDuplicadaException("Conta Corrente já cadastrada");
		}
		
		return repository.save(novaContaCorrente(contaCorrente));
	}
	
	public ContaCorrente atualizaSaldo(ContaCorrenteDto contaDto) {
		if (repository.findById(contaDto.getContaCorrente()).isEmpty()) {
			throw new ContaCorrenteInexistenteException("Conta Corrente inexistente");
		} else if (repository.findById(contaDto.getContaCorrente()).get().getSaldo().compareTo(contaDto.getSaldo()) < 0) {
			//Saldo nulo não será permitido
			throw new ContaCorrenteInexistenteException("Saldo indisponível para esta operação");
		} else {
			return repository.save(new ContaCorrente(contaDto));
		}
	}
	
	private ContaCorrente novaContaCorrente(ContaCorrenteDto contaCorrente) {
		BigDecimal saldo = contaCorrente.getSaldo() == null || contaCorrente.getSaldo().compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : contaCorrente.getSaldo();
		return new ContaCorrente(contaCorrente.getContaCorrente(),saldo);
	}

}
