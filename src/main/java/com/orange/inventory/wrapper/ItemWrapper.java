package com.orange.inventory.wrapper;

import java.util.Date;

public class ItemWrapper {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private Date purchase_date;
	
	private Long owner_id;
	private String owner_first_name;
	private String owner_last_name;
	private String owner_email;
	
	
	public ItemWrapper(Long id,String name,String description,Double price,Date purchase_date,Long owner_id,String owner_first_name,String owner_last_name,String owner_email) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.purchase_date = purchase_date;
		
		this.owner_id = owner_id;
		this.owner_first_name = owner_first_name;
		this.owner_last_name = owner_last_name;
		this.owner_email = owner_email;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	public Long getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}
	public String getOwner_first_name() {
		return owner_first_name;
	}
	public void setOwner_first_name(String owner_first_name) {
		this.owner_first_name = owner_first_name;
	}
	public String getOwner_last_name() {
		return owner_last_name;
	}
	public void setOwner_last_name(String owner_last_name) {
		this.owner_last_name = owner_last_name;
	}
	public String getOwner_email() {
		return owner_email;
	}
	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}
	
	
	
}
