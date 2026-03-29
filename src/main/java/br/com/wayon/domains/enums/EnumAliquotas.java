package br.com.wayon.domains.enums;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public enum EnumAliquotas {
	IMEDIATA(new BigDecimal("2.5")),
	PRAZO10(new BigDecimal("0")),
	PRAZO20(new BigDecimal("8.2")),
	PRAZO30(new BigDecimal("6.9")),
	PRAZO40(new BigDecimal("4.7")),
	PRAZO50(new BigDecimal("1.7"));
	
	private final BigDecimal aliquota;
	
	private EnumAliquotas(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}
	
	public BigDecimal getAliquota() {
		if(aliquota.compareTo(BigDecimal.ZERO) == 0 ) {
			return BigDecimal.ZERO;
		} else {
			if(aliquota.multiply(BigDecimal.TEN).doubleValue()%Double.valueOf(5) == 0){
				return aliquota.divide(new BigDecimal("100"));
			}
			return aliquota.divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
		}
	}
}
