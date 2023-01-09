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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entidades.Fornecedores;
import model.entidades.Produtos;
import utils.Alerts;
import utils.Constraints;
import utils.Utils;

public class AddProdutoViewController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtBuscaFornecedor.setText("FORN0");
		Constraints.setTextFieldDouble(txtQtdProd);
		f = Main.getMenu().getGerenciamentoFornecedores().getFornecedores();
		initializeTableColumnsProdutoFornecedor();
	}

	private ObservableList<Produtos> prodFornecedor;

	private LinkedList<Fornecedores> f;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnSair;

	@FXML
	private Button btnBuscar;

	@FXML
	void onBtnAddAction(ActionEvent event) {

		if (verificaCamposVazios(txtQtdProd.getText())) {
			Produtos novo = tableViewProdutoFornecedor.getSelectionModel().getSelectedItem();
			if (novo != null) {
				Double qtd = Utils.tryParseToDouble(txtQtdProd.getText());
				if (qtd > 0) {
					boolean b = Main.getMenu().addProduto(novo, qtd);
					if(b) {
						Utils.currentStage(event).close();	
					}
					else {
						Alerts.showAlertComum("Aviso", "Um erro ocorreu", AlertType.WARNING);
					}
					
				} else {
					Alerts.showAlertComum("Aviso", "Digite uma quantidade maior que 0", AlertType.WARNING);
				}

			} else {
				Alerts.showAlertComum("Aviso", "Selecione um produto na lista", AlertType.WARNING);
			}

		}
		else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
		}
	}

	@FXML
	void onBtnSairAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	// table view produtos do fornecedor

	@FXML
	private TextField txtBuscaFornecedor;

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		if (!txtBuscaFornecedor.getText().isBlank()) {
			Fornecedores f = buscaFornecedor(txtBuscaFornecedor.getText());
			if (f != null) {
				updateTableProdutosFornecedor(f);
			} else {
				Alerts.showAlertComum("Erro", "Fornecedor não encontrado", AlertType.ERROR);
			}
		} else {
			Alerts.showAlertComum("Erro", "Busca em branco", AlertType.WARNING);
		}
	}

	@FXML
	private TableColumn<Produtos, String> tableColumnCodProdutoFornecedor;

	@FXML
	private TableColumn<Produtos, Fornecedores> tableColumnFornecedor;

	@FXML
	private TableColumn<Produtos, String> tableColumnNomeProdutoFornecedor;

	@FXML
	private TableColumn<Produtos, String> tableColumnPrecoProdutoFornecedor;

	@FXML
	private TableColumn<Produtos, String> tableColumnQuantidadeProdutoFornecedor;

	@FXML
	private TableColumn<Produtos, String> tableColumnCodProdutoEstoque;

	@FXML
	private TableColumn<Produtos, String> tableColumnValidadeProdutoFornecedor;

	@FXML
	private TableView<Produtos> tableViewProdutoFornecedor;

	// -------------------------------

	@FXML
	private TextField txtQtdProd;

	/**
	 * metodo que inicializa as colunas dos dados do produto do fornecedor,
	 * incluindo o nome do fornecedor
	 */
	public void initializeTableColumnsProdutoFornecedor() {
		tableColumnCodProdutoFornecedor.setCellValueFactory(new PropertyValueFactory<Produtos, String>("cod"));
		tableColumnNomeProdutoFornecedor.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
		tableColumnPrecoProdutoFornecedor.setCellValueFactory(new PropertyValueFactory<Produtos, String>("preco"));
		tableColumnValidadeProdutoFornecedor
				.setCellValueFactory(new PropertyValueFactory<Produtos, String>("validade"));
		tableColumnQuantidadeProdutoFornecedor
				.setCellValueFactory(new PropertyValueFactory<Produtos, String>("quantidade"));
		tableColumnFornecedor.setCellValueFactory(new PropertyValueFactory<Produtos, Fornecedores>("fornecedor"));

		tableColumnFornecedor.setCellFactory(param -> new TableCell<Produtos, Fornecedores>() {
			@Override
			protected void updateItem(Fornecedores obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setText(null);
					return;
				} else {
					setText(obj.getNome());
				}

			}
		});
	}

	public void updateTableProdutosFornecedor(Fornecedores f) {
		prodFornecedor = FXCollections.observableArrayList(f.getProdutos());
		tableViewProdutoFornecedor.setItems(prodFornecedor);
	}

	public Fornecedores buscaFornecedor(String nome) {
		return Main.getMenu().getGerenciamentoProdutos().buscaFornecedor(nome, f);
	}

	public boolean verificaCamposVazios(String quantidade) {
		if (!quantidade.isBlank()) {
			return true;
		}
		return false;
	}

}
