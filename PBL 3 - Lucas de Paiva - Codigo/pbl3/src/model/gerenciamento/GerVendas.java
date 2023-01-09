package model.gerenciamento;

import java.text.ParseException;
import java.util.LinkedList;

import javafx.scene.control.Alert.AlertType;
import model.entidades.Itens;
import model.entidades.Produtos;
import model.entidades.Vendas;
import utils.Alerts;

/**
 * Classe que gerencia as vendas do estabelecimento
 * 
 * @author lucas
 *
 */
public class GerVendas {

	/**
	 * Lista de vendas j� conclu�das pelo estabelecimento
	 */
	private LinkedList<Vendas> vendas;
	/**
	 * Contador crescente para gerar c�digo �nico
	 */
	private int countCod;

	public GerVendas() {
		vendas = new LinkedList<Vendas>();
		countCod = 0;
	}

	/**
	 * 
	 * @param modPag Modo de pagamento
	 * @param itens  Lista de pratos do o pedido do cliente
	 * @param listP  Lista de produtos do estoque
	 * @return
	 * @throws ParseException
	 */
	public boolean addVenda(String modPag, LinkedList<Itens> itens, LinkedList<LinkedList<Produtos>> listP,
			GerEstoque estoque, GerProdutos gerP) {
		try {
			if (verificaAtributosVenda(modPag) && !itens.isEmpty()) {
				if (estoque.verificaItensAntesDaVenda(itens, listP)) {
					Vendas v = new Vendas(geraCod(), modPag, itens);
					vendas.add(v);
					return estoque.removeTodosDoEstoque(itens, listP, gerP);
				}
			}
		//	Alerts.showAlertComum("Erro", "Um ou mais espaços vazios", AlertType.WARNING);
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * M�todo que remove uma venda a partir de um c�digo �nico
	 * 
	 * @param cod C�digo da venda
	 * @return Retorna a venda removida
	 */
	public Vendas remove(String cod) {
		if(cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		try {
			int index = this.indexVenda(cod);
			if (index > -1) {
				return vendas.remove(index);
			}
		//	Alerts.showAlertComum("Erro", "Venda nao encontrada", AlertType.WARNING);
			return null;
		} catch (Exception e) {
			return null;
		}

	}
	
	/**
	 * Metodo para editar o modo de pagamento da venda e preco. Como a venda ja esta
	 * efetuada, nao se pode mudar os outros atributos ou a lista de itens da venda.
	 * Recebe uma venda como parametro e Set os novos atributos.
	 */
	public Vendas edit(Vendas v, Double precoTotal, String modPag) {
		if(verificaAtributosVenda(precoTotal, modPag)) {
			try {
				v.setPrecoTotal(precoTotal);
				v.setModPag(modPag);
				return v;
			}catch (NullPointerException e) {
				return null;
			}
		}else {
		//	Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return null;
		}
	}

	/**
	 * M�todo para mostrar todas as vendas efetuadas
	 */
	public void show() {
		for (Vendas venda : vendas) {
			venda.mostraVenda();
		}
	}

	/**
	 * M�todo que retorna o �ndice da venda
	 * 
	 * @param cod C�digo da venda a ser buscada
	 * @return �ndice da venda
	 */
	public int indexVenda(String cod) {
		try {
			if (!vendas.isEmpty()) {
				int index = 0;
				for (Vendas venda : vendas) {
					if (venda.getCod().contains(cod)) {
						return index;
					}
					index++;
				}
			}
			return -1;
		} catch (Exception e) {
			return -1;
		}

	}

	/**
	 * M�todo para gerar o c�digo da venda automaticamente
	 * 
	 * @return
	 */
	public String geraCod() {
		String s = "VEND" + countCod;
		countCod++;
		return s;
	}

	/**
	 * M�todo que verifica se o atributo � v�lido
	 * 
	 * @param modPag
	 * @return
	 */
	public boolean verificaAtributosVenda(String modPag) {
		if (modPag != null && modPag.length() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean verificaAtributosVenda(Double precoTotal, String modPag) {
		if (!modPag.isBlank() && precoTotal > 0.0 && precoTotal != null) {
			return true;
		}
		return false;
	}
	
	public LinkedList<Vendas> getVendas() {
		return vendas;
	}

	public void setVendas(LinkedList<Vendas> vendas) {
		this.vendas = vendas;
	}

}
