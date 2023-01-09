package controller.ver.tables;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entidades.Produtos;

public class VerCardapioProdutosController implements Initializable {

    @FXML
    private TableColumn<Produtos, String> tableColumnNomeProduto;

    @FXML
    private TableColumn<Produtos, String> tableColumnQuantidadeProduto;
    
    @FXML
    private TableView<Produtos> tbProdutos;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
    
	public void initializeColumns(LinkedList<Produtos> p) {
		tableColumnNomeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
		tableColumnQuantidadeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("quantidade"));
		ObservableList<Produtos> obs = FXCollections.observableArrayList(p);
		tbProdutos.setItems(obs);
	}
}
