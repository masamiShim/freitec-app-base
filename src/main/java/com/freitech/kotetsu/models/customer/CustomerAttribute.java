package com.freitech.kotetsu.models.customer;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

	/** 適用開始日 */
	@Column(name = "AppliedStartDate")
	private LocalDate applyStartDate;

	/** 企業名 */
	@Column(name = "CorpName")
	private String corpName;

	/** 企業表示名 */
	@Column(name = "CorpDispName")
	private String corpDispName;

	/** 敬称 */
	@Column(name = "Honorific")
	private String honorific;

	@Embedded
	private BillingInfo billing;

	@Embedded
	private PaymentInfo payment;

	/** 内税 */
	@Column(name = "IncludeTax")
	private boolean includeTax;

}
