package controller.crud.adicionar;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entidades.Itens;
import utils.Alerts;
import utils.Utils;

public class AddVendaViewController implements Initializable {

	private ObservableList<Itens> obsCardapio;

	private LinkedList<Itens> listTemp;
	
	@FXML
    private ComboBox<String> cbPagamento;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listTemp = new LinkedList<Itens>();
		initComboBox();
		initalizeTableCardapio();
		initalizeTablePratosVenda();

	}

	public void initalizeTableCardapio() {
		tableColumnCodCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("cod"));
		tableColumnNomeCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("nome"));
		tableColumnDescricaoCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("descricao"));
		tableColumnPrecoCardapio.setCellValueFactory(new PropertyValueFactory<Itens, Double>("preco"));
		tableColumnCategoriaCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("categoria"));
		List<Itens> list = Main.getMenu().getCardapio().getItens();
		obsCardapio = FXCollections.observableArrayList(list);
		tableViewCardapio.setItems(obsCardapio);
		initAdicionarButtonsCardapio();
	}
	
	public void initalizeTablePratosVenda() {
		tableColumnCodPratoVenda.setCellValueFactory(new PropertyValueFactory<Itens, String>("cod"));
		tableColumnNomePratoVenda.setCellValueFactory(new PropertyValueFactory<Itens, String>("nome"));
		tableColumnDescricaoPratoVenda.setCellValueFactory(new PropertyValueFactory<Itens, String>("descricao"));
		tableColumnPrecoPratoVenda.setCellValueFactory(new PropertyValueFactory<Itens, Double>("preco"));
		tableColumnCategoriaPratoVenda.setCellValueFactory(new PropertyValueFactory<Itens, String>("categoria"));
		initRemoverButtonsPratoVenda();
	}

	/**
	 * Metodo que cria os botoes para adicionar um item a lista de itens para a venda
	 */
	private void initAdicionarButtonsCardapio() {
		tableColumnAddPrato.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnAddPrato.setCellFactory(param -> new TableCell<Itens, Itens>() {
			private final Button button = new Button("+");

			@Override
			protected void updateItem(Itens obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> addPratoCardapio(obj));
			}
		});

	}

	/**
	 * Metodo que adiciona um item (prato) a uma lista temporaria e verifica
	 * a disponibilidade no estoque
	 * @param i
	 */
	public void addPratoCardapio(Itens i) {
		
		LinkedList<Itens> temp;

		temp = (LinkedList<Itens>) listTemp.clone();

		
		temp.add(i);
		
		boolean b = verificaEstoqueDosItens(temp);
		
		if(b) {
			listTemp.add(i);
			updateTablePratosVenda();	
		}
	}

	/**
	 * Metodo de criar os botoes para a remocao de algum item ja selecionado da venda
	 */
	private void initRemoverButtonsPratoVenda() {
		tableColumnRemPratoVenda.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnRemPratoVenda.setCellFactory(param -> new TableCell<Itens, Itens>() {
			private final Button button = new Button("-");

			@Override
			protected void updateItem(Itens obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> remPratoVenda(obj));
			}
		});

	}
	
	/**
	 * Metodo que remove o item da lista de venda
	 * @param i
	 */
	public void remPratoVenda(Itens i) {
		boolean b = listTemp.remove(i);
		
		if(b) {
			updateTablePratosVenda();	
		}
		else
			Alerts.showAlertComum("Erro", "Não foi possível remover o prato", AlertType.ERROR);
	}

	private void updateTableCardapio() {
		List<Itens> list = Main.getMenu().getCardapio().getItens();
		obsCardapio = FXCollections.observableArrayList(list);
		tableViewCardapio.setItems(obsCardapio);
		initAdicionarButtonsCardapio();
	}

	private void updateTablePratosVenda() {
		ObservableList<Itens> itens;
		itens = FXCollections.observableArrayList(listTemp);
		tableViewPratosVenda.setItems(itens);
		initRemoverButtonsPratoVenda();
	}

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnFinalizar;

	@FXML
	private TableColumn<Itens, Itens> tableColumnAddPrato;

	@FXML
	private TableColumn<Itens, String> tableColumnCategoriaCardapio;

	@FXML
	private TableColumn<Itens, String> tableColumnCategoriaPratoVenda;

	@FXML
	private TableColumn<Itens, String> tableColumnCodCardapio;

	@FXML
	private TableColumn<Itens, String> tableColumnCodPratoVenda;

	@FXML
	private TableColumn<Itens, String> tableColumnDescricaoCardapio;

	@FXML
	private TableColumn<Itens, String> tableColumnDescricaoPratoVenda;

	@FXML
	private TableColumn<Itens, String> tableColumnNomeCardapio;

	@FXML
	private TableColumn<Itens, String> tableColumnNomePratoVenda;

	@FXML
	private TableColumn<Itens, Double> tableColumnPrecoCardapio;

	@FXML
	private TableColumn<Itens, Double> tableColumnPrecoPratoVenda;

	@FXML
	private TableColumn<Itens, Itens> tableColumnRemPratoVenda;

	@FXML
	private TableView<Itens> tableViewCardapio;

	@FXML
	private TableView<Itens> tableViewPratosVenda;

	@FXML
	private TextField txtBuscaPrato;
	
	@FXML
	private Label lbNaoEcontrado;

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		if(txtBuscaPrato.getText().isBlank()) {
			updateTableCardapio();
		}
		else
			updateTableCardapioBuscado();		
	}
	
	/**
	 * Busca os pratos correspondentes ao nome buscado e atualiza a lista
	 * @param i
	 * @return
	 */
	private void updateTableCardapioBuscado() {
		LinkedList<Itens> cardapioBuscado = new LinkedList<Itens>();
		for(Itens i : obsCardapio) {
			if(i.getNome().contains(txtBuscaPrato.getText())) {
				cardapioBuscado.add(i);
			}
		}
		if(cardapioBuscado.isEmpty()) {
			lbNaoEcontrado.setText("Item não encontrado");
		}
		else {
			ObservableList<Itens> obsBuscado;
			obsBuscado = FXCollections.observableArrayList(cardapioBuscado);
			tableViewCardapio.setItems(obsBuscado);
			initAdicionarButtonsCardapio();
		}
	}

	@FXML
	void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	/**
	 * Verifica todos os itens selecionados para compra, e realiza a venda caso
	 * haja disponibilidade no estoque
	 * @param event
	 */
	@FXML
	void onBtnFinalizarAction(ActionEvent event) {
		if(!listTemp.isEmpty() && !cbPagamento.getValue().isBlank()) {
			boolean b = Main.getMenu().addVenda(cbPagamento.getValue(), (LinkedList<Itens>) listTemp.clone());
			if(b) {
				Alerts.showAlertComum("Confirmação", "Venda realizada", AlertType.CONFIRMATION);
				listTemp.clear();
				Utils.currentStage(event).close();
			}
			else {
				Alerts.showAlertComum("Erro", "Venda não realizada", AlertType.ERROR);
			}
		}
		else {		
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
		}
		
	}

	private boolean verificaEstoqueDosItens(LinkedList<Itens> i) {
		return Main.getMenu().Estoque().verificaItensAntesDaVenda(i,
				Main.getMenu().getGerenciamentoProdutos().getProdutos());
	}
	
	private void initComboBox() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("PIX");
		list.add("A vista");
		list.add("Cartao");
		ObservableList<String> obs = FXCollections.observableArrayList(list);
		cbPagamento.setItems(obs);
		cbPagamento.setValue("PIX");
	}
}
