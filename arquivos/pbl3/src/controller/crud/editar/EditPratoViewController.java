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
import model.entidades.Itens;
import utils.Constraints;
import utils.Utils;

public class EditPratoViewController implements Initializable{
	
	private Itens i;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lbAvisoCampoVazio;

	@FXML
	private Label lbCod;

	@FXML
	private TextField txtCategoria;

	@FXML
	private TextField txtDescricao;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtPreco;

	@FXML
	void onBtnAlterarAction(ActionEvent event) {
		if (camposVazios()) {
			Main.getMenu().editPratoCardapio(i, txtNome.getText(), txtDescricao.getText(),
					Utils.tryParseToDouble(txtPreco.getText()), txtCategoria.getText());
			Utils.currentStage(event).close();
		} else {
			lbAvisoCampoVazio.setText("Um ou mais campos vazios");
		}
	}

	@FXML
	void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	private boolean camposVazios() {
		if (!txtNome.getText().isBlank() && !txtDescricao.getText().isBlank() && !txtPreco.getText().isBlank()
				&& !txtCategoria.getText().isBlank()) {
			return true;
		}
		return false;
	}

	public void preencheCampos(Itens i) {
		setI(i);
		lbCod.setText(i.getCod());
		txtNome.setText(i.getNome());
		txtDescricao.setText(i.getDescricao());
		txtPreco.setText(Double.toString(i.getPreco()));
		txtCategoria.setText(i.getCategoria());

	}

	public Itens getI() {
		return i;
	}

	public void setI(Itens i) {
		this.i = i;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldDouble(txtPreco);
	}

}
