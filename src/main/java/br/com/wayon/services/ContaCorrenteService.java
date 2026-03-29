package br.com.wayon.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.ContaCorrenteDto;
import br.com.wayon.commons.dto.OperacaoFinanceiraDto;
import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.domains.OperacaoFinanceira;
import br.com.wayon.domains.enums.EnumTipoOperacao;
import br.com.wayon.exceptions.ChaveDuplicadaException;
import br.com.wayon.exceptions.ContaCorrenteInexistenteException;
import br.com.wayon.exceptions.ValorInvalidoException;
import br.com.wayon.repositories.ContaCorrenteRepository;
import br.com.wayon.repositories.OperacaoFinanceiraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContaCorrenteService {
	
	private ContaCorrenteRepository repository;
	private OperacaoFinanceiraRepository operacaoFinanceiraRepository;
	
	public ContaCorrente criarNovaContaCorrente(ContaCorrenteDto contaCorrente) {
		ContaCorrente novaConta = repository.save(novaContaCorrente(contaCorrente));
		
		//Registrando o início das atividades da nova conta
		OperacaoFinanceiraDto novaOperacao = new OperacaoFinanceiraDto();
		novaOperacao.setContaCorrente(novaConta);
		novaOperacao.setDataExecucao(LocalDateTime.now());
		novaOperacao.setSaldoInstantaneo(novaConta.getSaldo());
		novaOperacao.setTipoOperacao(EnumTipoOperacao.DEPOSITO);
		novaOperacao.setValorOperacao(novaConta.getSaldo());
		novaOperacao.setObservacao("Saldo Inicial");
		operacaoFinanceiraRepository.save(new OperacaoFinanceira(novaOperacao));
		
		return novaConta;
	}
	
	public ContaCorrente sacar(Long contaCorrente, BigDecimal valorOperacao) {
		
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

	public ContaCorrente depositar(Long contaCorrente, BigDecimal valorOperacao) {
		
		ContaCorrente contaCorrenteBase = repository.findById(contaCorrente).orElseThrow(() -> new ContaCorrenteInexistenteException("Conta Corrente Inexistente"));
		
		if(valorOperacao.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValorInvalidoException("Valor da operação deve ser maior que zero");
		}
		
		//Atualiza o saldo
		BigDecimal novoSaldo = contaCorrenteBase.getSaldo().add(valorOperacao);
		contaCorrenteBase.setSaldo(novoSaldo);
		return repository.save(contaCorrenteBase);
	}
	
	private ContaCorrente novaContaCorrente(ContaCorrenteDto contaCorrente) {
		BigDecimal saldo = contaCorrente.getSaldo() == null || contaCorrente.getSaldo().compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : contaCorrente.getSaldo();
		contaCorrente.setSaldo(saldo);
		return new ContaCorrente(contaCorrente);
	}
}
