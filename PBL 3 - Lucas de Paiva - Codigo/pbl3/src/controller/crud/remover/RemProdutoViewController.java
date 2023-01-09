package controller.crud.remover;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import utils.Alerts;
import utils.Utils;

public class RemProdutoViewController implements Initializable {

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnRemover;

	@FXML
	private TextField txtCod;

	@FXML
	void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@FXML
	void onBtnRemoverAction(ActionEvent event) {

		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");

		if (result.get() == ButtonType.OK) {
			if (Main.getMenu().remProduto(txtCod.getText()) != null) {
				Utils.currentStage(event).close();
			}

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnRemover.setDefaultButton(true);
	}

}
