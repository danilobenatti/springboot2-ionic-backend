package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Table(name = "tbl_product", 
	uniqueConstraints = 
		@UniqueConstraint(name = "uk_product__name", columnNames = "col_name"))
@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Integer id;
	
	@Column(name = "col_name", length = 150, nullable = false)
	private String name;
	
	@Column(name = "col_price", precision = 8, scale = 2)
	private Double price;
	
	@JsonBackReference
	@Builder.Default
	@ManyToMany
	@JoinTable(name = "tbl_product__category", 
		uniqueConstraints = @UniqueConstraint(name = "uk_idproduct__idcategory", 
			columnNames = {"id_product", "id_category"}), 
		joinColumns = @JoinColumn(name = "id_product", nullable = false, 
			foreignKey = @ForeignKey(name = "fk_product__idproduct", 
				foreignKeyDefinition = "foreign key (id_product) references tbl_product(id_product) on delete cascade")), 
		inverseJoinColumns = @JoinColumn(name = "id_category", nullable = false, 
			foreignKey = @ForeignKey(name = "fk_product__idcategory", 
				foreignKeyDefinition = "foreign key (id_category) references tbl_category(id_category) on delete cascade")))
	private List<Categoria> categories = new ArrayList<>();

	public Produto(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
}
