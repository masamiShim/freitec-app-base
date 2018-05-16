package com.freitech.kotetsu.models.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.freitech.kotetsu.models.base.AbstractAttribute;

import groovy.transform.ToString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@Data
@ToString(excludes = "customer")
@EqualsAndHashCode(exclude = "customer", callSuper = false)
public class CustomerAttribute extends AbstractAttribute {
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "CustomerId", nullable = false)
	private Customer customer;

	/** 企業名 */

	@Column(name = "CorpName")
	private String corpName;

	/** 企業表示名 */
	@Column(name = "CorpDispName")
	private String corpDispName;

	/** 継承 */
	@Column(name = "Honorific")
	private String honorific;

	/** 請求月 */
	@Column(name = "CutoffAfter")
	private int cutoffAfter;

	/** 請求締日 */
	@Column(name = "CutoffDays")
	private String cutoffDays;

	/** 請求丸め */
	@Column(name = "CutoffRounding")
	private int cutoffRounding;

	/** 支払月 */
	@Column(name = "PaymentAfter")
	private int paymentAfter;

	/** 支払日 */
	@Column(name = "PaymentDay")
	private String paymentDay;

	/** 支払丸め */
	@Column(name = "PaymentRounding")
	private int paymentRounding;

	/** 外税 */
	@Column(name = "ExcludeTax")
	private boolean excludeTax;

}
