package br.com.wayon.domains;

import java.math.BigDecimal;
import java.util.Comparator;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wayon.commons.dto.ContaCorrenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tab_conta_corrente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrente{

	@Id
	private String numeroConta;
	
	private BigDecimal saldo;
	
	public ContaCorrente(ContaCorrenteDto dto) {
		this.saldo = dto.getSaldo();
	}
	
	public ContaCorrente(String contaCorrente) {
		this.numeroConta = contaCorrente;
	}
}