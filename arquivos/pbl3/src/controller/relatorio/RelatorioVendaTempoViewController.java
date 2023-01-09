package controller.relatorio;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import utils.Alerts;
import utils.Utils;

public class RelatorioVendaTempoViewController implements Initializable{

	private LocalDate data1, data2;

    @FXML
    private Button btnGerarRelatorio;

    @FXML
    private DatePicker dpData1;

    @FXML
    private DatePicker dpData2;

    /**
     * Acao que chama o metodo de gerar relatorio entre um periodo especifico
     * @param event
     */
    @FXML
    void OnBtnGerarRelatorioAction(ActionEvent event) {
    	if(dataVazia()) {
    		data1 = dpData1.getValue();
    		data2 = dpData2.getValue();
    		if(Main.getMenu().geraRelatorioVendasPeriodo(data1, data2)) {
    			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
    			Utils.currentStage(event).close();
    		}else
    			Alerts.showAlertComum("Erro", "Relatorio nao foi gerado", AlertType.WARNING);
    	}
    	else {
    		Alerts.showAlertComum("Erro", "Uma das datas está vazia", AlertType.WARNING);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	/**
	 * Verifica se as datas do date picker estao vazias
	 * @return
	 */
	public boolean dataVazia() {
		if(dpData1.getValue() != null && dpData2.getValue() != null) {
			return true;
		}
		return false;
	}

}
