package br.com.ecosensor.cursospringmc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import br.com.ecosensor.cursospringmc.domain.enums.PerfilUsuario;
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

@Service()
public class DBService {
	
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = Categoria.builder().name("Copa e Cozinha").build();
		Categoria cat2 = Categoria.builder().name("Material Escolar").build();
		Categoria cat3 = Categoria.builder().name("Eletro-eletrônicos").build();
		Categoria cat4 = Categoria.builder().name("Casa e Jardim").build();
		Categoria cat5 = Categoria.builder().name("Lar e Conforto").build();
		Categoria cat6 = Categoria.builder().name("Cosméticos").build();
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
		Produto p4 = Produto.builder().name("Regador 5Litros BomJardim")
				.unitPrice(50.00).build();
		Produto p5 = Produto.builder().name("Perfume KaiaK Oceano Natura 100ml")
				.unitPrice(125.00).build();
		Produto p6 = Produto.builder().name("Rádio AM/FM Sony")
				.unitPrice(210.00).build();
		Produto p7 = Produto.builder().name("Quadro para sala de estar")
				.unitPrice(500.00).build();
		Produto p8 = Produto.builder().name("Adubo fertilizante para roseiras")
				.unitPrice(7.00).build();
		Produto p9 = Produto.builder().name("Brinco Estelar Biju")
				.unitPrice(10.00).build();
		Produto p10 = Produto.builder().name("Colar artesanal Mão-de-Mãe")
				.unitPrice(5.70).build();
		Produto p11 = Produto.builder().name("Esmalte vermelho cereja 8ml")
				.unitPrice(3.50).build();
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2));
		cat2.getProducts().add(p3);
		cat3.getProducts().add(p6);
		cat4.getProducts().addAll(Arrays.asList(p4, p8));
		cat5.getProducts().add(p7);
		cat6.getProducts().addAll(Arrays.asList(p5, p11));
		cat7.getProducts().addAll(Arrays.asList(p9, p10));
		
		p1.getCategories().add(cat1);
		p2.getCategories().add(cat1);
		p3.getCategories().add(cat2);
		p4.getCategories().add(cat4);
		p5.getCategories().add(cat6);
		p6.getCategories().add(cat3);
		p7.getCategories().add(cat5);
		p8.getCategories().add(cat4);
		p9.getCategories().add(cat7);
		p10.getCategories().add(cat7);
		p11.getCategories().add(cat6);
		
		produtoRepository.saveAll(
				Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = Estado.builder().name("Goiás").uf("GO").build();
		Estado est2 = Estado.builder().name("Mato Grosso").uf("MT").build();
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		Cidade c1 = Cidade.builder().name("Goiânia").estate(est1).build();
		Cidade c2 = Cidade.builder().name("Cuiabá").estate(est2).build();
		Cidade c3 = Cidade.builder().name("Sorriso").estate(est2).build();
		
		est1.getCities().add(c1);
		est2.getCities().addAll(Arrays.asList(c2, c3));
		
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		List<Cliente> clients = new ArrayList<>();
		
		Cliente cli1 = Cliente.builder().name("Pedro Augusto")
				.email("danilonb@ecosensor.com.br").cpfCnpj("49959308000158")
				.type(TipoCliente.PESSOAJURIDICA.getCode())
				.password(passwordEncoder.encode("123456")).imageUrl(null)
				.build();
		cli1.getPhones().addAll(Arrays.asList("9998893322", "8898894455"));
		cli1.addProfile(PerfilUsuario.CLIENT);
		clients.add(cli1);
		
		Cliente cli2 = Cliente.builder().name("Claudomiro Costa")
				.email("danilobenatti@yahoo.com.br").cpfCnpj("10505228009")
				.type(TipoCliente.PESSOAFISICA.getCode())
				.password(passwordEncoder.encode("123456")).imageUrl(null)
				.build();
		cli2.getPhones().add("9938383933");
		cli2.addProfile(PerfilUsuario.CLIENT);
		cli2.addProfile(PerfilUsuario.ADMIN);
		clients.add(cli2);
		
		clienteRepository.saveAll(clients);
		
		List<Endereco> address = new ArrayList<>();
		
		Endereco e1 = Endereco.builder().street("Rua Pedro Moreira")
				.number("1020").complement("AP5").district("Centro")
				.zipCode("99000555").client(cli1).city(c1).build();
		address.add(e1);
		Endereco e2 = Endereco.builder().street("Avenida Cabral Neto")
				.number("88").district("Jardim Esplanada").zipCode("45520123")
				.client(cli1).city(c2).build();
		address.add(e2);
		Endereco e3 = Endereco.builder().street("Rua dos Alecrins")
				.number("301").district("Nova Vista").zipCode("18500900")
				.client(cli2).city(c3).build();
		address.add(e3);
		
		cli1.getAddresses().addAll(Arrays.asList(e1, e2));
		cli2.getAddresses().add(e3);
		
		enderecoRepository.saveAll(address);
		
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
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 3);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 5.00, 2);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 10.00, 5);
		
		ped1.getItems().addAll(Arrays.asList(ip1, ip2));
		ped2.getItems().add(ip3);
		
		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
	
}
