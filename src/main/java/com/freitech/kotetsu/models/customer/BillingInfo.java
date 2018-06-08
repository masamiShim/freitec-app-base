package com.freitech.kotetsu.models.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class BillingInfo {
	
	/** 請求月 */
	@Column(name = "CutoffAfter")
	private CutoffAfter cutoffAfter;

	/** 請求締日 */
	@Column(name = "CutoffDay")
	private CutoffDay cutoffDay;

	/** 請求丸め */
	@Column(name = "CutoffRounding")
	private Rounding cutoffRounding;
}
