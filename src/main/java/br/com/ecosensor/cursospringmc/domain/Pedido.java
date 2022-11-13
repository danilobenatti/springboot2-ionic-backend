package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Builder
@Table(name = "tbl_order")
@Entity(name = "order")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "col_instant")
	private Date instant;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Pagamento payment;
	
	@ManyToOne
	@JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(
		name = "fk_order__idclient",
		foreignKeyDefinition = "foreign key (id_client) references tbl_client(id_client) on delete cascade"))
	private Cliente client;
	
	@ManyToOne
	@JoinColumn(name = "id_deliveryaddress", nullable = false,
		foreignKey = @ForeignKey(name = "fk_order__idaddress",
			foreignKeyDefinition = "foreign key (id_deliveryaddress) references tbl_address(id_address) on delete cascade"))
	private Endereco deliveryAddress;
	
	@Builder.Default
	@OneToMany(mappedBy = "id.order")
	private Set<ItemPedido> items = new HashSet<>();
	
	public Double getTotalOrderPrice() {
		double total = 0.0;
		for (ItemPedido itemPedido : items) {
			total += itemPedido.getSubTotalProductPrice();
		}
		return total;
	}
	
	@Override
	public String toString() {
		Locale inLocale = new Locale("pt", "BR");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		NumberFormat nf = NumberFormat.getCurrencyInstance(inLocale);
		StringBuilder builder = new StringBuilder();
		builder.append("Order Number: ");
		builder.append(getId());
		builder.append(", Instant: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Client: ");
		builder.append(getClient().getName());
		builder.append(", Status: ");
		builder.append(getPayment().getStatus());
		builder.append("\nDetails:\n");
		for (ItemPedido itemPedido : items) {
			builder.append(itemPedido.toString());
		}
		builder.append("Total: " + nf.format(getTotalOrderPrice()));
		return builder.toString();
	}
	
}
