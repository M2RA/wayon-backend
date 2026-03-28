package br.com.wayon.commons.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.wayon.domains.pk.ContaCorrentePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovaContaDto implements Serializable{
	private ContaCorrentePK contaCorrente;
}
