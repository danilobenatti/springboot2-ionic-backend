package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "col_instant")
	private Date instant;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Pagamento payment;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_client", nullable = false,
		foreignKey = @ForeignKey(name= "fk_order__idclient",
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
}
