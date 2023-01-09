package model.gerenciamento;

import java.util.LinkedList;

import model.entidades.Gerente;

/**
 * Classe que gerencia todos os gerentes do estabelecimento
 * 
 * @author lucas
 *
 */
public class GerGerentes {

	/**
	 * Lista de gerentes do estabelecimento
	 */
	private LinkedList<Gerente> gerentes;

	/**
	 * Contador crescente para gerar c�digo �nico
	 */
	private int countCod;

	public GerGerentes() {
		gerentes = new LinkedList<Gerente>();
		countCod = 0;
		gerentes.add(new Gerente("MESTRE", "Mestre", "mestre"));
	}

	/**
	 * M�todo para adicionar novos gerentes na lista, e retorna true se adicionou na
	 * lista
	 * 
	 * @param nome
	 */
	public boolean add(String nome, String senha) {
		try {
			if (this.verificaAtributosGerente(nome, senha)) {
				Gerente e = new Gerente(geraCod(), nome, senha);
				gerentes.add(e);
				return true;
			}
		//	Alerts.showAlertComum("Erro", "Nao foi possivel adicionar o gerente", AlertType.WARNING);
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * M�todo para remover os gerentes a partir do seu c�digo �nico
	 * 
	 * @param cod C�digo �nico do gerente
	 * @return Gerente removido
	 */
	public Gerente remove(String cod) {
		if(cod.isBlank()) {
		//	Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		try {
			int index = this.indexGerente(cod);
			if (index > -1) {
				return gerentes.remove(index);
			}
	//		Alerts.showAlertComum("Erro", "Nao ha gerentes com esse codigo", AlertType.WARNING);
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * M�todo para editar o nome do gerente a partir do seu c�digo �nico
	 * 
	 * @param cod  C�digo �nico do gerente
	 * @param nome Nome novo
	 */
	public Gerente edit(String cod, String nome, String senha) {
		try {
			if (this.verificaAtributosGerente(nome, senha)) {
				int index = this.indexGerente(cod);
				if (index > -1) {
					Gerente novo = new Gerente(gerentes.get(index).getCod(), nome, senha);
					gerentes.remove(index);
					gerentes.add(index, novo);
					return gerentes.get(index);
				} else
				//	Alerts.showAlertComum("Erro", "Nao ha clientes com esse codigo", AlertType.WARNING);
					return null;
			} else
			//	Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
				return null;
		} catch (Exception e) {
			return null;
		}

	}
	
	public Gerente edit(Gerente g, String nome, String senha) {
		try {
			g.setNome(nome);
			g.setSenha(senha);
			return g;
		}catch (NullPointerException e) {
	//		Alerts.showAlertComum("Erro", "Nao foi possivel editar", AlertType.WARNING);
			return null;
		}	
	}

	/**
	 * M�todo que mostra todos os gerentes da lista
	 */
	public void show() {
		for (Gerente ger : gerentes) {
			ger.mostraGerente();
		}
	}

	/**
	 * M�todo que retorna o �ndice da posi��o do gerente na lista
	 * 
	 * @param cod C�digo do gerente
	 * @return �ndice
	 */
	public int indexGerente(String cod) {
		try {
			if (!gerentes.isEmpty()) {
				int index = 0;
				for (Gerente ger : gerentes) {
					if (ger.getCod().equals(cod)) {
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
	 * M�todo para gerar os c�digos dos gerentes novos automaticamente
	 * 
	 * @return C�digo gerado
	 */
	public String geraCod() {
		String s = "GER" + countCod;
		countCod++;
		return s;
	}

	/**
	 * M�todo que verifica se os atributos sa� v�lidos
	 * 
	 * @param nome
	 * @return
	 */
	public boolean verificaAtributosGerente(String nome, String senha) {
		if (nome != null && !nome.isBlank() && senha != null && !senha.isBlank()) {
			return true;
		}
		return false;
	}

	public LinkedList<Gerente> getGerentes() {
		return gerentes;
	}

	public void setGerentes(LinkedList<Gerente> gerentes) {
		this.gerentes = gerentes;
	}

}
