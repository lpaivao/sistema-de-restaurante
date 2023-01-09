package controller.crud.editar;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entidades.Gerente;
import utils.Alerts;
import utils.Utils;

public class EditGerenteViewController implements Initializable {

	private Gerente g;

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
			Main.getMenu().editGerente(g, txtNomeNovo.getText(), txtSenhaNova.getText());
			Utils.currentStage(event).close();
		}
	}

	@FXML
	void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnAlterar.setDefaultButton(true);
	}

	public void preencheCampos(Gerente g) {
		setG(g);
		lbCod.setText(g.getCod());
		txtNomeNovo.setText(g.getNome());
		txtSenhaNova.setText(g.getSenha());
	}

	public Gerente getG() {
		return g;
	}

	public void setG(Gerente g) {
		this.g = g;
	}

}
