package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "tbl_city",
		uniqueConstraints = @UniqueConstraint(name = "uk_city__name_idstate",
				columnNames = { "col_name", "col_id_estate" }))
@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_city")
	private Integer id;
	
	@Column(name = "col_name", length = 85, nullable = false)
	private String name;
	
	@JsonManagedReference
	@ManyToOne(targetEntity = Estado.class, optional = false)
	@JoinColumn(name = "col_id_estate", nullable = false,
			foreignKey = @ForeignKey(name = "fk_city__idestate",
					foreignKeyDefinition = "foreign key (col_id_estate) references tbl_estate(id_estate) on delete cascade"))
	private Estado estate;
	
}
