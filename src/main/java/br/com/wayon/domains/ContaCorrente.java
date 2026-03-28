package br.com.wayon.domains;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	
	private final BigDecimal saldo = BigDecimal.ZERO;
	
}