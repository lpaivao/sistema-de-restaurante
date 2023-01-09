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
import model.entidades.Produtos;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

public class EditProdutoViewController implements Initializable{

	private Produtos p;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lbAvisoCampoVazio;

	@FXML
	private Label lbCod;

	@FXML
	private Label lbNome;

	@FXML
	private Label lbValidade;

	@FXML
	private TextField txtPreco;

	@FXML
	private TextField txtQuantidade;

	@FXML
	void onBtnAlterarAction(ActionEvent event) {
		if (verificaAtributos()) {
			Main.getMenu().editProduto(p, Utils.tryParseToDouble(txtPreco.getText()),
					Utils.tryParseToDouble(txtQuantidade.getText()));
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

	public Produtos getP() {
		return p;
	}

	public void setP(Produtos p) {
		this.p = p;
	}

	public void preencheCampos(Produtos p) {
		setP(p);
		lbCod.setText(p.getCod());
		lbNome.setText(p.getNome());
		lbValidade.setText(p.getNome());
		txtPreco.setText(Double.toString(p.getPreco()));
		txtQuantidade.setText(Double.toString(p.getQuantidade()));
	}

	public boolean verificaAtributos() {
		return (Main.getMenu().getGerenciamentoProdutos().verificaAtributosProdEdit(
				Utils.tryParseToDouble(txtPreco.getText()), Utils.tryParseToDouble(txtQuantidade.getText())));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldDouble(txtPreco);
		Constraints.setTextFieldDouble(txtQuantidade);
	}

}
