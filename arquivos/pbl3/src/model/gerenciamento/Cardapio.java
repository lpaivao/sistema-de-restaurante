package model.gerenciamento;

import java.util.LinkedList;

import model.entidades.Itens;
import model.entidades.Produtos;

/**
 * Classe que gerencia os Itens (ou pratos) do card�pio
 * 
 * @author lucas
 *
 */

public class Cardapio {

	private String cod;

	/**
	 * Lista encadeada que armazena os itens do card�pio
	 */
	private LinkedList<Itens> itens;

	/**
	 * Contador crescente para gerar c�digo �nico
	 */
	private int countCod;

	public Cardapio() {
		cod = "CARD";
		itens = new LinkedList<Itens>();
		countCod = 0;
	}

	/**
	 * M�todo que adiciona um novo item ao card�pio
	 * 
	 * @param nome
	 * @param descricao
	 * @param preco
	 * @param categoria
	 */
	public boolean addItens(String nome, String descricao, Double preco, String categoria,
			LinkedList<Produtos> componentesPrato) {
		try {
			if (this.verificaAtributosItem(nome, descricao, preco, categoria) && !componentesPrato.isEmpty()) {
				Itens e = new Itens(geraCod(), nome, descricao, preco, categoria, componentesPrato);
				itens.add(e);
				return true;
			}
		//	Alerts.showAlertComum("Erro", "Um ou mais espaços vazios", AlertType.WARNING);
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * M�todo que busca o item a partir do seu c�digo �nico e remove da lista
	 * 
	 * @param cod
	 * @return
	 */
	public Itens removeItens(String cod) {
		if(cod.isBlank()) {
		//	Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		try {
			int index = this.indexItem(cod);
			if (index > -1) {
				return itens.remove(index);
			}
		//	Alerts.showAlertComum("Erro", "Item nao encontrado", AlertType.WARNING);
			return null;
		} catch (Exception e) {
			return null;
		}

	}
	
	/**
	 * Metodo que recebe um item, e seus atributos novos e faz o Set em cada um deles
	 * @param i
	 * @param nome
	 * @param descricao
	 * @param preco
	 * @param categoria
	 * @return
	 */
	public Itens edit(Itens i, String nome, String descricao, Double preco, String categoria) {
		if(verificaAtributosItem(nome, descricao, preco, categoria)) {
			try {
				i.setNome(nome);
				i.setDescricao(descricao);
				i.setPreco(preco);
				i.setCategoria(categoria);
				return i;
			}catch (NullPointerException e) {
				return null;
			}
		}
		else {
		//	Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return null;
		}
	}

	/**
	 * M�todo que lista todos os atributos dos itens do card�pio
	 */
	public void show() {
		if (!itens.isEmpty()) {
			System.out.println("---------------------------------");
			for (Itens itens : itens) {
				itens.mostraItem();
				for (Produtos p : itens.getComponentesPrato()) {
					p.mostraProduto();
				}
			}
		} else
			System.out.println("Card�pio sem itens");
	}

	/**
	 * M�todo que retorna o �ndice do item (se existir) a partir do c�digo �nico
	 * 
	 * @param C�digo do item a ser buscado
	 * @return �ndice do item
	 */
	public int indexItem(String cod) {
		try {
			if (!itens.isEmpty()) {
				int index = 0;
				for (Itens item : itens) {
					if (item.getCod().equals(cod)) {
						return index;
					}
					index++;
				}
			} else
				System.out.println("Item n�o encontrado");
			return -1;

		} catch (Exception e) {
			return -1;
		}

	}

	/**
	 * M�todo que busca e retorna uma c�pia do item (se existir) a partir do c�digo
	 * �nico
	 * 
	 * @param C�digo do item a ser buscado
	 * @return C�pia do item encontrado (se existir). Sen�o, retorna nulo
	 */
	public Itens buscaItem(String cod) {
		try {
			for (Itens item : itens) {
				if (item.getCod().equals(cod)) {
					return item;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
		
	}

	/**
	 * M�todo que gera o c�digo �nico da entidade Card�pio
	 * 
	 * @return retorna o c�digo gerado
	 */
	public String geraCod() {
		String s = "ITEM" + countCod;
		countCod++;
		return s;
	}

	/**
	 * M�todo que verifica se os atributos do item s�o v�lidos para adicionar/editar
	 * 
	 * @param nome
	 * @param descricao
	 * @param preco
	 * @param categoria
	 * @return retorna se o item pode ser adicionado a lista de itens
	 */
	public boolean verificaAtributosItem(String nome, String descricao, Double preco, String categoria) {
		if (nome != null && nome.length() > 0 && descricao != null && preco > 0.0 && categoria != null) {
			return true;
		}
		return false;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public LinkedList<Itens> getItens() {
		return itens;
	}

	public void setItens(LinkedList<Itens> itens) {
		this.itens = itens;
	}

}
