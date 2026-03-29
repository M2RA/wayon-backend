package br.com.wayon.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDto {
	private OperacaoFinanceiraDto saida;
	private OperacaoFinanceiraDto entrada;
}
