package br.com.wayon.domains;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.wayon.commons.dto.ContaCorrenteDto;
import br.com.wayon.domains.pk.ContaCorrentePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tab_conta_corrente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrente {
	
	@EmbeddedId
	private ContaCorrentePK contaCorrente;
	
	private BigDecimal saldo;
	
	public ContaCorrente(ContaCorrenteDto dto) {
		this.contaCorrente = dto.getContaCorrente();
		this.saldo = dto.getSaldo();
	}
	
	public ContaCorrente(ContaCorrentePK pk) {
		this.contaCorrente = pk;
	}
	
}