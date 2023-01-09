package controller.crud.adicionar;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.Utils;

public class AddFuncionarioViewController implements Initializable{

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnAdicionar;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtSenha;

	@FXML
	void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@FXML
	void onBtnAdicionarAction(ActionEvent event) {
		if(Main.getMenu().addFuncionario(txtNome.getText(), txtSenha.getText())) {
			Utils.currentStage(event).close();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnAdicionar.setDefaultButton(true);
	}
	
}
