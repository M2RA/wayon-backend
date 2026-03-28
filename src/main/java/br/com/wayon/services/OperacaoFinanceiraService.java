package br.com.wayon.services;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.OperacaoFinanceiraDto;
import br.com.wayon.commons.dto.response.OperacaoFinanceiraResponseDto;
import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.domains.OperacaoFinanceira;
import br.com.wayon.domains.enums.EnumTipoOperacao;
import br.com.wayon.exceptions.ValorInvalidoException;
import br.com.wayon.repositories.OperacaoFinanceiraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperacaoFinanceiraService {
	
	private OperacaoFinanceiraRepository repository;
	
	private ContaCorrenteService contaCorrenteService;
	
	public OperacaoFinanceiraResponseDto criarNovaOperacao(OperacaoFinanceiraDto dto) {
		ContaCorrente contaAtualizada;
		// Se operação de saída
		if(dto.getTipoOperacao().equals(EnumTipoOperacao.SAQUE)) {
			//Atualiza Saldo
			contaAtualizada = contaCorrenteService.sacar(dto.getContaCorrente(),dto.getValorOperacao());
			dto.setContaCorrente(contaAtualizada.getContaCorrente());
		} else if (dto.getTipoOperacao().equals(EnumTipoOperacao.DEPOSITO)) {
			contaAtualizada = contaCorrenteService.depositar(dto.getContaCorrente(), dto.getValorOperacao());
			dto.setContaCorrente(contaAtualizada.getContaCorrente());
		} else {
			throw new ValorInvalidoException("Tipo operação Inválida. Valores válidos: SAQUE ou DEPOSITO");
		}
		
		OperacaoFinanceira novaOperacao = repository.save(new OperacaoFinanceira(dto));
		
		return new OperacaoFinanceiraResponseDto(novaOperacao);
	}

}
