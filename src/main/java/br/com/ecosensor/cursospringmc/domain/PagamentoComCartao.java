package br.com.ecosensor.cursospringmc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.ecosensor.cursospringmc.domain.enums.EstadoPagamento;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "tbl_payment__creditcard")
@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "col_number_of_installments")
	private Integer numberOfInstallments;
	
	public PagamentoComCartao(Integer id, EstadoPagamento status, Pedido order,
			Integer numberOfInstallments) {
		super(id, status, order);
		this.setNumberOfInstallments(numberOfInstallments);
	}
	
}
