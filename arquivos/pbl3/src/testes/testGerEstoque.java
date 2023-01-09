package testes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.entidades.Itens;
import model.entidades.Produtos;
import model.gerenciamento.Cardapio;
import model.gerenciamento.GerEstoque;
import model.gerenciamento.GerFornecedores;
import model.gerenciamento.GerProdutos;
import model.gerenciamento.GerVendas;

public class testGerEstoque {

	GerEstoque estoque;
	
	Cardapio c;
	GerFornecedores forn;
	GerProdutos p;
	GerVendas v;
	
	private LinkedList<Itens> listIPedido;
	private LinkedList<Produtos> pAux1, pAux2;
	private LinkedList<Produtos> pItens;
	
	private Produtos p0, p1, p2;
	private Produtos p3, p4, p5;
	
	private Produtos aux0, aux1, aux2, aux3;
	
	@Before
	public void setUp() throws Exception {
		
		estoque = new GerEstoque();
		
		c = new Cardapio();
		forn = new GerFornecedores();
		p = new GerProdutos();

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
		
		//Parte do cardapio
		
		listIPedido = new LinkedList<Itens>();
		pItens = new LinkedList<Produtos>();
		
		aux0 = new Produtos("Queijo", 10.0);
		aux1 = new Produtos("Calabresa", 10.0);
		
		pItens.add(aux0); pItens.add(aux1);
		
		c.addItens("Pizza de Calabresa", "Calabresa, Mussarela", 45.0, "Massas", pItens);
		listIPedido.add(c.getItens().get(0));
		
		//Parte do estoque
			
		p.add(forn.getFornecedores(), "FORN0", "PROD0");
		p.add(forn.getFornecedores(), "FORN0", "PROD1");
		p.add(forn.getFornecedores(), "FORN0", "PROD2");
		
		p.add(forn.getFornecedores(), "FORN1", "PROD3");
		p.add(forn.getFornecedores(), "FORN1", "PROD4");
		p.add(forn.getFornecedores(), "FORN1", "PROD5");
	}
	
	@Test
	public void testCalculaEstoqueDisponivelDeProdutoExistente(){
		assertEquals(estoque.calculaEstoqueDisponivel(p.getProdutos().get(0)), (float) 22.0);
		assertEquals(estoque.calculaEstoqueDisponivel(p.getProdutos().get(1)), (float) 19.0);
	}
	
	@Test
	public void testVerificaSeOProdutoExistenteExisteNoEstoque() {
		assertEquals(estoque.pExists("Queijo", p.getProdutos()), 0);
		assertEquals(estoque.pExists("Calabresa", p.getProdutos()), 1);
	}
	
	@Test
	public void testVerificaSeOProdutoInexistenteExisteNoEstoque() {
		assertEquals(estoque.pExists("Alface", p.getProdutos()), -1);
		assertEquals(estoque.pExists("Tomate", p.getProdutos()), -1);
	}
	
	@Test
	public void testVerificaItensExistentesAntesDaVenda() {
		assertTrue(estoque.verificaItensAntesDaVenda(listIPedido, p.getProdutos()));
	}
	
	@Test
	public void testVerificaItensComEstoqueInsuficienteProPedido() {
		aux2 = new Produtos("Queijo", 40.0);
		aux3 = new Produtos("Calabresa", 40.0);
		
		LinkedList<Produtos> prodAux = new LinkedList<Produtos>();
		prodAux.add(aux2);
		prodAux.add(aux3);
		
		c.addItens("Pizza de Calabresa", "Calabresa, Mussarela", 45.0, "Massas", prodAux);
		
		LinkedList<Itens> iAux = new LinkedList<Itens>();
		iAux.add(c.getItens().get(1));
		
		assertFalse(estoque.verificaItensAntesDaVenda(iAux, p.getProdutos()));
	}
	
	@Test
	public void testVerificaItensDeListaVaziaAntesDaVenda() {
		LinkedList<Itens> i =  new LinkedList<Itens>();
		assertFalse(estoque.verificaItensAntesDaVenda(i, p.getProdutos()));
	}
	
	@Test
	public void testRemoverColetivamenteDoEstoqueComEstoqueSuficiente() {
		assertTrue(estoque.removeTodosDoEstoque(listIPedido, p.getProdutos(), p));
	}

}
