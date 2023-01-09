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
import model.entidades.Fornecedores;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

public class EditFornecedorViewController implements Initializable{

	private Fornecedores f;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lbAvisoCampoVazio;

	@FXML
	private Label lbCod;

	@FXML
	private TextField txtCnpj;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtNome;

	@FXML
	void onBtnAlterarAction(ActionEvent event) {
		if(verificaAtributos()) {
			Main.getMenu().editFornecedor(f, txtNome.getText(), txtCnpj.getText(), txtEndereco.getText());
			Utils.currentStage(event).close();	
		}
		else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
		}
	}

	@FXML
	void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	public void preencheCampos(Fornecedores f) {
		setF(f);
		lbCod.setText(f.getCod());
		txtNome.setText(f.getNome());
		txtCnpj.setText(f.getCnpj());
		txtEndereco.setText(f.getEndereco());
	}

	public Fornecedores getF() {
		return f;
	}

	public void setF(Fornecedores f) {
		this.f = f;
	}

	public boolean verificaAtributos() {
		return (Main.getMenu().getGerenciamentoFornecedores().verificaAtributosForn(txtNome.getText(),
				txtCnpj.getText(), txtEndereco.getText()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldInteger(txtCnpj);
	}

}
