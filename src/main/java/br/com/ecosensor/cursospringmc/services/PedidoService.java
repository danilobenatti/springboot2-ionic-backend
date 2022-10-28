package br.com.ecosensor.cursospringmc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecosensor.cursospringmc.domain.ItemPedido;
import br.com.ecosensor.cursospringmc.domain.PagamentoComBoleto;
import br.com.ecosensor.cursospringmc.domain.Pedido;
import br.com.ecosensor.cursospringmc.domain.Produto;
import br.com.ecosensor.cursospringmc.domain.enums.EstadoPagamento;
import br.com.ecosensor.cursospringmc.repositories.ItemPedidoRepository;
import br.com.ecosensor.cursospringmc.repositories.PagamentoRepository;
import br.com.ecosensor.cursospringmc.repositories.PedidoRepository;
import br.com.ecosensor.cursospringmc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido findOrderById(Integer id) {
		Optional<Pedido> order = repository.findById(id);
		return order.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id
						+ ", Type: " + Pedido.class.getSimpleName()));
	}
	
	@Transactional
	public Pedido insertOrder(Pedido obj) {
		obj.setClient(clienteService.findClientById(obj.getClient().getId()));
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setStatus(EstadoPagamento.PENDENTE);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PagamentoComBoleto) {
			PagamentoComBoleto boleto = (PagamentoComBoleto) obj.getPayment();
			boletoService.preencherPagamentoComBoleto(boleto, obj.getInstant());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPayment());
		for (ItemPedido item : obj.getItems()) {
			Produto product = produtoService
					.findProductById(item.getProduct().getId());
			item.setProduct(product);
			item.setDiscount(item.getDiscount());
			item.setProductPrice(product.getUnitPrice());
			item.setOrder(obj);
		}
		itemPedidoRepository.saveAll(obj.getItems());
		System.out.println(obj);
		return obj;
	}
}
