package model.gerenciamento;

import java.util.LinkedList;

import model.entidades.Fornecedores;
import model.entidades.Produtos;

/**
 * Classe que gerencia os fornecedores do estabelecimento
 * 
 * @author lucas
 *
 */
public class GerFornecedores {

	/**
	 * Lista com todos os fornecedores do estabelecimento
	 */
	private LinkedList<Fornecedores> fornecedores;

	/**
	 * Contador crescente para gerar c�digo �nico
	 */
	private int countCod;

	public GerFornecedores() {
		fornecedores = new LinkedList<Fornecedores>();
	}

	/**
	 * M�todo que adiciona um fornecedor a lista do restaurante Como o fornecedor �
	 * um agente de fora do estabelecimento, sup�e-se que que ele j� forne�a uma
	 * lista de produtos j� pronta e n�o seja poss�vel mexer nela.
	 * 
	 * @param nome
	 * @param cnpj
	 * @param endereco
	 * @param p
	 * @param produtos Lista de produtos dispon�veis do fornecedor
	 */
	public boolean add(String nome, String cnpj, String endereco, LinkedList<Produtos> p) {
		try {
			if (verificaAtributosForn(nome, cnpj, endereco, p)) {
				Fornecedores e = new Fornecedores(geraCod(), nome, cnpj, endereco, p);
				atualizaReferenciaFornecedores(e);
				fornecedores.add(e);
				return true;
			}
		//	Alerts.showAlertComum("Erro", "Erro ao adicionar o fornecedor", AlertType.WARNING);
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * M�todo para remover um fornecedor (se existir) pelo seu c�digo �nico
	 * 
	 * @param cod C�digo do fornecedor a ser removido
	 * @return remove uma c�pia do fornecedor removido
	 */
	public Fornecedores remove(String cod) {
		if(cod.isBlank()) {
			//Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		try {
			int index = this.indexFornecedor(cod);
			if (index > -1) {
				return fornecedores.remove(index);
			}
		//	Alerts.showAlertComum("Erro", "Fornecedor nao encontrado", AlertType.WARNING);
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Recebe o fornecedor a ser editado e os novos atributos como parametro,
	 * e faz o Set de cada um deles.
	 * @param f
	 * @param nome
	 * @param cnpj
	 * @param endereco
	 * @return
	 */
	public Fornecedores edit(Fornecedores f, String nome, String cnpj, String endereco) {
		if (verificaAtributosForn(nome, cnpj, endereco)) {
			try {
				f.setNome(nome);
				f.setCnpj(cnpj);
				f.setEndereco(endereco);
				return f;
			} catch (NullPointerException e) {
				return null;
			}
		} else {
		//	Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return null;
		}
	}

	/**
	 * M�todo para mostrar todos os fornecedores
	 */
	public void show() {
		for (Fornecedores forn : fornecedores) {
			forn.mostraFornecedor();
			forn.mostraListaProdutos();
		}
	}

	/**
	 * M�todo que retorna o �ndice do fornecedor pelo c�digo �nico
	 * 
	 * @param cod C�digo �nico do fornecedor
	 * @retur �ndice do fornecedor
	 */
	public int indexFornecedor(String cod) {
		try {
			if (!fornecedores.isEmpty()) {
				int index = 0;
				for (Fornecedores forn : fornecedores) {
					if (forn.getCod().equals(cod)) {
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
	 * M�todo para gerar os c�digos automaticamente
	 * 
	 * @return
	 */
	public String geraCod() {
		String s = "FORN" + countCod;
		countCod++;
		return s;
	}

	/**
	 * M�todo que verifica se os atributos s�o v�lidos
	 * 
	 * @param nome
	 * @param cnpj
	 * @param endereco
	 * @return
	 */
	public boolean verificaAtributosForn(String nome, String cnpj, String endereco, LinkedList<Produtos> p) {
		if (nome.isBlank() || cnpj.isBlank() || endereco.isBlank() || p != null) {
			return true;
		}
		return false;
	}

	public boolean verificaAtributosForn(String nome, String cnpj, String endereco) {
		if (!nome.isBlank() && !cnpj.isBlank() && !endereco.isBlank()) {
			return true;
		}
		return false;
	}

	/**
	 * Método que atualiza todas as referencias de produtos para o fornecedor criado
	 */
	public void atualizaReferenciaFornecedores(Fornecedores f) {
		for (Produtos p : f.getProdutos()) {
			p.setFornecedor(f);
		}
	}

	public LinkedList<Fornecedores> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(LinkedList<Fornecedores> fornecedores) {
		this.fornecedores = fornecedores;
	}

}
