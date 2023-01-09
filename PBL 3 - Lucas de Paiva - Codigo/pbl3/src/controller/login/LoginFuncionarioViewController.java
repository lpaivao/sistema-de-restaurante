package controller.login;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entidades.Funcionario;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

/**
 * Controller da tela de login do funcionario
 * @author lucas
 *
 */
public class LoginFuncionarioViewController implements Initializable {

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnLogar;

	@FXML
	private TextField txtLogin;

	@FXML
	private TextField txtSenha;

	@FXML
	void onBtnCancelarAction(ActionEvent event) {

	}

	@FXML
	void onBtnLogarAction(ActionEvent event) {
		if (verificaLoginFuncionario(txtLogin.getText(), txtSenha.getText(),
				Main.getMenu().getGerenciamentoFuncionarios().getFuncionarios())) {
			Main.mudaTela("menuFuncionario");
			Utils.currentStage(event).close();
		} else {
			Alerts.showAlertComum("Erro de busca", "Funcionario não encontrado", AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldMaxLength(txtLogin, 6);
		Constraints.setTextFieldMaxLength(txtSenha, 10);
	}

	private boolean verificaLoginFuncionario(String login, String senha, LinkedList<Funcionario> listF) {
		for (Funcionario f : listF) {
			if (f.getCod().contains(login) && f.getSenha().contains(senha)) {
				return true;
			}
		}
		return false;
	}

}
