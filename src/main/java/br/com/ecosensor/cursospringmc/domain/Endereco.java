package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "tbl_address")
@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_address")
	private Integer id;
	
	@Column(name = "col_street", length = 150, nullable = false)
	private String street;
	
	@Column(name = "col_number", length = 25, nullable = false)
	private String number;
	
	@Column(name = "col_complement", length = 45)
	private String complement;
	
	@Column(name = "col_district", length = 80, nullable = false)
	private String district;
	
	@Column(name = "col_zipcode", length = 10)
	private String zipCode;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Cliente.class, optional = false,
			cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(
			name = "fk_address__idclient",
			foreignKeyDefinition = "foreign key (id_client) references tbl_client(id_client) on delete cascade"))
	private Cliente client;
	
	@ManyToOne(targetEntity = Cidade.class, optional = false,
			cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_city", nullable = false, foreignKey = @ForeignKey(
			name = "fk_address__idcity",
			foreignKeyDefinition = "foreign key (id_city) references tbl_city(id_city) on delete cascade"))
	private Cidade city;
}
