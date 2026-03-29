package br.com.wayon.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import br.com.wayon.commons.dto.TransferenciaDto;
import br.com.wayon.commons.dto.response.OperacaoFinanceiraResponseDto;
import br.com.wayon.commons.dto.response.TransferenciaResponseDto;
import br.com.wayon.domains.enums.EnumTipoOperacao;
import br.com.wayon.exceptions.ValorInvalidoException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransferenciaService {
	private OperacaoFinanceiraService service;
	
	public TransferenciaResponseDto novaTransferencia(TransferenciaDto dto) {
		if(isTransferenciaValida(dto)) {
		
			OperacaoFinanceiraResponseDto saida = service.criarNovaOperacao(dto.getSaida()); 
			OperacaoFinanceiraResponseDto entrada = service.criarNovaOperacao(dto.getEntrada());
			
			return new TransferenciaResponseDto(saida,entrada);
		} else {
			throw new RuntimeException("Erro genérico\n");
		}
	}

	private Boolean isTransferenciaValida(TransferenciaDto dto) {
		//Saída tem que ser SAQUE / Entrada tem que ser DEPOSITO
		//Operacao deve ser OBRIGATORIAMENTE do tipo SAQUE
		if(dto.getSaida().getTipoOperacao() != EnumTipoOperacao.SAQUE || dto.getEntrada().getTipoOperacao() != EnumTipoOperacao.DEPOSITO) {
			throw new ValorInvalidoException("Operação Inválida. Operação de saída deve ser SAQUE e entrada deve ser DEPOSITO");
		}
		
		//As operações devem ocorrer em contas diferentes
		if(Long.compare(dto.getSaida().getContaCorrente().getContaCorrente(), dto.getEntrada().getContaCorrente().getContaCorrente()) == 0) {
			throw new ValorInvalidoException("Conta de Origem x Destino devem ser diferentes");
		}
		
		//Valor das operações de Saida x Entrada devem ser os mesmos
		if(dto.getSaida().getValorOperacao().compareTo(dto.getEntrada().getValorOperacao()) != 0) {
			throw new ValorInvalidoException("Valores Saida x Entrada são divergentes");
		}
		
		//As datas de execução de saída deve ser menor que a data de execução da entrada
		if(ChronoUnit.DAYS.between(dto.getSaida().getDataExecucao().toLocalDate(),dto.getEntrada().getDataExecucao().toLocalDate()) > 0) {
			throw new ValorInvalidoException("Data de entrada não pode ser maior que a data de saída");
		}
		
		return true;
	}
}
