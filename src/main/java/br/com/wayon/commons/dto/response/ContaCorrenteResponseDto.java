package br.com.wayon.commons.dto.response;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.wayon.commons.dto.ContaCorrenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteResponseDto {
	
	private String numeroConta;
	
	private BigDecimal saldo;
	
	private List<OperacaoFinanceiraResponseDto> extrato;
	
}