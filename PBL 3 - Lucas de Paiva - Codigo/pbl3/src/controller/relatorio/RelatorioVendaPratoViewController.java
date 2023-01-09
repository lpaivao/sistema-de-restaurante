package controller.relatorio;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import utils.Alerts;
import utils.Utils;

public class RelatorioVendaPratoViewController {

	@FXML
	private Button btnGerarRelatorio;

	@FXML
	private TextField txtCategoria;

	@FXML
	void onBtnGerarRelatorioAction(ActionEvent event) {
		if (!txtCategoria.getText().isBlank()) {
			if (Main.getMenu().geraRelatorioVendasPrato(txtCategoria.getText())) {
				Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
				Utils.currentStage(event).close();
			} else
				Alerts.showAlertComum("Erro", "Relatorio nao foi gerado", AlertType.WARNING);
		} else {
			Alerts.showAlertComum("Aviso", "Campo vazio", AlertType.WARNING);
		}
	}

}
