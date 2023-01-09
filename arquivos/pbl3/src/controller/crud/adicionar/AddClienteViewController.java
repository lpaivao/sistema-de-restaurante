package controller.crud.adicionar;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.Constraints;
import utils.Utils;

public class AddClienteViewController implements Initializable{

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label txtAvisoCampoVazio;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    void onBtnAdicionarAction(ActionEvent event) {
    	if(Main.getMenu().addCliente(txtNome.getText(), txtCpf.getText(), txtEmail.getText(), txtTelefone.getText())) {
    		Utils.currentStage(event).close();
    	}	
    }

    @FXML
    void onBtnCancelarAction(ActionEvent event) {
    	Utils.currentStage(event).close();
    }
    
    public boolean campoVazio(String nome, String cpf, String email, String telefone) {
    	if(!nome.isBlank() && !cpf.isBlank() && !email.isBlank() && !telefone.isBlank()) {
    		return true;
    	}
    	return false;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldInteger(txtCpf);
		Constraints.setTextFieldInteger(txtTelefone);
		Constraints.setTextFieldMaxLength(txtCpf, 11);
		Constraints.setTextFieldMaxLength(txtTelefone, 15);
	}

}
