package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ecosensor.cursospringmc.domain.enums.TipoCliente;
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
@EqualsAndHashCode(of = "id")
@Builder
@Table(name = "tbl_client", uniqueConstraints = {
		@UniqueConstraint(name = "uk_client__email", columnNames = "col_email"),
		@UniqueConstraint(name = "uk_client__cpfcnpj",
			columnNames = "col_cpfcnpj") })
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private Integer id;
	
	@Column(name = "col_name", nullable = false)
	private String name;
	
	@Column(name = "col_email", nullable = false)
	private String email;
	
	@Column(name = "col_cpfcnpj", nullable = true)
	private String cpfCnpj;
	
	@Column(name = "col_type", nullable = true)
	private Integer type;
	
	@JsonManagedReference
	@Builder.Default
	@OneToMany(mappedBy = "client")
	private List<Endereco> addresses = new ArrayList<>();
	
	@Builder.Default
	@ElementCollection
	@CollectionTable(name = "tbl_phone",
		uniqueConstraints = @UniqueConstraint(name = "uk_phone__number",
			columnNames = { "col_phonenumber" }),
		foreignKey = @ForeignKey(name = "fk_phone__idclient",
			foreignKeyDefinition = "foreign key (cliente_id_client) references tbl_client(id_client) on delete cascade"))
	@Column(name = "col_phonenumber", length = 20, nullable = false)
	private Set<String> phones = new HashSet<>();
	
	@JsonBackReference
	@Builder.Default
	@OneToMany(mappedBy = "client")
	private List<Pedido> orders = new ArrayList<>();
	
	public Cliente(Integer id, String name, String email, String cpfCnpj,
			TipoCliente type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.type = (type != null) ? type.getCode() : null;
	}
	
	public TipoCliente getClientType() {
		return TipoCliente.toEnum(type);
	}
	
	public void setClientType(TipoCliente type) {
		this.type = type.getCode();
	}
	
}
