package br.com.wayon.commons.utils;

import java.math.BigDecimal;

import br.com.wayon.domains.enums.EnumAliquotas;

public class Utils {
	
	public static BigDecimal getValorTransferencia(Long prazo, BigDecimal valorTransferido) {
		
		if(prazo == 0l) {

			return getTaxaTransferencia(valorTransferido,new BigDecimal("3"), EnumAliquotas.IMEDIATA);
			
		} else if (prazo > 0l && prazo <= 10l) {
			
			return getTaxaTransferencia(valorTransferido ,new BigDecimal("12"), EnumAliquotas.PRAZO10);
			
		} else if (prazo > 10l && prazo <= 20l) {
			
			return getTaxaTransferencia(valorTransferido ,BigDecimal.ZERO, EnumAliquotas.PRAZO20);
			
		} else if (prazo > 20l && prazo <= 30l) {
			
			return getTaxaTransferencia(valorTransferido ,BigDecimal.ZERO, EnumAliquotas.PRAZO30);
			
		} else if (prazo > 31l && prazo <= 40l) {
			
			return getTaxaTransferencia(valorTransferido ,BigDecimal.ZERO, EnumAliquotas.PRAZO40);
			
		} else if (prazo > 41l && prazo <= 50l) {
			
			return getTaxaTransferencia(valorTransferido ,BigDecimal.ZERO, EnumAliquotas.PRAZO50);
			
		} else {
			throw new RuntimeException("Prazo extrapolou a antecedência máxima. Transferência não pode ser realizada.");
		}
	}

	private static BigDecimal getTaxaTransferencia(BigDecimal valorRequerido, BigDecimal valorAdicional, EnumAliquotas aliquota) {
		BigDecimal valorTaxa = valorRequerido.multiply(aliquota.getAliquota());
		BigDecimal valorFinal = valorTaxa.add(valorAdicional);
		return valorFinal;
	}
}
