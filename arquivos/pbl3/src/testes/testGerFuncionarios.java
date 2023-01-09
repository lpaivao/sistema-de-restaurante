package testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.entidades.Funcionario;
import model.gerenciamento.GerFuncionarios;

public class testGerFuncionarios {

	GerFuncionarios f;
	
	Funcionario zero;
	Funcionario um;
	Funcionario dois;
	
	@Before
	public void setUp() throws Exception {
		f = new GerFuncionarios();
		zero = new Funcionario("FUNC0","Lucas", "123");
		um = new Funcionario("FUNC1","Gabriela", "321");
		dois = new Funcionario("FUNC2","Paulo", "456");
		
		f.add("Lucas", "123");
		f.add("Gabriela", "321");
		f.add("Paulo", "456");
	}
	
	@Test
	public void testeAdicionaFuncionarioComParametroValido() {
		assertEquals(f.getFuncionarios().get(0).getCod(), zero.getCod());
		assertEquals(f.getFuncionarios().get(0).getNome(), zero.getNome());
		
		assertEquals(f.getFuncionarios().get(1).getCod(), um.getCod());
		assertEquals(f.getFuncionarios().get(1).getNome(), um.getNome());
		
		assertEquals(f.getFuncionarios().get(2).getCod(), dois.getCod());
		assertEquals(f.getFuncionarios().get(2).getNome(), dois.getNome());
	}
	
	@Test
	public void testeAdicionaFuncionarioComParametroInvalido() {	
		
		assertFalse(f.add("", "123"));
		assertFalse(f.add(null, ""));
		
	}

	
	@Test
	public void testeRemoveFuncionarioExistente() {
		
		Funcionario um = f.getFuncionarios().get(1);
		
		Funcionario aux = f.remove("FUNC1");
		
		assertEquals(aux.getCod(), um.getCod());
		assertEquals(aux.getNome(), um.getNome());
	}
	
	@Test
	public void testeRemoveFuncionarioInexistente() {
		assertNull(f.remove("FUNC3"));
		assertNull(f.remove("FUNC4"));
		assertNull(f.remove("FUNC5"));
	}
	
	@Test
	public void testeEditaFuncionarioComParametroValido() {

		f.edit(f.getFuncionarios().get(0), "Robson", "Senha");
		
		assertEquals(f.getFuncionarios().get(0).getNome(), "Robson");
	}
	
	@Test
	public void testeEditaFuncionarioComParametroInvalido() {
		
		assertNull(f.edit(f.getFuncionarios().get(0), "Robson", " "));
		assertNull(f.edit(f.getFuncionarios().get(0), " ", "Senha"));
	}

	@Test
	public void testaBuscaIndexDoFuncionarioExistente() {
		
		assertEquals(f.indexFuncionario("FUNC0"), 0);
		assertEquals(f.indexFuncionario("FUNC1"), 1);
		assertEquals(f.indexFuncionario("FUNC2"), 2);
		
		f.remove("FUNC1");
		
		assertEquals(f.indexFuncionario("FUNC2"), 1);	
	}
	
	@Test
	public void testaBuscaIndexDoFuncionarioInexistente() {
		
		assertEquals(f.indexFuncionario("FUNC3"), -1);
		assertEquals(f.indexFuncionario("FUNC4"), -1);
		assertEquals(f.indexFuncionario("FUNC5"), -1);
	}
	
}
