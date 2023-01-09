package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.entidades.Fornecedores;
import model.entidades.Produtos;
import model.gerenciamento.GerProdutos;

public class testGerProdutos {

	private GerProdutos p;
	
	LinkedList<Produtos> listP1;
	LinkedList<Produtos> listP2;
	
	LinkedList<Fornecedores> listF;
	
	Fornecedores zero;
	Fornecedores um;
	
	Produtos p0, p1, p2, p3, p4, p5;
	
	@Before
	public void setUp() throws Exception {
		p = new GerProdutos();
		listP1 = new LinkedList<Produtos>();
		listP2 = new LinkedList<Produtos>();
		listF = new LinkedList<Fornecedores>();
		
		zero = new Fornecedores("FORN0", "Frios distribuidora", "12345", "Rua A", new LinkedList<Produtos>());
		um = new Fornecedores("FORN1", "Carnes distribuidora", "54321", "Rua B", new LinkedList<Produtos>());
		
		p0 = new Produtos("PROD0", "Queijo", 15.0, "22/11/2023", 5.0, zero);
		p1 = new Produtos("PROD1", "Queijo", 20.0, "22/11/2022", 7.0, zero);
		p2 = new Produtos("PROD2", "Queijo", 10.0, "22/11/2021", 10.0, zero);
		p3 = new Produtos("PROD3", "Calabresa", 20.0, "23/11/2022", 4.0, um);
		p4 = new Produtos("PROD4", "Calabresa", 30.0, "22/11/2022", 6.0, um);
		p5 = new Produtos("PROD5", "Calabresa", 40.0, "21/11/2022", 9.0, um);
		
		listP1.add(p0);
		listP1.add(p1);
		listP1.add(p2);
		
		listP2.add(p3);
		listP2.add(p4);
		listP2.add(p5);
		
		zero.setProdutos(listP1);
		um.setProdutos(listP2);
		
		listF.add(zero);
		listF.add(um);
		
		p.add(listF, "FORN0", "PROD0");
		p.add(listF, "FORN0", "PROD1");
		p.add(listF, "FORN0", "PROD2");
		
		p.add(listF, "FORN1", "PROD3");
		p.add(listF, "FORN1", "PROD4");
		p.add(listF, "FORN1", "PROD5");
		
	}

	@Test
	public void testeBuscaFornecedor() {
		assertEquals(p.buscaFornecedor("FORN0", listF), listF.get(0));
		assertEquals(p.buscaFornecedor("FORN1", listF), listF.get(1));
	}
	
	@Test
	public void testeBuscaProdutoDoRestaurante() {
		
		int aux1 = p.indexProdutoDoRestaurante1("PROD0");
		int aux2 = p.indexProdutoDoRestaurante2("PROD0", aux1);
		
		assertEquals(p.getProdutos().get(aux1).get(aux2).getCod(), p0.getCod());
		assertEquals(p.getProdutos().get(aux1).get(aux2).getNome(), p0.getNome());
		assertEquals(p.getProdutos().get(aux1).get(aux2).getPreco(), p0.getPreco());
		assertEquals(p.getProdutos().get(aux1).get(aux2).getQuantidade(), p0.getQuantidade());
		assertEquals(p.getProdutos().get(aux1).get(aux2).getValidade(), p0.getValidade());
		
		
		
	}

