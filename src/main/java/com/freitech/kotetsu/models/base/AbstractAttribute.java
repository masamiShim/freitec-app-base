package com.freitech.kotetsu.models.base;

import java.time.LocalDate;

import javax.persistence.Column;

import com.freitech.kotetsu.core.domain.Amount;
import com.freitech.kotetsu.models.SecurityAuditor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractAttribute extends SecurityAuditor {

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "FirstNameKana")
	private String firstNameKana;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "LastNameKana")
	private String lastNameKana;

	@Column(name = "EstablishDate")
	private LocalDate establishDate;

	@Column(name = "Fund")
	private Amount fund;

	@Column(name = "ZipCode")
	private String zipCode;

	@Column(name = "PhoneNum")
	private String phoneNum;

	@Column(name = "FaxNum")
	private String faxNum;

	@Column(name = "Email")
	private String email;

	@Column(name = "Address1")
	private String address1;

	@Column(name = "Address2")
	private String address2;

	@Column(name = "Address3")
	private String address3;

	@Column(name = "CutoffDay")
	private String cutoffDay;

	@Column(name = "Remark1")
	private String remark1;

	@Column(name = "Remark2")
	private String remark2;
}
