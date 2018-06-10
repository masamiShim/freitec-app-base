package com.freitech.kotetsu.models.customer;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.freitech.kotetsu.models.SecurityAuditor;
import com.freitech.kotetsu.models.base.AbstractAttribute;

import groovy.transform.ToString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "CustomerAttribute")
@SuppressWarnings("serial")
@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class CustomerAttribute extends SecurityAuditor {

	@ManyToOne(optional = false)
	@JoinColumn(name = "CustomerId", nullable = false)
	private Customer customer;

	/** 企業名 */
	@Column(name = "CorpName")
	private String corpName;

	/** 企業表示名 */
	@Column(name = "CorpDispName")
	private String corpDispName;

	/** 適用開始日 */
	@NotNull
	@Column(name = "AppliedStartDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate applyStartDate;

	/** 敬称 */
	@Column(name = "Honorific")
	private String honorific;
	
	@Embedded
	private Name name;
	
	@Embedded
	private Address address;

	@Embedded
	private Contact contact;

	@Embedded
	private BillingInfo billing;

	@Embedded
	private PaymentInfo payment;

	/** 内税 */
	@Column(name = "IncludeTax")
	private IncludeTax includeTax;

	public void setPersist(CustomerAttribute other) {
		corpName = other.getCorpName();
		corpDispName = other.getCorpDispName();
		applyStartDate = other.getApplyStartDate();
		address = other.getAddress();
		name = other.getName();
		contact = other.getContact();
		honorific = other.getHonorific();
		billing = other.getBilling();
		payment = other.getPayment();
		includeTax = other.getIncludeTax();
	}
}
