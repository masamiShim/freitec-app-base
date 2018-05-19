package com.freitech.kotetsu.models.customer;

import java.math.RoundingMode;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

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

	@Column(name = "AppliedStartDate")
	private LocalDate applyStartDate;

	/** 企業番号 */
	@Length(min = 1, max = 10)
	@Column(name = "CustomerNo")
	private String customerNo;

	/** 企業名 */
	@Column(name = "CorpName")
	private String corpName;

	/** 企業表示名 */
	@Column(name = "CorpDispName")
	private String corpDispName;

	/** 敬称 */
	@Column(name = "Honorific")
	private String honorific;

	/** 請求月 */
	@Column(name = "CutoffAfter")
	private CutoffAfter cutoffAfter;

	/** 請求締日 */
	@Column(name = "CutoffDays")
	private CutoffDays cutoffDays;

	/** 請求丸め */
	@Column(name = "CutoffRounding")
	private RoundingMode cutoffRounding;

	/** 支払月 */
	@Column(name = "PaymentAfter")
	private PaymentAfter paymentAfter;

	/** 支払日 */
	@Column(name = "PaymentDay")
	private PaymentDay paymentDay;

	/** 支払丸め */
	@Column(name = "PaymentRounding")
	private RoundingMode paymentRounding;

	/** 外税 */
	@Column(name = "ExcludeTax")
	private boolean excludeTax;

}
