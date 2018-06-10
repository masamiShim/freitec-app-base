package com.freitech.kotetsu.models.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.Length;

import com.freitech.kotetsu.core.domain.Amount;
import com.freitech.kotetsu.models.SecurityAuditor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table(name = "Customer")
@Entity
@Data
@EqualsAndHashCode(exclude = "attributes",callSuper = false)
@ToString(exclude = "attributes")
public class Customer extends SecurityAuditor {

	private static final long serialVersionUID = 1L;

	/** 企業番号 */
	@Length(min = 1, max = 10)
	@Column(name = "CustomerNo")
	private String customerNo;

	/** 設立日 */
	@Column(name = "EstablishDate")
	private LocalDate establishDate;

	/** 資本金 */
	@Column(name = "Fund")
	private Amount fund;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	List<CustomerAttribute> attributes = new ArrayList<>();

	@Transient
	CustomerAttribute attribute;

	private Optional<CustomerAttribute> findAttributeById(Long id) {
		if (CollectionUtils.isNotEmpty(attributes)) {
			return attributes.stream().filter(attr -> attr.getId() == id).findFirst();
		}
		return Optional.empty();
	}

	public CustomerAttribute getAttributes() {
		if (CollectionUtils.isNotEmpty(attributes)) {
			return attributes.stream().sorted(Comparator.comparing(
			  CustomerAttribute::getApplyStartDate)
			  .reversed())
			  .findFirst().orElse(new CustomerAttribute());
		}
		return new CustomerAttribute();
	}

	public void merge(Customer attr) {
		attr.getAttribute().setCustomer(this);
		if (attr.getAttribute().getId() != null) {
			findAttributeById(attr.getAttribute().getId()).ifPresent(prev -> {
				prev.setPersist(attr.getAttribute());
			});
		}
		else {
			attributes.add(attr.getAttribute());
		}
	}

	public CustomerAttribute getLatestAttribute() {
		if (CollectionUtils.isNotEmpty(attributes)) {
			return attributes.stream().sorted(Comparator.comparing(CustomerAttribute::getApplyStartDate)
			  .reversed())
			  .findFirst().orElse(null);
		}
		return null;
	}

	public Optional<CustomerAttribute> getLatestAttribute(LocalDate targetDate) {
		if (CollectionUtils.isNotEmpty(attributes)) {
			return attributes.stream().filter(attr -> attr.getApplyStartDate().isBefore(targetDate))
			  .sorted(Comparator.comparing(CustomerAttribute::getApplyStartDate).reversed()).findFirst();
		}
		return Optional.empty();
	}

	/**
	 * 適用開始日があとの場合、新しく情報を作成する。
	 * 
	 * @param other
	 */
	public void setPersistInfo(Customer other) {
		// FIXME:あんまり好きじゃないけど
		CustomerAttribute otherAttribute = other.getLatestAttribute();
		this.getLatestAttribute(otherAttribute.getApplyStartDate()).ifPresent(ca -> {
			if (ca.getApplyStartDate().isEqual(otherAttribute.getApplyStartDate())) {
				map(ca, otherAttribute);
			}
			else {
				this.attributes.add(map(new CustomerAttribute(), otherAttribute));
			}
		});
	}

	private CustomerAttribute map(CustomerAttribute target, CustomerAttribute otherAttribute) {
		target.setAddress(otherAttribute.getAddress());
		target.setAddress(otherAttribute.getAddress());
		target.setBilling(otherAttribute.getBilling());
		target.setPayment(otherAttribute.getPayment());
		return target;
	}
}
