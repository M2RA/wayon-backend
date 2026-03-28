package br.com.wayon.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.OperacaoFinanceiraDto;
import br.com.wayon.commons.dto.response.ContaCorrenteResponseDto;
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
	
	private ContaCorrenteService contaCorrenteService;
	private OperacaoFinanceiraRepository repository;
	
	public OperacaoFinanceiraResponseDto criarNovaOperacao(OperacaoFinanceiraDto dto) {
		ContaCorrente contaAtualizada;
		// Se operação de saída
		if(dto.getTipoOperacao().equals(EnumTipoOperacao.SAQUE)) {
			//Atualiza Saldo
			contaAtualizada = contaCorrenteService.sacar(dto.getContaCorrente().getContaCorrente(),dto.getValorOperacao());
			dto.setContaCorrente(contaAtualizada);
			dto.setSaldoInstantaneo(contaAtualizada.getSaldo());
		} else if (dto.getTipoOperacao().equals(EnumTipoOperacao.DEPOSITO)) {
			contaAtualizada = contaCorrenteService.depositar(dto.getContaCorrente().getContaCorrente(), dto.getValorOperacao());
			dto.setContaCorrente(contaAtualizada);
			dto.setSaldoInstantaneo(contaAtualizada.getSaldo());
		} else {
			throw new ValorInvalidoException("Tipo operação Inválida. Valores válidos: SAQUE ou DEPOSITO");
		}
		
		OperacaoFinanceira novaOperacao = repository.save(new OperacaoFinanceira(dto));
		
		return new OperacaoFinanceiraResponseDto(novaOperacao);
	}
	
	public List<OperacaoFinanceiraResponseDto> getExtrato(Long contaCorrente) {
		
		List<OperacaoFinanceira> operacoesBase = repository.getExtrato(contaCorrente);
		
		List<OperacaoFinanceiraResponseDto> operacoes = new ArrayList<>();
		
		operacoesBase.forEach(o -> {
			OperacaoFinanceiraResponseDto operacao = new OperacaoFinanceiraResponseDto();
			
			operacao.setContaCorrente(o.getContaCorrente().getContaCorrente());
			operacao.setDataAgendamento(o.getDataAgendamento());
			operacao.setDataExecucao(o.getDataExecucao());
			operacao.setId(o.getId());
			operacao.setSaldoInstantaneo(o.getSaldoInstantaneo());
			operacao.setTipoOperacao(o.getTipoOperacao());
			operacao.setValorOperacao(o.getValorOperacao());
			
			operacoes.add(operacao);
		});
		
		return operacoes;
	}

}