	@Test
	public void testeAdicionarProdutoFornecedorExistente() {
		
		assertEquals(p.getProdutos().get(0).get(0).getCod(), "PROD2");
		assertEquals(p.getProdutos().get(0).get(0).getNome(), "Queijo");
		assertEquals(p.getProdutos().get(0).get(0).getPreco(), p2.getPreco());
		assertEquals(p.getProdutos().get(0).get(0).getValidade(), p2.getValidade());
		assertEquals(p.getProdutos().get(0).get(0).getQuantidade(), p2.getQuantidade());
		
		assertEquals(p.getProdutos().get(0).get(1).getCod(), "PROD1");
		assertEquals(p.getProdutos().get(0).get(1).getNome(), "Queijo");
		assertEquals(p.getProdutos().get(0).get(1).getPreco(), p1.getPreco());
		assertEquals(p.getProdutos().get(0).get(1).getValidade(), p1.getValidade());
		assertEquals(p.getProdutos().get(0).get(1).getQuantidade(), p1.getQuantidade());
		
		assertEquals(p.getProdutos().get(0).get(2).getCod(), "PROD0");
		assertEquals(p.getProdutos().get(0).get(2).getNome(), "Queijo");
		assertEquals(p.getProdutos().get(0).get(2).getPreco(), p0.getPreco());
		assertEquals(p.getProdutos().get(0).get(2).getValidade(), p0.getValidade());
		assertEquals(p.getProdutos().get(0).get(2).getQuantidade(), p0.getQuantidade());
		

		assertEquals(p.getProdutos().get(1).get(0).getCod(), "PROD5");
		assertEquals(p.getProdutos().get(1).get(0).getNome(), "Calabresa");
		assertEquals(p.getProdutos().get(1).get(0).getPreco(), p5.getPreco());
		assertEquals(p.getProdutos().get(1).get(0).getValidade(), p5.getValidade());
		assertEquals(p.getProdutos().get(1).get(0).getQuantidade(), p5.getQuantidade());
		
		assertEquals(p.getProdutos().get(1).get(1).getCod(), "PROD4");
		assertEquals(p.getProdutos().get(1).get(1).getNome(), "Calabresa");
		assertEquals(p.getProdutos().get(1).get(1).getPreco(), p4.getPreco());
		assertEquals(p.getProdutos().get(1).get(1).getValidade(), p4.getValidade());
		assertEquals(p.getProdutos().get(1).get(1).getQuantidade(), p4.getQuantidade());
		
		assertEquals(p.getProdutos().get(1).get(2).getCod(), "PROD3");
		assertEquals(p.getProdutos().get(1).get(2).getNome(), "Calabresa");
		assertEquals(p.getProdutos().get(1).get(2).getPreco(), p3.getPreco());
		assertEquals(p.getProdutos().get(1).get(2).getValidade(), p3.getValidade());
		assertEquals(p.getProdutos().get(1).get(2).getQuantidade(), p3.getQuantidade());
		
	}
	
	@Test
	public void testeAdicionarProdutoFornecedorInexistente() {
		
		assertFalse(p.add(listF, "FORN3", "PROD2"));
		assertFalse(p.add(listF, "FORN3", "PROD1"));
		assertFalse(p.add(listF, "FORN3", "PROD0"));
		
	}
	
	@Test
	public void testeAdicionarProdutoInexistenteDoFornecedor() {
		
		assertFalse(p.add(listF, "FORN2", "PROD5"));
		assertFalse(p.add(listF, "FORN2", "PROD4"));
		assertFalse(p.add(listF, "FORN2", "PROD3"));
	}
	
	@Test
	public void testeRemoverProdutoExistente() {
		
		Produtos aux = p.remove("PROD0");
		
		assertEquals(aux.getCod(), "PROD0");
		assertEquals(aux.getNome(), "Queijo");
		assertEquals(aux.getPreco(), p0.getPreco());
		assertEquals(aux.getValidade(), p0.getValidade());
		assertEquals(aux.getQuantidade(), p0.getQuantidade());		
		
	}
	
	@Test
	public void testeRemoverProdutoInexistente() {
		
		assertNull(p.remove("PROD6"));
		assertNull(p.remove("PROD7"));
		assertNull(p.remove("PROD8"));
		
	}
	
	@Test
	public void testeEditaProdutoExistente() {
		
		assertEquals(p.getProdutos().get(0).get(0), p.edit(p.getProdutos().get(0).get(0), 25.0, 10.0));
	}
	
	@Test
	public void testeEditaProdutoInexistente() {
		
		assertNull(p.edit(null, 25.0, 10.0));
		
	}
	
	@Test
	public void testeEditaProdutoExistenteComParametroInvalido() {
		
		assertNull(p.edit(p.getProdutos().get(0).get(0), 25.0, null));
		
	}
	
	@Test
	public void testeBuscaProdutoDoFornecedorInexistente() {
		
		assertNull(p.buscaFornecedor("PROD3", listF));
		assertNull(p.buscaFornecedor("PROD4", listF));
		assertNull(p.buscaFornecedor("PROD5", listF));
		
	}

}
