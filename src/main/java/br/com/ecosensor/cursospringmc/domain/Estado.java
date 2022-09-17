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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_estate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
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
	
	@Builder.Default
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<>();
	
	public Estado(Integer id, String name, String uf) {
		super();
		this.id = id;
		this.name = name;
		this.uf = uf;
	}
	
}
