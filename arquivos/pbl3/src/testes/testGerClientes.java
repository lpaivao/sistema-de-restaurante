package testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.gerenciamento.GerClientes;

public class testGerClientes {

	GerClientes c;

	@Before
	public void setUp() throws Exception{
		c = new GerClientes();
		c.add("nome", "cpf", "email", "telefone");
	}
	
	@Test
	public void testeAdicionaClienteComParametroValido() {
		assertTrue(c.add("nome", "cpf", "email", "telefone"));
	}
	
	@Test
	public void testeAdicionaGerenteComParametroInvalido() {	
		assertFalse(c.add("", "cpf", "email", "telefone"));
		assertFalse(c.add("nome", null, "email", "telefone"));
	}

	@Test
	public void testeRemoveGerenteExistente() {
		assertEquals(c.getClientes().get(0), c.remove("CLI0"));
	}
	
	@Test
	public void testeRemoveGerenteInexistente() {
		assertNull(c.remove("CLI1"));
	}
	
	@Test
	public void testeEditaGerenteComParametroValido() {
		assertEquals(c.getClientes().get(0), c.edit(c.getClientes().get(0), "nome", "cpf", "email", "telefone"));
	}
	
	@Test
	public void testeEditaGerenteComParametroInvalido() {
		assertNull(c.edit(c.getClientes().get(0), "", "cpf", "email", "telefone"));
	}

}
