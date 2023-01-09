package testes;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.entidades.Itens;
import model.entidades.Produtos;
import model.gerenciamento.Cardapio;

public class testCardapio {

	public Cardapio c;

	public LinkedList<Produtos> p;
	
	public Produtos p0,p1;
	
	@Before
	public void setUp() throws Exception {
		c = new Cardapio();
		p = new LinkedList<Produtos>();
		p0 = new Produtos("Queijo", 10.0);
		p1 = new Produtos("Calabresa", 5.0);
		p.add(p0); p.add(p1);
	}
	
	@Test
	public void testaAdicionarItensComParametroCerto() {
		
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		
		assertEquals(c.getItens().get(0).getCod(), "ITEM0");
		assertEquals(c.getItens().get(0).getNome(), "Pizza de calabresa");
		assertEquals(c.getItens().get(0).getDescricao(), "pizza de calabresa com mussarela");
		assertEquals(c.getItens().get(0).getPreco(), 50.0);
		assertEquals(c.getItens().get(0).getCategoria(), "Massas");
		
	}

	
	@Test
	public void AdicionarItemComParametroInvalido() {
		
		assertFalse(c.addItens("", "pizza de calabresa com mussarela", 50.0, "Massas", p));
		assertFalse(c.addItens("Pizza de calabresa", null, 50.0, "Massas", p));
		assertFalse(c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 0.0, "Massas", p));
		
		assertNull(c.getItens().peek());
	}
	
	@Test
	public void testaRemoverItemComCodigoCerto() {
		
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		
		Itens temp = c.removeItens("ITEM0");
		
		assertEquals(temp.getCod(), "ITEM0");
		assertEquals(temp.getNome(), "Pizza de calabresa");
		assertEquals(temp.getDescricao(), "pizza de calabresa com mussarela");
		assertEquals(temp.getPreco(), 50.0);
		assertEquals(temp.getCategoria(), "Massas");
		
		
	}
	
	@Test
	public void testaRemoverItemQueNaoExiste() {
		
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		
		assertNull(c.removeItens("ITEM1"));
		assertNull(c.removeItens("ITEM2"));
		assertNull(c.removeItens("ITEM3"));
	}
	
	@Test
	public void testaEditarItemComParametroValido() {

		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);

		c.edit(c.getItens().get(0), "Pizza de Frango", "Frango, Mussarela", 40.0, "Massas");

		assertEquals(c.getItens().get(0).getCod(), "ITEM0");
		assertEquals(c.getItens().get(0).getNome(), "Pizza de Frango");
		assertEquals(c.getItens().get(0).getDescricao(), "Frango, Mussarela");
		assertEquals(c.getItens().get(0).getPreco(), 40.0);
		assertEquals(c.getItens().get(0).getCategoria(), "Massas");
	}

	@Test
	public void testaEditarItemComParametroErrado() {

		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		
		assertNull(c.edit(c.getItens().get(0), null, "Frango, Mussarela", 40.0, "Massas"));
		assertNull(c.edit(c.getItens().get(0), "Pizza de Frango", null, 40.0, "Massas"));
		assertNull(c.edit(c.getItens().get(0), "Pizza de Frango", "Frango, Mussarela", 0.0, "Massas"));

	}
	
	@Test
	public void testaBuscaIndexDoItemExistente() {

		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		
		int index0 = c.indexItem("ITEM0");
		int index1 = c.indexItem("ITEM1");
		int index2 = c.indexItem("ITEM2");

		assertEquals(index0, 0);
		assertEquals(index1, 1);
		assertEquals(index2, 2);

	}
	
	@Test
	public void testaBuscaIndexDoItemInexistente() {
		
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);

		int index0 = c.indexItem("ITEM3");
		int index1 = c.indexItem("ITEM4");
		int index2 = c.indexItem("ITEM5");

		assertEquals(index0, -1);
		assertEquals(index1, -1);
		assertEquals(index2, -1);
	}

	@Test
	public void testaBuscaItemExistente() {

		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);

		assertEquals(c.getItens().get(0).getCod(), "ITEM0");
		assertEquals(c.getItens().get(0).getNome(), "Pizza de calabresa");
		assertEquals(c.getItens().get(0).getDescricao(), "pizza de calabresa com mussarela");
		assertEquals(c.getItens().get(0).getPreco(), 50.0);
		assertEquals(c.getItens().get(0).getCategoria(), "Massas");
		
		assertEquals(c.getItens().get(1).getCod(), "ITEM1");
		assertEquals(c.getItens().get(1).getNome(), "Pizza de calabresa");
		assertEquals(c.getItens().get(1).getDescricao(), "pizza de calabresa com mussarela");
		assertEquals(c.getItens().get(1).getPreco(), 50.0);
		assertEquals(c.getItens().get(1).getCategoria(), "Massas");
		
		assertEquals(c.getItens().get(2).getCod(), "ITEM2");
		assertEquals(c.getItens().get(2).getNome(), "Pizza de calabresa");
		assertEquals(c.getItens().get(2).getDescricao(), "pizza de calabresa com mussarela");
		assertEquals(c.getItens().get(2).getPreco(), 50.0);
		assertEquals(c.getItens().get(2).getCategoria(), "Massas");
	}
	
	@Test
	public void testaBuscaItemInexistente() {
		
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		c.addItens("Pizza de calabresa", "pizza de calabresa com mussarela", 50.0, "Massas", p);
		
		assertNull(c.buscaItem("ITEM3"));
		assertNull(c.buscaItem("ITEM4"));
		assertNull(c.buscaItem("ITEM5"));
		
	}

}
