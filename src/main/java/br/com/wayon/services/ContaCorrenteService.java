package br.com.wayon.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.ContaCorrenteDto;
import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.domains.pk.ContaCorrentePK;
import br.com.wayon.exceptions.ChaveDuplicadaException;
import br.com.wayon.exceptions.ContaCorrenteInexistenteException;
import br.com.wayon.exceptions.ValorInvalidoException;
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
	
	public ContaCorrente sacar(ContaCorrentePK contaCorrente, BigDecimal valorOperacao) {
		
		ContaCorrente contaCorrenteBase = repository.findById(contaCorrente).orElseThrow(() -> new ContaCorrenteInexistenteException("Conta Corrente Inexistente"));
		
		if (contaCorrenteBase.getSaldo().compareTo(valorOperacao) < 0) {
			//Saldo nulo não será permitido
			throw new ContaCorrenteInexistenteException("Saldo indisponível para esta operação");
			
		} else {
			//Atualiza o saldo
			BigDecimal novoSaldo = contaCorrenteBase.getSaldo().subtract(valorOperacao);
			contaCorrenteBase.setSaldo(novoSaldo);
			return repository.save(contaCorrenteBase);
		}
	}

	public ContaCorrente depositar(ContaCorrentePK contaCorrente, BigDecimal valorOperacao) {
		
		ContaCorrente contaCorrenteBase = repository.findById(contaCorrente).orElseThrow(() -> new ContaCorrenteInexistenteException("Conta Corrente Inexistente"));
		
		if(valorOperacao.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValorInvalidoException("Valor da operação deve ser maior que zero");
		}
		
		//Atualiza o saldo
		BigDecimal novoSaldo = contaCorrenteBase.getSaldo().subtract(valorOperacao);
		contaCorrenteBase.setSaldo(novoSaldo);
		return repository.save(contaCorrenteBase);
	}
	
	private ContaCorrente novaContaCorrente(ContaCorrenteDto contaCorrente) {
		BigDecimal saldo = contaCorrente.getSaldo() == null || contaCorrente.getSaldo().compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : contaCorrente.getSaldo();
		return new ContaCorrente(contaCorrente.getContaCorrente(),saldo);
	}

}
