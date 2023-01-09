package testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.entidades.Fornecedores;
import model.entidades.Produtos;
import model.gerenciamento.GerFornecedores;

public class testGerFornecedores {

	GerFornecedores f;
	
	private LinkedList<Produtos> pAux1, pAux2;

	private Produtos p0, p1, p2;
	private Produtos p3, p4, p5;

	
	@Before
	public void setUp() throws Exception {
		f = new GerFornecedores();
		
		f.add("Frios distribuidora", "12345", "Rua A", new LinkedList<Produtos>());
		f.add("Carnes distribuidora", "54321", "Rua B", new LinkedList<Produtos>());

		p0 = new Produtos("PROD0", "Queijo", 15.0, "22/11/2023", 5.0, f.getFornecedores().get(0));
		p1 = new Produtos("PROD1", "Queijo", 20.0, "22/11/2022", 7.0, f.getFornecedores().get(0));
		p2 = new Produtos("PROD2", "Queijo", 10.0, "22/11/2021", 10.0, f.getFornecedores().get(0));
		p3 = new Produtos("PROD3", "Calabresa", 20.0, "23/11/2022", 4.0, f.getFornecedores().get(1));
		p4 = new Produtos("PROD4", "Calabresa", 30.0, "22/11/2022", 6.0, f.getFornecedores().get(1));
		p5 = new Produtos("PROD5", "Calabresa", 40.0, "21/11/2022", 9.0, f.getFornecedores().get(1));

		pAux1 = new LinkedList<Produtos>();
		pAux2 = new LinkedList<Produtos>();

		pAux1.add(p0);
		pAux1.add(p1);
		pAux1.add(p2);
		pAux2.add(p3);
		pAux2.add(p4);
		pAux2.add(p5);

		f.getFornecedores().get(0).setProdutos(pAux1);
		f.getFornecedores().get(1).setProdutos(pAux2);

	}

	@Test
	public void testeAdicionaFornecedorComParametroValido() {

		assertEquals(f.getFornecedores().get(0).getCod(),"FORN0");
		assertEquals(f.getFornecedores().get(0).getNome(), "Frios distribuidora");
		assertEquals(f.getFornecedores().get(0).getCnpj(),"12345");
		assertEquals(f.getFornecedores().get(0).getEndereco(), "Rua A");
		
		assertEquals(f.getFornecedores().get(1).getCod(), "FORN1");
		assertEquals(f.getFornecedores().get(1).getNome(), "Carnes distribuidora");
		assertEquals(f.getFornecedores().get(1).getCnpj(), "54321");
		assertEquals(f.getFornecedores().get(1).getEndereco(), "Rua B");
		
	}
	
	@Test
	public void testeAdicionaFornecedorComParametroInvalido() {
		assertFalse(f.add(null, "12345", "Rua A", pAux1));
		assertFalse(f.add("Corujao", null, "Rua B", pAux2));
	}
	
	@Test
	public void testeRemoveFornecedorExistente() {
		
		Fornecedores aux = f.remove("FORN1");
		
		assertEquals(aux.getCod(), "FORN1");
		assertEquals(aux.getNome(), "Carnes distribuidora");
		assertEquals(aux.getCnpj(), "54321");
		assertEquals(aux.getEndereco(), "Rua B");
	}
	
	@Test
	public void testeRemoveFornecedorInexistente() {
		
		assertNull(f.remove("FORN3"));
		assertNull(f.remove("FORN4"));
		assertNull(f.remove("FORN5"));
		
	}
	
	@Test
	public void testeEditaFornecedorComParametroValido() {
		
		Fornecedores aux = f.edit(f.getFornecedores().get(0), "Gbarbosa Geconsud", "09875", "Rua D");
		
		assertEquals(aux.getCod(), "FORN0");

	}
	
	@Test
	public void testeEditaFornecedorComParametroInvalido() {
		
		assertNull(f.edit(f.getFornecedores().get(0), "A�ai Atacado", "", "Rua E"));
		assertNull(f.edit(f.getFornecedores().get(0), "Corujao", "", "Rua F"));	
	}
	
	
	@Test
	public void testeIndexFornecedorExistente() {
		
		int i0 = f.indexFornecedor("FORN0");
		int i1 = f.indexFornecedor("FORN1");
		
		assertEquals(i0, 0);
		assertEquals(i1, 1);
	}
	
	@Test
	public void testeIndexFornecedorInexistente() {
		
		assertEquals(f.indexFornecedor("FORN3"), -1);
		assertEquals(f.indexFornecedor("FORN4"), -1);
		assertEquals(f.indexFornecedor("FORN5"), -1);
		
	}

}
