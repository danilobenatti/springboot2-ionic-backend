package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Table(name = "tbl_estate",
	uniqueConstraints = {
			@UniqueConstraint(name = "uk_estate__name",
				columnNames = "col_name"),
			@UniqueConstraint(name = "uk_estate__uf", columnNames = "col_uf") })
@Entity
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estate")
	private Integer id;
	
	@Column(name = "col_name", length = 45, nullable = false)
	private String name;
	
	@Column(name = "col_uf", length = 2, nullable = false)
	private String uf;
	
	@JsonBackReference
	@Builder.Default
	@OneToMany(mappedBy = "estate")
	private List<Cidade> cities = new ArrayList<>();
	
	public Estado(Integer id, String name, String uf) {
		super();
		this.id = id;
		this.name = name;
		this.uf = uf;
	}
	
}
