package br.com.wayon.commons.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaResponseDto {
	private OperacaoFinanceiraResponseDto entrada;
	private OperacaoFinanceiraResponseDto saida;
}
