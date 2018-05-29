package com.freitech.kotetsu.models.customer;

import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PaymentInfo {
	/** 支払月 */
	@Column(name = "PaymentAfter")
	private PaymentAfter paymentAfter;

	/** 支払日 */
	@Column(name = "PaymentDay")
	private PaymentDay paymentDay;

	/** 支払丸め */
	@Column(name = "PaymentRounding")
	private RoundingMode paymentRounding;
}
