package br.com.ecosensor.cursospringmc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.ecosensor.cursospringmc.domain.enums.EstadoPagamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "tbl_payment_slipbank")
@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "col_expiration_date")
	private Date expirationDate;
	
	@Column(name = "col_pay_day")
	private Date payDay;
	
	public PagamentoComBoleto(Integer id, EstadoPagamento status, Pedido order,
			Date expirationDate, Date payDay) {
		super(id, status, order);
		this.setExpirationDate(expirationDate);
		this.setPayDay(payDay);
	}
	
}
