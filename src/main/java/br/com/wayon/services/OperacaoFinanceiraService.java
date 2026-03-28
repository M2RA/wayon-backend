package br.com.wayon.services;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.OperacaoFinanceiraDto;
import br.com.wayon.commons.dto.response.OperacaoFinanceiraResponseDto;
import br.com.wayon.domains.OperacaoFinanceira;
import br.com.wayon.repositories.OperacaoFinanceiraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperacaoFinanceiraService {
	
	private OperacaoFinanceiraRepository repository;
	
	public OperacaoFinanceiraResponseDto criarNovaOperacao(OperacaoFinanceiraDto dto) {
		
		OperacaoFinanceira novaOperacao = repository.save(new OperacaoFinanceira(dto));
		
		return new OperacaoFinanceiraResponseDto(novaOperacao);
	}

}
