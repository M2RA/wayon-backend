package br.com.wayon.domains;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import br.com.wayon.commons.dto.OperacaoFinanceiraDto;
import br.com.wayon.domains.enums.EnumTipoOperacao;
import br.com.wayon.domains.pk.ContaCorrentePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tab_operacao_financeira")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperacaoFinanceira {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoOperacao tipoOperacao;

	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name = "FK_CONTA_CORRENTE"), 
	             value = {@JoinColumn(name="agencia"), @JoinColumn(name="numero_conta")})
	private ContaCorrente contaCorrente;

	@NotNull
	private BigDecimal valorOperacao;
	
	private LocalDateTime dataAgendamento;

	private LocalDateTime dataExecucao;
	
	public OperacaoFinanceira(OperacaoFinanceiraDto dto) {
		this.tipoOperacao = dto.getTipoOperacao();
		this.contaCorrente = new ContaCorrente(dto.getContaCorrente());
		this.dataAgendamento = LocalDateTime.now();
		this.dataExecucao = dto.getDataExecucao();
		this.valorOperacao = dto.getValorOperacao();
	}
	
}
