package com.freitech.kotetsu.models.estimate;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.freitech.kotetsu.codes.SectionType;
import com.freitech.kotetsu.models.SecurityAuditor;
import com.freitech.kotetsu.models.item.Item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "EstimateDetail")
@ToString(exclude = "estimate")
@EqualsAndHashCode(callSuper = false, exclude = "estimate")
public class EstimateDetail extends SecurityAuditor {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "estimateDetailId")
	private Long id;

	/** 順序  */
	@Column(name = "ordinary")
	private int ordinary;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estimateId")
	private Estimate estimate;

	@Column(name = "section")
	private SectionType section;

	@Column(name = "content")
	private String content;

	/** 商品 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemId")
	private Item item;

	/** 価格 */
	@Column(name = "price")
	private BigDecimal price;

	/** 個数 */
	@Column(name = "quantity")
	private int quantity;

	public void setPersistInfo(EstimateDetail other) {

	}
}
