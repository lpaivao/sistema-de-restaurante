package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.entidades.Itens;
import model.entidades.Produtos;
import model.entidades.Vendas;
import model.gerenciamento.Cardapio;
import model.gerenciamento.GerEstoque;
import model.gerenciamento.GerFornecedores;
import model.gerenciamento.GerProdutos;
import model.gerenciamento.GerVendas;

public class TesteGerVendas {

	private Cardapio c;
	private GerVendas v;
	private GerProdutos p;
	private GerFornecedores forn;
	private GerEstoque estoque;

	private LinkedList<Itens> listIPedido;
	private LinkedList<Produtos> pAux1, pAux2;
	private LinkedList<Produtos> pItens;
	private LinkedList<Produtos> pItens2;

	private Produtos p0, p1, p2;
	private Produtos p3, p4, p5;

	private Produtos aux0, aux1;

	@Before
	public void setUp() throws Exception {

		estoque = new GerEstoque();

		c = new Cardapio();
		forn = new GerFornecedores();
		p = new GerProdutos();
		v = new GerVendas();
		estoque = new GerEstoque();

		forn.add("Frios distribuidora", "12345", "Rua A", new LinkedList<Produtos>());
		forn.add("Carnes distribuidora", "54321", "Rua B", new LinkedList<Produtos>());

		p0 = new Produtos("PROD0", "Queijo", 15.0, "22/11/2023", 5.0, forn.getFornecedores().get(0));
		p1 = new Produtos("PROD1", "Queijo", 20.0, "22/11/2022", 7.0, forn.getFornecedores().get(0));
		p2 = new Produtos("PROD2", "Queijo", 10.0, "22/11/2021", 10.0, forn.getFornecedores().get(0));
		p3 = new Produtos("PROD3", "Calabresa", 20.0, "23/11/2022", 4.0, forn.getFornecedores().get(1));
		p4 = new Produtos("PROD4", "Calabresa", 30.0, "22/11/2022", 6.0, forn.getFornecedores().get(1));
		p5 = new Produtos("PROD5", "Calabresa", 40.0, "21/11/2022", 9.0, forn.getFornecedores().get(1));

		pAux1 = new LinkedList<Produtos>();
		pAux2 = new LinkedList<Produtos>();

		pAux1.add(p0);
		pAux1.add(p1);
		pAux1.add(p2);
		pAux2.add(p3);
		pAux2.add(p4);
		pAux2.add(p5);

		forn.getFornecedores().get(0).setProdutos(pAux1);
		forn.getFornecedores().get(1).setProdutos(pAux2);

		// Parte do cardapio

		listIPedido = new LinkedList<Itens>();
		pItens = new LinkedList<Produtos>();

		pItens2 = new LinkedList<Produtos>();

		aux0 = new Produtos("Queijo", 10.0);
		aux1 = new Produtos("Calabresa", 10.0);

		Produtos aux2 = new Produtos("Queijo", 5.0);
		Produtos aux3 = new Produtos("Calabresa", 5.0);

		pItens.add(aux0);
		pItens.add(aux1);

		pItens2.add(aux2);
		pItens2.add(aux3);

		c.addItens("Pizza de Calabresa Grande", "Calabresa, Mussarela", 45.0, "Massas", pItens);
		c.addItens("Pizza de Calabresa Media", "Calabresa, Mussarela", 30.0, "Massas", pItens2);

		listIPedido.add(c.getItens().get(0));

		// Parte do estoque

		p.add(forn.getFornecedores(), "FORN0", "PROD0");
		p.add(forn.getFornecedores(), "FORN0", "PROD1");
		p.add(forn.getFornecedores(), "FORN0", "PROD2");

		p.add(forn.getFornecedores(), "FORN1", "PROD3");
		p.add(forn.getFornecedores(), "FORN1", "PROD4");
		p.add(forn.getFornecedores(), "FORN1", "PROD5");

	}

	@Test
	public void testAdcionarVendaComlistaDeItensExistente() {
		assertTrue(v.addVenda("Modo de Pagamento", listIPedido, p.getProdutos(), estoque, p));
	}

	@Test
	public void testAdcionarVendaComlistaDeItensInexistente() {

		LinkedList<Itens> iAux = new LinkedList<Itens>();

		assertFalse(v.addVenda("Modo de Pagamento", iAux, p.getProdutos(), estoque, p));
	}

	@Test
	public void testAdcionarVendaComProdutoNoEstoqueInsuficiente() {
		Produtos aux2 = new Produtos("Queijo", 40.0);
		Produtos aux3 = new Produtos("Calabresa", 40.0);

		LinkedList<Produtos> prodAux = new LinkedList<Produtos>();
		prodAux.add(aux2);
		prodAux.add(aux3);

		c.addItens("Pizza de Calabresa", "Calabresa, Mussarela", 45.0, "Massas", prodAux);

		LinkedList<Itens> iAux = new LinkedList<Itens>();
		iAux.add(c.getItens().get(2));
		assertFalse(v.addVenda("Modo de Pagamento", iAux, p.getProdutos(), estoque, p));
	}

	@Test
	public void testRemoveVendaExistente() {
		v.addVenda("Modo de Pagamento", listIPedido, p.getProdutos(), estoque, p);
		Vendas vendas = v.remove("VEND0");

		assertEquals(vendas.getCod(), "VEND0");
		assertEquals(vendas.getPrecoTotal(), 45.0);
	}

	@Test
	public void testRemoveVendaInexistente() {
		v.addVenda("Modo de Pagamento", listIPedido, p.getProdutos(), estoque, p);
		assertNull(v.remove("VEND1"));
	}

	@Test
	public void testEditarVendaExistente() {
		v.addVenda("Modo de Pagamento", listIPedido, p.getProdutos(), estoque, p);
		Vendas vendas = v.edit(v.getVendas().get(0), v.getVendas().get(0).getPrecoTotal(), "Cartao");

		assertEquals(vendas.getModPag(), "Cartao");
	}

	@Test
	public void testIndexVendaExistente() {
		v.addVenda("Modo de Pagamento", listIPedido, p.getProdutos(), estoque, p);
		assertEquals(v.indexVenda("VEND0"), 0);
	}

	@Test
	public void testIndexVendaInexistente() {
		assertEquals(v.indexVenda("VEND1"), -1);
	}
}
