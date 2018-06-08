package com.freitech.kotetsu.models.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class PaymentInfo {
	/** 支払月 */
	@Column(name = "PaymentAfter")
	private PaymentAfter paymentAfter;

	/** 支払日 */
	@Column(name = "PaymentDay")
	private PaymentDay paymentDay;

	/** 支払丸め */
	@Column(name = "PaymentRounding")
	private Rounding paymentRounding;
}
