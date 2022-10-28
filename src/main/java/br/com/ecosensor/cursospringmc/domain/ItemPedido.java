package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

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
	
	@JsonIgnore
	@Column(name = "col_product_price")
	private Double productPrice;
	
	public ItemPedido(Pedido order, Produto product, Double discount,
			Integer quantity) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.productPrice = product.getUnitPrice();
	}
	
	@JsonIgnore
	public Pedido getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Pedido order) {
		id.setOrder(order);
	}
	
	public Produto getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Produto product) {
		id.setProduct(product);
	}
	
	public Double getSubTotalProductPrice() {
		return (1 - discount / 100) * (productPrice * quantity);
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat
				.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("\tProduct: ");
		builder.append(getProduct().getName());
		builder.append(", Qtd: ");
		builder.append(getQuantity());
		builder.append(", Price: ");
		builder.append(nf.format(getProduct().getUnitPrice()));
		builder.append(", Discount: ");
		builder.append(getDiscount());
		builder.append(", SubTotal: ");
		builder.append(nf.format(getSubTotalProductPrice()));
		builder.append("\n");
		return builder.toString();
	}
	
}
