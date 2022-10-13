package br.com.ecosensor.cursospringmc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import br.com.ecosensor.cursospringmc.domain.Cidade;
import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.domain.Endereco;
import br.com.ecosensor.cursospringmc.domain.Estado;
import br.com.ecosensor.cursospringmc.domain.ItemPedido;
import br.com.ecosensor.cursospringmc.domain.Pagamento;
import br.com.ecosensor.cursospringmc.domain.PagamentoComBoleto;
import br.com.ecosensor.cursospringmc.domain.PagamentoComCartao;
import br.com.ecosensor.cursospringmc.domain.Pedido;
import br.com.ecosensor.cursospringmc.domain.Produto;
import br.com.ecosensor.cursospringmc.domain.enums.EstadoPagamento;
import br.com.ecosensor.cursospringmc.domain.enums.TipoCliente;
import br.com.ecosensor.cursospringmc.repositories.CategoriaRepository;
import br.com.ecosensor.cursospringmc.repositories.CidadeRepository;
import br.com.ecosensor.cursospringmc.repositories.ClienteRepository;
import br.com.ecosensor.cursospringmc.repositories.EnderecoRepository;
import br.com.ecosensor.cursospringmc.repositories.EstadoRepository;
import br.com.ecosensor.cursospringmc.repositories.ItemPedidoRepository;
import br.com.ecosensor.cursospringmc.repositories.PagamentoRepository;
import br.com.ecosensor.cursospringmc.repositories.PedidoRepository;
import br.com.ecosensor.cursospringmc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringmcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringmcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = Categoria.builder().name("Copa e Cozinha").build();
		Categoria cat2 = Categoria.builder().name("Material Escolar").build();
		Categoria cat3 = Categoria.builder().name("Eletrônicos").build();
		Categoria cat4 = Categoria.builder().name("Jardinagem").build();
		Categoria cat5 = Categoria.builder().name("Decoração").build();
		Categoria cat6 = Categoria.builder().name("Perfumaria").build();
		Categoria cat7 = Categoria.builder().name("Acessórios e Jóias").build();
		
		List<Categoria> asList = Arrays.asList(cat1, cat2, cat3, cat4, cat5,
				cat6, cat7);
		
		categoriaRepository.saveAll(asList);
		
		Produto p1 = Produto.builder().name("Copo Descartável 100und")
				.unitPrice(20.00).build();
		Produto p2 = Produto.builder().name("Guardanapo Papel 50und")
				.unitPrice(15.00).build();
		Produto p3 = Produto.builder().name("Régua Escalímetro Triangular 30cm")
				.unitPrice(50.00).build();
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2));
		cat2.getProducts().add(p3);
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1));
		p3.getCategories().addAll(Arrays.asList(cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = Estado.builder().name("Goiás").uf("GO").build();
		Estado est2 = Estado.builder().name("Mato Grosso").uf("MT").build();
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		Cidade c1 = Cidade.builder().name("Goiânia").estate(est1).build();
		Cidade c2 = Cidade.builder().name("Cuiabá").estate(est2).build();
		Cidade c3 = Cidade.builder().name("Sorriso").estate(est2).build();
		
		est1.getCities().add(c1);
		est2.getCities().addAll(Arrays.asList(c2, c3));
		
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = Cliente.builder().name("Pedro Augusto")
				.email("augusto@email.com").cpfCnpj("49959308000158")
				.type(TipoCliente.PESSOAJURIDICA.getCode()).build();
		
		cli1.getPhones().addAll(Arrays.asList("9998893322", "8898894455"));
		
		clienteRepository.save(cli1);
		
		Endereco e1 = Endereco.builder().street("Rua Pedro Moreira")
				.number("1020").complement("AP5").district("Centro")
				.zipCode("99000555").client(cli1).city(c1).build();
		Endereco e2 = Endereco.builder().street("Avenida Cabral Neto")
				.number("88").district("Jardim Esplanada").zipCode("45520123")
				.client(cli1).city(c2).build();
		
		cli1.getAddresses().addAll(Arrays.asList(e1, e2));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = Pedido.builder().instant(df.parse("15/12/2021 09:23"))
				.client(cli1).deliveryAddress(e1).build();
		
		Pedido ped2 = Pedido.builder().instant(df.parse("27/09/2022 15:46"))
				.client(cli1).deliveryAddress(e2).build();
		
		Pagamento pagto1 = PagamentoComCartao.builder().order(ped1)
				.numberOfInstallments(3).build();
		pagto1.setStatus(EstadoPagamento.QUITADO);
		ped1.setPayment(pagto1);
		
		Pagamento pagto2 = PagamentoComBoleto.builder().order(ped2)
				.expirationDate(df.parse("20/01/2022 00:00")).payDay(null)
				.build();
		pagto2.setStatus(EstadoPagamento.PENDENTE);
		ped2.setPayment(pagto2);
		
		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 3, 60.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 5.00, 2, 100.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 10.00, 5, 50.00);
		
		ped1.getItems().addAll(Arrays.asList(ip1, ip2));
		ped2.getItems().add(ip3);
		
		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
	
}
