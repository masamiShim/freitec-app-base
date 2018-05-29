package com.freitech.kotetsu.models.customer;

import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillingInfo {
	/** 請求月 */
	@Column(name = "CutoffAfter")
	private CutoffAfter cutoffAfter;

	/** 請求締日 */
	@Column(name = "CutoffDays")
	private CutoffDays cutoffDays;

	/** 請求丸め */
	@Column(name = "CutoffRounding")
	private RoundingMode cutoffRounding;
}
