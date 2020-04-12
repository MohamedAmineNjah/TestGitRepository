/*
 * Copyright 2017 by Walid YAICH <walid.yaich@esprit.tn>
 * This is an Open Source Software
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3
 */

package tn.esprit.spring.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long barCode;
    private int price;
    
    private Type type;
    private Category category;
   
    
    @Temporal(TemporalType.DATE)
    private Date exprdate;
    
  
    @JsonIgnore 
    @ManyToOne
    private Ray ray;
    
    public Product() {
	}


public Ray getRay() {
		return ray;
	}


	public void setRay(Ray ray) {
		this.ray = ray;
	}


public Product(Long id, String name, Long barCode, int price, Type type, Category category, Date exprdate) {
	super();
	this.id = id;
	this.name = name;
	this.barCode = barCode;
	this.price = price;
	this.type = type;
	this.category = category;
	this.exprdate = exprdate;
}


public Product(String name, Long barCode, int price, Type type, Category category, Date exprdate) {
	super();
	this.name = name;
	this.barCode = barCode;
	this.price = price;
	this.type = type;
	this.category = category;
	this.exprdate = exprdate;
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


public Long getBarCode() {
	return barCode;
}


public void setBarCode(Long barCode) {
	this.barCode = barCode;
}


public int getPrice() {
	return price;
}


public void setPrice(int price) {
	this.price = price;
}


public Type getType() {
	return type;
}


public void setType(Type type) {
	this.type = type;
}


public Category getCategory() {
	return category;
}


public void setCategory(Category category) {
	this.category = category;
}


public Date getExprdate() {
	return exprdate;
}


public void setExprdate(Date exprdate) {
	this.exprdate = exprdate;
}


@Override
public String toString() {
	return "Product [id=" + id + ", name=" + name + ", barCode=" + barCode + ", price=" + price + ", type=" + type
			+ ", category=" + category + ", exprdate=" + exprdate + "]";
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
	result = prime * result + ((category == null) ? 0 : category.hashCode());
	result = prime * result + ((exprdate == null) ? 0 : exprdate.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + price;
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Product other = (Product) obj;
	if (barCode == null) {
		if (other.barCode != null)
			return false;
	} else if (!barCode.equals(other.barCode))
		return false;
	if (category != other.category)
		return false;
	if (exprdate == null) {
		if (other.exprdate != null)
			return false;
	} else if (!exprdate.equals(other.exprdate))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (price != other.price)
		return false;
	if (type != other.type)
		return false;
	return true;
}
    
    
    
}