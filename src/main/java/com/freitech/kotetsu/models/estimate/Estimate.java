package com.freitech.kotetsu.models.estimate;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.freitech.kotetsu.models.Resume;
import com.freitech.kotetsu.models.SecurityAuditor;
import com.freitech.kotetsu.models.Staff;
import com.freitech.kotetsu.models.customer.Customer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
@Data
@Entity
@Table(name = "Estimate")
public class Estimate extends SecurityAuditor {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "estimateId")
	private Long id;

	/** 見積番号 */
	@NotNull
	@Column(name = "no")
	private String no;

	/** タイトル */
	@Column(name = "title")
	private String title;

	/** 顧客番号 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	private Customer customer;

	/** 担当者 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffId")
	private Staff staff;

	/** 見積日 */
	@Column(name = "date")
	private LocalDate date;

	/** 見積期限 */
	@Column(name = "expired")
	private LocalDate expired;

	/** 前回見積番号 */
	@Column(name = "prevEstimateId")
	private String prevEstimateId;

	@OneToMany(mappedBy = "estimate", cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "estimateDetailId")
	private List<EstimateDetail> details;

	@OneToMany
	@JoinColumn(name = "resumeId")
	private List<Resume> resumes;

	public void setPersistInfo(Estimate other) {
		no = other.getNo();
		title = other.getTitle();
		customer = other.getCustomer();
		expired = other.getExpired();
		prevEstimateId = other.getPrevEstimateId();
		details = other.getDetails();
	}
	
}
