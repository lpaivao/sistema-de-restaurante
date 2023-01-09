package controller.crud.adicionar;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entidades.Produtos;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

public class AddPratoViewController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		produtos = new LinkedList<Produtos>();
		Constraints.setTextFieldDouble(txtPreco);
		Constraints.setTextFieldDouble(txtQuantidadeProduto);
		btnAdicionar.setDefaultButton(true);
		
		initializeTableViewProdutos();
		initializeComboBox();
	}

	private ArrayList<String> categorias;

	@FXML
	private Button btnAddCategoria;

	@FXML
	private Button btnAdicionar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnRemCategoria;

	@FXML
	private ComboBox<String> cbCategoria;

	@FXML
	private Label lblAddCategoria;

	@FXML
	private Label lblAddPrato;

	@FXML
	private Label txtAvisoCampoVazio;

	@FXML
	private TextField txtAddNovaCategoria;

	@FXML
	private TextField txtDescricao;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtPreco;

	@FXML
	private TextField txtRemCategoria;

	@FXML
	void OnBtnRemCategoriaActon(ActionEvent event) {
		if (!txtRemCategoria.getText().isBlank()) {
			categorias.remove(txtRemCategoria.getText());
			updateComboBox();
			txtRemCategoria.clear();
		}
	}

	@FXML
	void onBtnAddCategoriaAction(ActionEvent event) {
		if (!txtAddNovaCategoria.getText().isBlank()) {
			categorias.add(txtAddNovaCategoria.getText());
			updateComboBox();
			txtAddNovaCategoria.clear();
		}
	}

	@FXML
	void onBtnAdicionarAction(ActionEvent event) {
		if (!verificaCamposVazios()) {
			Double preco = Utils.tryParseToDouble(txtPreco.getText());
			LinkedList<Produtos> p = (LinkedList<Produtos>) produtos.clone();
			boolean b = Main.getMenu().addPratoCardapio(txtNome.getText(), txtDescricao.getText(), preco,
					cbCategoria.getValue(), p);
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
	 * Inicializa a combo box de categorias
	 */
	public void initializeComboBox() {
		
		categorias = new ArrayList<String>();

		categorias.add("Massas");
		categorias.add("Entradas");
		categorias.add("Sobremesas");
		categorias.add("Acompanhamentos");
		categorias.add("Bebidas");

		ObservableList<String> obs = FXCollections.observableArrayList(categorias);

		cbCategoria.setItems(obs);
	}

	public void updateComboBox() {
		ObservableList<String> obs = FXCollections.observableArrayList(categorias);
		cbCategoria.setItems(obs);
	}

	private boolean verificaCamposVazios() {
		if (txtNome.getText().isBlank() || txtPreco.getText().isBlank() || txtDescricao.getText().isBlank()) {
			return true;
		}
		return false;
	}

	private LinkedList<Produtos> produtos;

	@FXML
	private Button btnAdicionarProduto;

	@FXML
	private Button btnLimparProdutos;

	@FXML
	private Label lblAddProdutos;

	@FXML
	private TableColumn<Produtos, String> tableColumnNomeProduto;

	@FXML
	private TableColumn<Produtos, String> tableColumnQuantidadeProduto;

	@FXML
	private TableView<Produtos> tableViewProdutos;

	@FXML
	private TextField txtNomeProduto;

	@FXML
	private TextField txtQuantidadeProduto;

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

	@FXML
	private Label lblCampoVazioProduto;

	public ArrayList<String> getCategorias() {
		return categorias;
	}

}
