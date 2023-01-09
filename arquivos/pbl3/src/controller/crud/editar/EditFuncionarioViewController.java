package controller.crud.editar;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entidades.Clientes;
import model.entidades.Funcionario;
import model.entidades.Gerente;
import utils.Alerts;
import utils.Utils;

public class EditFuncionarioViewController {

	private Funcionario f;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lblMensagemAviso;

	@FXML
	private Label lbCod;

	@FXML
	private TextField txtNomeNovo;

	@FXML
	private TextField txtSenhaNova;

	@FXML
	void onBtnAlterarAction(ActionEvent event) {
		if (txtNomeNovo.getText().isEmpty() || txtSenhaNova.getText().isEmpty()) {
			lblMensagemAviso.setText("Todos os campos precisam ser preenchidos!");
		} else {
			Main.getMenu().editFuncionario(f, txtNomeNovo.getText(), txtSenhaNova.getText());
			Utils.currentStage(event).close();
		}
	}

	@FXML
	void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	public void preencheCampos(Funcionario f) {
		setf(f);
		lbCod.setText(f.getCod());
		txtNomeNovo.setText(f.getNome());
		txtSenhaNova.setText(f.getSenha());
	}

	public Funcionario getF() {
		return f;
	}

	public void setf(Funcionario f) {
		this.f = f;
	}

}
