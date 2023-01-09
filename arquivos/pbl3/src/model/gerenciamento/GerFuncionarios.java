package model.gerenciamento;

import java.util.LinkedList;

import javafx.scene.control.Alert.AlertType;
import model.entidades.Funcionario;
import utils.Alerts;

/**
 * Classe que gerencia os funcionarios do estabelecimento
 * 
 * @author lucas
 *
 */
public class GerFuncionarios {

	/**
	 * Lista de funcionarios do estabelecimento
	 */
	private LinkedList<Funcionario> funcionarios;

	/**
	 * Contador crescente para gerar c�digo �nico
	 */
	private int countCod;

	public GerFuncionarios() {
		funcionarios = new LinkedList<Funcionario>();
		countCod = 0;
	}

	/**
	 * M�todo para adicionar novos funcionarios na lista, e retorna true se
	 * adicionou na lista
	 * 
	 * @param nome
	 */
	public boolean add(String nome, String senha) {
		try {
			if (this.verificaAtributosFuncionario(nome, senha)) {
				Funcionario e = new Funcionario(geraCod(), nome, senha);
				funcionarios.add(e);
				return true;
			}
		//	Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * M�todo para remover os funcionarios a partir do seu c�digo �nico
	 * 
	 * @param cod C�digo �nico do funcionario
	 * @return funcionario removido
	 */
	public Funcionario remove(String cod) {
		if(cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		try {
			int index = this.indexFuncionario(cod);
			if (index > -1) {
				return funcionarios.remove(index);
			}
	//		Alerts.showAlertComum("Erro", "Nao ha funcionarios com esse codigo", AlertType.WARNING);
			return null;
		} catch (Exception e) {
			return null;
		}

	}
	
	/**
	 * Metodo que recebe um funcionario como parametro e faz o Set de seus
	 * novos atributos
	 * @param f
	 * @param nome
	 * @param senha
	 * @return
	 */
	public Funcionario edit(Funcionario f, String nome, String senha) {
		if(verificaAtributosFuncionario(nome, senha)) {
			try {
				f.setNome(nome);
				f.setSenha(senha);
				return f;
			}catch (NullPointerException e) {
				return null;
			}	
		}else {
		//	Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return null;
		}
			
	}

	/**
	 * M�todo que mostra todos os funcionarios da lista
	 */
	public void show() {
		for (Funcionario fur : funcionarios) {
			fur.mostraFuncionario();
		}
	}

	/**
	 * M�todo que retorna o �ndice da posi��o do funcionario na lista
	 * 
	 * @param cod C�digo do funcionario
	 * @return �ndice
	 */
	public int indexFuncionario(String cod) {
		try {
			if (!funcionarios.isEmpty()) {
				int index = 0;
				for (Funcionario func : funcionarios) {
					if (func.getCod().equals(cod)) {
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
	 * M�todo para gerar os c�digos dos funcionarios novos automaticamente
	 * 
	 * @return C�digo gerado
	 */
	public String geraCod() {
		String s = "FUNC" + countCod;
		countCod++;
		return s;
	}

	/**
	 * M�todo que verifica se o atibuto � v�lido
	 * 
	 * @param nome
	 * @return
	 */
	public boolean verificaAtributosFuncionario(String nome, String senha) {
		if (!nome.isBlank() && !senha.isBlank()) {
			return true;
		}
		return false;
	}

	public LinkedList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(LinkedList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
