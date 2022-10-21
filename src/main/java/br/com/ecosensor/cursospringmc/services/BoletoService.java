package br.com.ecosensor.cursospringmc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.ecosensor.cursospringmc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto boleto,
			Date instant) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instant);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		boleto.setExpirationDate(calendar.getTime());
	}
	
}
