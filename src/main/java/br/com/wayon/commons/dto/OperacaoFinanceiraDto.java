package br.com.wayon.commons.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.domains.enums.EnumStatusOperacao;
import br.com.wayon.domains.enums.EnumTipoOperacao;
import lombok.Builder;
import lombok.Data;

@Data
public class OperacaoFinanceiraDto {
	
	private EnumTipoOperacao tipoOperacao;
	
	private ContaCorrente contaCorrente;
	
	private BigDecimal valorOperacao;
	
	private BigDecimal saldoInstantaneo;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dataExecucao;
	
	private String observacao;
}