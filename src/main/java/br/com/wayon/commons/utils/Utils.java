package br.com.wayon.commons.utils;

import java.math.BigDecimal;

public class Utils {
	
	public static BigDecimal getValorTransferencia(Integer prazo, BigDecimal valorTransferido) {
		
		if(prazo == 0) {

			return getTaxaTransferencia(valorTransferido,new BigDecimal("3"), new BigDecimal("2.5"));
			
		} else if (prazo > 0 && prazo <= 10) {
			
			return getTaxaTransferencia(valorTransferido ,new BigDecimal("12"), BigDecimal.ZERO);
			
		} else if (prazo > 10 && prazo <= 20) {
			
			return getTaxaTransferencia(valorTransferido ,BigDecimal.ZERO, new BigDecimal("8.2"));
			
		} else if (prazo > 20 && prazo <= 30) {
			
			return getTaxaTransferencia(valorTransferido ,BigDecimal.ZERO, new BigDecimal("6.9"));
			
		} else if (prazo > 31 && prazo <= 40) {
			
			return getTaxaTransferencia(valorTransferido ,BigDecimal.ZERO, new BigDecimal("4.7"));
			
		} else if (prazo > 41 && prazo <= 50) {
			
			return getTaxaTransferencia(valorTransferido ,BigDecimal.ZERO, new BigDecimal("1.7"));
			
		} else {
			throw new RuntimeException("Prazo extrapolou a antecedência máxima. Transferência não pode ser realizada.");
		}
	}

	private static BigDecimal getTaxaTransferencia(BigDecimal valorRequerido, BigDecimal valorAdicional, BigDecimal aliquota) {
		return valorRequerido.multiply(aliquota).add(valorAdicional);
	}

}
