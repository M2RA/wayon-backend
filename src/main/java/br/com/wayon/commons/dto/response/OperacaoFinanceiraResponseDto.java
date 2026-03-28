package br.com.wayon.commons.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.istack.NotNull;

import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.domains.OperacaoFinanceira;
import br.com.wayon.domains.enums.EnumTipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class OperacaoFinanceiraResponseDto {

	private Long id;
	
	private EnumTipoOperacao tipoOperacao;

	private Long contaCorrente;

	private BigDecimal valorOperacao;

	private BigDecimal saldoInstantaneo;
	
	private LocalDateTime dataAgendamento;

	private LocalDateTime dataExecucao;
	
	public OperacaoFinanceiraResponseDto(OperacaoFinanceira operacao) {
		this.id = operacao.getId();
		this.tipoOperacao = operacao.getTipoOperacao();
		this.contaCorrente = operacao.getContaCorrente().getContaCorrente();
		this.valorOperacao = operacao.getValorOperacao();
		this.dataAgendamento = operacao.getDataAgendamento();
		this.dataExecucao = operacao.getDataExecucao();
		this.saldoInstantaneo = operacao.getContaCorrente().getSaldo();
	}

}
