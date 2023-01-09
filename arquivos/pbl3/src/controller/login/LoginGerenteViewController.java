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
import model.entidades.Gerente;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

/**
 * Controller da tela de login do gerente
 * @author lucas
 *
 */
public class LoginGerenteViewController implements Initializable {

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
    	Utils.currentStage(event).close();
    }

    @FXML
    void onBtnLogarAction(ActionEvent event) {
    	if(verificaLoginGerente(txtLogin.getText(), txtSenha.getText(), Main.getMenu().getGerenciamentoGerentes().getGerentes())) {
			Main.mudaTela("menuGerente");
			Utils.currentStage(event).close();
		}
		else {
			Alerts.showAlertComum("Erro de busca", "Gerente não encontrado", AlertType.ERROR);
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldMaxLength(txtLogin, 6);
		Constraints.setTextFieldMaxLength(txtSenha, 10);
	}

	private boolean verificaLoginGerente(String login, String senha, LinkedList<Gerente> listG) {
		for (Gerente g : listG) {
			if (g.getCod().contains(login) && g.getSenha().contains(senha)) {
				return true;
			}
		}
		return false;
	}

}
