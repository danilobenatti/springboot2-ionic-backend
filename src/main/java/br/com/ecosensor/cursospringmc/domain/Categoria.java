package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_category", uniqueConstraints = @UniqueConstraint(name = "uk_category__name", columnNames = "col_name"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Builder
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_category")
	private Integer id;
	
	@Column(name = "col_name", length = 45, nullable = false)
	private String name;
	
	@Builder.Default
	@ManyToMany(mappedBy = "categories")
	private List<Produto> products = new ArrayList<>();
	
	public Categoria(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
