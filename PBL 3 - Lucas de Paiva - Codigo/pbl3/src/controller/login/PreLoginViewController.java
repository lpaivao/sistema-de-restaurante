package controller.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Alerts;
import utils.Utils;

public class PreLoginViewController implements Initializable {

    @FXML
    private Button btnLoginFuncionario;

    @FXML
    private Button btnLoginGerente;

    @FXML
    void onBtnLoginFuncionarioAction(ActionEvent event) {
    	this.loadView("/view/login/LoginFuncionarioView.fxml", Utils.currentStage(event));
    }

    @FXML
    void onBtnLoginGerenteAction(ActionEvent event) {
    	this.loadView("/view/login/LoginGerenteView.fxml", Utils.currentStage(event));
    }
    
    private void loadView(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			Parent parent = loader.load();

			Stage stage = new Stage();
			stage.setTitle(" ");
			stage.setScene(new Scene(parent));
			stage.initOwner(parentStage);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
