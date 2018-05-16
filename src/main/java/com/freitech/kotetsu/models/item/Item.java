package com.freitech.kotetsu.models.item;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.freitech.kotetsu.codes.ItemType;
import com.freitech.kotetsu.codes.Unit;
import com.freitech.kotetsu.models.SecurityAuditor;
import com.freitech.kotetsu.models.UnitPrice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Item")
public class Item extends SecurityAuditor {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "itemId")
	private Long id;

	/** 品番 */
	@Column(name = "itemNo")
	private String itemNo;

	/** 品名 */
	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private ItemType type;

	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<UnitPrice> price;

	@Enumerated(EnumType.STRING)
	@Column(name = "unit")
	private Unit unit;

	public void setPersistInfo(Item other) {
		itemNo = other.getItemNo();
		name = other.getName();
		type = other.getType();
		unit = other.getUnit();
	}
}
