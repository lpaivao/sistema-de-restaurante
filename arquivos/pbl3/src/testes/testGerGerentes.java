package testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.entidades.Gerente;
import model.gerenciamento.GerGerentes;

public class testGerGerentes {

	GerGerentes g;
	Gerente zero, um, dois;
	
	@Before
	public void setUp() throws Exception {
		g = new GerGerentes();

		zero = new Gerente("MESTRE","Mestre", "mestre");
		um = new Gerente("GER0","Lucas", "123");
		dois = new Gerente("GER1","Gabriela", "321");
		
		
		g.add("Lucas", "123");
		g.add("Gabriela", "321");
	}

	@Test
	public void testeAdicionaGerenteComParametroValido() {

		assertEquals(g.getGerentes().get(0).getCod(), zero.getCod());
		assertEquals(g.getGerentes().get(0).getNome(), zero.getNome());
		
		assertEquals(g.getGerentes().get(1).getCod(), um.getCod());
		assertEquals(g.getGerentes().get(1).getNome(), um.getNome());
		
		assertEquals(g.getGerentes().get(2).getCod(), dois.getCod());
		assertEquals(g.getGerentes().get(2).getNome(), dois.getNome());
	}
	
	@Test
	public void testeAdicionaGerenteComParametroInvalido() {	
		
		assertFalse(g.add(null, "123"));
		
	}

	
	@Test
	public void testeRemoveGerenteExistente() {
		
		Gerente aux = g.remove("GER0");
		
		assertEquals(aux.getCod(), um.getCod());
		assertEquals(aux.getNome(), um.getNome());
		
	}
	
	@Test
	public void testeRemoveGerenteInexistente() {
		
		assertNull(g.remove("GER3"));
		assertNull(g.remove("GER4"));
		assertNull(g.remove("GER5"));
	}
	
	@Test
	public void testeEditaGerenteComParametroValido() {
		
		g.edit("GER0", "Robson", "Senha");
		
		assertEquals(g.getGerentes().get(1).getNome(), "Robson");
	}
	
	@Test
	public void testeEditaGerenteComParametroInvalido() {
		
		assertNull(g.edit("GER3", "Robson", "Senha"));
	}

	@Test
	public void testaBuscaIndexDoGerenteExistente() {
		
		assertEquals(g.indexGerente("MESTRE"), 0);
		assertEquals(g.indexGerente("GER0"), 1);
		assertEquals(g.indexGerente("GER1"), 2);
		
		g.remove("GER0");
		
		assertEquals(g.indexGerente("GER1"), 1);	
	}
	
	@Test
	public void testaBuscaIndexDoGerenteInexistente() {
		
		assertEquals(g.indexGerente("GER3"), -1);
		assertEquals(g.indexGerente("GER4"), -1);
		assertEquals(g.indexGerente("GER5"), -1);
	} 
}
