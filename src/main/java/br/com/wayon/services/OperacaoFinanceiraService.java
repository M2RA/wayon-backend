package br.com.wayon.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.OperacaoFinanceiraDto;
import br.com.wayon.commons.dto.response.ContaCorrenteResponseDto;
import br.com.wayon.commons.dto.response.OperacaoFinanceiraResponseDto;
import br.com.wayon.commons.utils.Utils;
import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.domains.OperacaoFinanceira;
import br.com.wayon.domains.enums.EnumStatusOperacao;
import br.com.wayon.domains.enums.EnumTipoOperacao;
import br.com.wayon.exceptions.ValorInvalidoException;
import br.com.wayon.repositories.OperacaoFinanceiraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperacaoFinanceiraService {
	
	private ContaCorrenteService contaCorrenteService;
	private OperacaoFinanceiraRepository repository;
	
	
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
	
	public OperacaoFinanceiraResponseDto criarNovaOperacao(OperacaoFinanceiraDto dto) {
		
		//Primeiro vai lançar o desconto da Taxa de transferência se houver
		if(dto.getTipoOperacao().equals(EnumTipoOperacao.SAQUE)) {
			
			OperacaoFinanceiraResponseDto operacaoTaxa = registraOperacaoTaxaTransferencia(dto);
			OperacaoFinanceiraDto novaOperacao = new OperacaoFinanceiraDto();
			novaOperacao.setContaCorrente(new ContaCorrente(operacaoTaxa.getContaCorrente()));
			novaOperacao.setDataExecucao(operacaoTaxa.getDataExecucao());
			novaOperacao.setTipoOperacao(dto.getTipoOperacao());
			novaOperacao.setValorOperacao(dto.getValorOperacao());
			novaOperacao.setObservacao(dto.getObservacao());
			return novaOperacao(novaOperacao);
			
		} else {
			return novaOperacao(dto);
			
		}
	}
	
	private OperacaoFinanceiraResponseDto registraOperacaoTaxaTransferencia(OperacaoFinanceiraDto dto) {
		Long prazo = ChronoUnit.DAYS.between(LocalDate.now(),dto.getDataExecucao().toLocalDate());
		
		BigDecimal taxaTransferencia = Utils.getValorTransferencia(prazo, dto.getValorOperacao());
		
		OperacaoFinanceiraDto novaOperacao = new OperacaoFinanceiraDto();
		
		novaOperacao.setContaCorrente(dto.getContaCorrente());
		novaOperacao.setDataExecucao(dto.getDataExecucao());
		novaOperacao.setObservacao("Taxa de Transferência");
		novaOperacao.setTipoOperacao(EnumTipoOperacao.SAQUE);
		novaOperacao.setValorOperacao(taxaTransferencia);
		
		return novaOperacao(novaOperacao);
		
	}

	private OperacaoFinanceiraResponseDto novaOperacao(OperacaoFinanceiraDto dto) {
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
		} else if(dto.getDataExecucao().compareTo(LocalDateTime.now()) <= 0) {
			throw new ValorInvalidoException("A data de execução deve ser maior que a data atual");
		} else {
			throw new ValorInvalidoException("Tipo operação Inválida. Valores válidos: SAQUE ou DEPOSITO");
		}
		
		OperacaoFinanceira novaOperacao = repository.save(new OperacaoFinanceira(dto));
		
		return new OperacaoFinanceiraResponseDto(novaOperacao);
	}
}
