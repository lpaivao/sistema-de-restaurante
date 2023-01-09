package model.gerenciamento;

import java.util.LinkedList;

import javafx.scene.control.Alert.AlertType;
import model.entidades.Clientes;
import utils.Alerts;

public class GerClientes {

	private LinkedList<Clientes> clientes;

	private int countCod;

	public GerClientes() {
		clientes = new LinkedList<Clientes>();
	}

	/**
	 * Metodo para adicionar novos clientes na lista, e retorna true se adicionou na
	 * lista
	 * 
	 * @param nome
	 */
	public boolean add(String nome, String cpf, String email, String telefone) {
		try {
			if (verificaAtributosCliente(nome, cpf, email, telefone)) {
				Clientes e = new Clientes(geraCod(), nome, cpf, email, telefone);
				clientes.add(e);
				return true;
			} else {
			//	Alerts.showAlertComum("Erro", "Um ou mais campos nao preenchidos", AlertType.WARNING);
				return false;
			}
		} catch (Exception e) {
		//	Alerts.showAlertComum("Erro", "Nao foi possivel adicionar", AlertType.WARNING);
			return false;
		}

	}

	/**
	 * Metodo para remover os clientes a partir do seu codigo unico
	 * 
	 * @param cod C�digo �nico do cliente
	 * @return Cliente removido
	 */
	public Clientes remove(String cod) {
		if(cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		try {
			int index = this.indexCliente(cod);
			if (index > -1) {
				return clientes.remove(index);
			} else {
			//	Alerts.showAlertComum("Erro", "Nao ha clientes com esse codigo", AlertType.WARNING);
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Metodo que recebe um cliente e seus atributos novos, e faz o Set em cada um
	 * @param c
	 * @param nome
	 * @param cpf
	 * @param email
	 * @param telefone
	 * @return
	 */
	public Clientes edit(Clientes c, String nome, String cpf, String email, String telefone) {
		if(!verificaAtributosCliente(nome, cpf, email, telefone)) {
		//	Alerts.showAlertComum("Erro", "Um ou mais campos nao preenchidos", AlertType.WARNING);
			return null;
		}
		if (c != null) {
			try {
				c.setNome(nome);
				c.setCpf(cpf);
				c.setEmail(email);
				c.setTelefone(telefone);
				return c;
			} catch (NullPointerException e) {
				return null;
			}
		} else {
			Alerts.showAlertComum("Erro", "Nao foi possivel editar", AlertType.WARNING);
			return null;
		}
	}

	/**
	 * M�todo que retorna o �ndice da posi��o do cliente na lista
	 * 
	 * @param cod C�digo do cliente
	 * @return �ndice
	 */
	public int indexCliente(String cod) {
		try {
			if (!clientes.isEmpty()) {
				int index = 0;
				for (Clientes c : clientes) {
					if (c.getCod().equals(cod)) {
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
	 * M�todo para gerar os c�digos dos clientes novos automaticamente
	 * 
	 * @return C�digo gerado
	 */
	public String geraCod() {
		String s = "CLI" + countCod;
		countCod++;
		return s;
	}

	/**
	 * M�todo que verifica se os atributos sa� v�lidos
	 * 
	 * @param nome
	 * @return
	 */
	public boolean verificaAtributosCliente(String nome, String cpf, String email, String telefone) {
		if (!nome.isBlank() && !cpf.isBlank() && !email.isBlank() && !telefone.isBlank()) {
			return true;
		}
		return false;
	}

	public LinkedList<Clientes> getClientes() {
		return clientes;
	}

	public void setClientes(LinkedList<Clientes> clientes) {
		this.clientes = clientes;
	}

}
