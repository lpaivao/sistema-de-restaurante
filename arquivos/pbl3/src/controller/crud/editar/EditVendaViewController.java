package controller.crud.editar;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entidades.Vendas;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

public class EditVendaViewController implements Initializable {

	private Vendas v;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lbCod;

	@FXML
	private Label lbRegistro;

	@FXML
	private Label lblMensagemAviso;

	@FXML
	private ComboBox<String> cbPagamento;

	@FXML
	private TextField txtValor;

	@FXML
	void onBtnAlterarAction(ActionEvent event) {
		if(verificaAtributos()) {
			Main.getMenu().editVenda(v, Utils.tryParseToDouble(txtValor.getText()), cbPagamento.getValue());
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

	public void preencheCampos(Vendas v) {
		setV(v);
		lbCod.setText(v.getCod());
		lbRegistro.setText(v.getRegistro());
		txtValor.setText(Double.toString(v.getPrecoTotal()));
		cbPagamento.setValue(v.getModPag());
	}

	public Vendas getV() {
		return v;
	}

	public void setV(Vendas v) {
		this.v = v;
	}

	private void initComboBox() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("PIX");
		list.add("A vista");
		list.add("Cartao");
		ObservableList<String> obs = FXCollections.observableArrayList(list);
		cbPagamento.setItems(obs);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initComboBox();
		Constraints.setTextFieldDouble(txtValor);
	}

	public boolean verificaAtributos() {
		if(txtValor.getText().isBlank()) {
			return false;
		}
		return (Main.getMenu().getGerenciamentoVendas()
				.verificaAtributosVenda(Utils.tryParseToDouble(txtValor.getText()), cbPagamento.getValue()));
	}
}
