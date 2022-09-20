package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "order", "product" })
@Embeddable
@Table(name = "tbl_orderItem")
public class ItemPedidoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_order", nullable = false,
	foreignKey = @ForeignKey(name = "fk_orderitem__idorder",
	foreignKeyDefinition = "foreign key (id_order) references tbl_order(id_order) on delete cascade"))
	private Pedido order;
	
	@ManyToOne
	@JoinColumn(name = "id_product", nullable = false,
	foreignKey = @ForeignKey(name = "fk_orderitem__idproduct",
	foreignKeyDefinition = "foreign key (id_product) references tbl_product(id_product) on delete cascade"))
	private Produto product;
	
}
