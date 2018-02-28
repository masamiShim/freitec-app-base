package com.freitech.kotetsu.core.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

/**
 * 金額系を司るドメイン
 * 
 * @author shimbuichi
 *
 */
@Slf4j
public class Amount extends BigDecimal {

	private static final long serialVersionUID = -8920446754939881773L;

	public Amount(String val) {
		super(val);
	}

	public Amount(int val) {
		super(val);
	}

	/**
	 * ゼロより大きい金額か確認
	 * 
	 * @return
	 */
	public boolean aboveZero() {
		return Optional.ofNullable(this).map(u -> u.compareTo(BigDecimal.ZERO) > 0).orElse(false);
	}

	/**
	 * 0除算セーフな割り算
	 * 
	 * @param diviser
	 * @param scale
	 * @param mode
	 * @return
	 */
	public Amount divide(Amount diviser, int scale, RoundingMode mode) {
		Optional.ofNullable(diviser).ifPresent(div -> {
			if (!div.aboveZero()) {
				log.warn("ゼロで割ろうとしてるから、とりあえず0で返します。");
			} else {
				this.divide(div, scale, mode);
			}
		});
		return this;
	}

}
