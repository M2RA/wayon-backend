package br.com.wayon.domains;

import java.time.LocalDateTime;
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

import br.com.wayon.domains.pk.ContaCorrentePK;

@Entity
@Table(name="tab_operacao_financeira")
public class OperacaoFinanceira {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name = "FK_CONTA_CORRENTE"), 
	             value = {@JoinColumn(name="agencia"), @JoinColumn(name="conta_corrente")})
	private ContaCorrente contaCorrente;
	
	private LocalDateTime dataAgendamento;

	private LocalDateTime dataExecucao;
}
