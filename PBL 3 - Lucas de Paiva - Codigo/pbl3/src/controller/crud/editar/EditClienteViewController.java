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
import model.entidades.Clientes;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

public class EditClienteViewController implements Initializable{

	private Clientes c;

	public void preencheCampos(Clientes c) {
		setC(c);
		lbCod.setText(c.getCod());
		txtNome.setText(c.getNome());
		txtCpf.setText(c.getCpf());
		txtEmail.setText(c.getEmail());
		txtTelefone.setText(c.getTelefone());
	}

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lbAviso;

	@FXML
	private Label lbCod;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtTelefone;

	@FXML
	void onBtnAlterarAction(ActionEvent event) {
		if(verificaAtributos()) {
			Main.getMenu().editCliente(c, txtNome.getText(), txtCpf.getText(), txtEmail.getText(), txtTelefone.getText());
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

	public Clientes getC() {
		return c;
	}

	public void setC(Clientes c) {
		this.c = c;
	}

	public boolean verificaAtributos() {
		return (Main.getMenu().getGerenciamentoClientes().verificaAtributosCliente(txtNome.getText(), txtCpf.getText(),
				txtEmail.getText(), txtTelefone.getText()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldInteger(txtCpf);
		Constraints.setTextFieldInteger(txtTelefone);
	}
}
