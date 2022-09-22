package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Table(name = "tbl_ordered__item")
@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	@Column(name = "col_discount")
	private Double discount;
	
	@Column(name = "col_quantity")
	private Integer quantity;
	
	@Column(name = "col_subtotal_price")
	private Double subTotalPrice;
	
	public ItemPedido(Pedido order, Produto product, Double discount,
			Integer quantity, Double subTotalPrice) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.subTotalPrice = subTotalPrice;
	}
	
	@JsonIgnore
	public Pedido getOrder() {
		return id.getOrder();
	}
	
	public Produto getProduct() {
		return id.getProduct();
	}
	
}
