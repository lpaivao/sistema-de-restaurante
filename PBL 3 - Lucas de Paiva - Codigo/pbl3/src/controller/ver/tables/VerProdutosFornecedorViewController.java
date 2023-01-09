package controller.ver.tables;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entidades.Produtos;

public class VerProdutosFornecedorViewController {

	@FXML
	private TableColumn<Produtos, String> tableColumnNomeProduto;

	@FXML
	private TableColumn<Produtos, String> tableColumnPrecoProduto;

	@FXML
	private TableColumn<Produtos, String> tableColumnQuantidadeProduto;

	@FXML
	private TableColumn<Produtos, String> tableColumnValidadeProduto;

	@FXML
	private TableView<Produtos> tableViewProduto;

	public void initializeColumns(LinkedList<Produtos> p) {
		tableColumnNomeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
		tableColumnPrecoProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("preco"));
		tableColumnValidadeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("validade"));
		tableColumnQuantidadeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("quantidade"));
		ObservableList<Produtos> obs = FXCollections.observableArrayList(p);
		tableViewProduto.setItems(obs);
	}
	
}
