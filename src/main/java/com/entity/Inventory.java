package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="Inventory")
@Builder
public class Inventory {
	
	@Id
	Long productId;
	
	@Min(value=0,message="availableQuantity should be non negative")
	Long availableQuantity;
	
	Long minQuantity;

	Long maxQuantity;
	
	Long totalQuantity;

	Long returnQuantity;

	String manufacturer;

	public Inventory(Long productId, Long availableQuantity, Long minQuantity, Long maxQuantity, Long totalQuantity,
			Long returnQuantity, String manufacturer) {
		super();
		this.productId = productId;
		this.availableQuantity = availableQuantity;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
		this.totalQuantity = totalQuantity;
		this.returnQuantity = returnQuantity;
		this.manufacturer = manufacturer;
	}
}
