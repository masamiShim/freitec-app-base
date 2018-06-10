package com.freitech.kotetsu.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.freitech.kotetsu.models.customer.Customer;
import com.freitech.kotetsu.models.item.Item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 単価
 * @author shimbuichi
 *
 */
@Data
@Entity
@ToString(exclude = {"item", "customer"})
@EqualsAndHashCode(exclude = {"item", "customer"}, callSuper = false)
@Table(name = "UnitPrice")
public class UnitPrice extends SecurityAuditor implements PriceBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UnitPriceId")
	private Long id;

	@NotNull
	@ManyToOne
	private Item item;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;

	@Column(name = "Resume")
	private String resume;

	@NotNull
	@Column(name = "Price")
	private BigDecimal price;

	@NotNull
	@Column(name = "AppliedDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate appliedDate;

	public void setPersistInfo(UnitPrice other) {
		item = other.getItem();
		customer = other.getCustomer();
		resume = other.getResume();
		price = other.getPrice();
		appliedDate = other.getAppliedDate();
	}

}
