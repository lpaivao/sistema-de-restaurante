package controller.crud.adicionar;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entidades.Produtos;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

public class AddFornecedorViewController implements Initializable {

	private LinkedList<Produtos> produtos;

	@FXML
	private Button btnAdicionar;

	@FXML
	private Button btnAdicionarProduto;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnLimparProdutos;

	@FXML
	private Label lblCampoVazioProduto;

	@FXML
	private TableColumn<Produtos, String> tableColumnNomeProduto;

	@FXML
	private TableColumn<Produtos, String> tableColumnQuantidadeProduto;

	@FXML
	private TableView<Produtos> tableViewProdutos;

	@FXML
	private Label txtAvisoCampoVazio;

	@FXML
	private TextField txtCnpj;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtNomeProduto;

	@FXML
	private TextField txtQuantidadeProduto;

	@FXML
    void onBtnAdicionarAction(ActionEvent event) {
    	if (!verificaCamposVazios()) {
			LinkedList<Produtos> p = (LinkedList<Produtos>) produtos.clone();
			boolean b = Main.getMenu().addFornecedor(txtNome.getText(), txtCnpj.getText(), txtEndereco.getText(), p);
			if (!b) {
				Alerts.showAlertComum("Aviso", "Adicao nao feita", AlertType.WARNING);
			} else {
				produtos.clear();
				Utils.currentStage(event).close();
			}
		} else {
			txtAvisoCampoVazio.setText("Todos os campos devem ser preenchidos!");
		}
    }

	@FXML
	void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	/**
	 * Botao que adiciona os produtos ao fornecedor novo
	 * @param event
	 */
	@FXML
	void onBtnAdicionarProdutoAction(ActionEvent event) {
		
		if (!verificaCamposVaziosProduto()) {
			Double quantidade = Utils.tryParseToDouble(txtQuantidadeProduto.getText());
			if (quantidade != null) {
				Produtos p = new Produtos(txtNomeProduto.getText(), quantidade);
				produtos.add(p);
				updateTableView();
			} else {
				Alerts.showAlertComum("Aviso", "Digite a quantidade corretamente", AlertType.WARNING);
			}
		} else {
			lblCampoVazioProduto.setText("Todos os campos devem ser preenchidos!");
		}
	}

	@FXML
	void onBtnLimparProdutosAction(ActionEvent event) {
		clearTableView();
	}

	public void updateTableView() {
		ObservableList<Produtos> obs = FXCollections.observableArrayList(produtos);
		tableViewProdutos.setItems(obs);
	}

	private void clearTableView() {
		produtos.clear();
		updateTableView();
	}

	private boolean verificaCamposVaziosProduto() {
		if (txtNomeProduto.getText().isBlank() || txtQuantidadeProduto.getText().isBlank()) {
			return true;
		}
		return false;
	}

	private void initializeTableViewProdutos() {
		tableColumnNomeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
		tableColumnQuantidadeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("quantidade"));
	}

	private boolean verificaCamposVazios() {
		if (txtNome.getText().isBlank() || txtCnpj.getText().isBlank() || txtEndereco.getText().isBlank()) {
			return true;
		}
		return false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		produtos = new LinkedList<Produtos>();
		initializeTableViewProdutos();
		Constraints.setTextFieldDouble(txtQuantidadeProduto);
		Constraints.setTextFieldInteger(txtCnpj);
		btnAdicionar.setDefaultButton(true);
	}

}
