package br.com.alura.jms.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Boolean ebook;
	private BigDecimal price;
	
	public Cart(Long id, Boolean ebook, BigDecimal price) {
		this.id = id;
		this.ebook = ebook;
		this.price = price;
	}
	
	public Boolean getEbook() {
		return ebook;
	}
	
	public Long getId() {
		return id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setEbook(Boolean ebook) {
		this.ebook = ebook;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cart {id=" + id + ", ebook=" + ebook + ", price=" + price + "}";
	}
	
}
