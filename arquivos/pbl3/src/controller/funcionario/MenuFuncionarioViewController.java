package controller.funcionario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import controller.ver.tables.VerCardapioProdutosController;
import controller.ver.tables.VerProdutosFornecedorViewController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entidades.Clientes;
import model.entidades.Fornecedores;
import model.entidades.Funcionario;
import model.entidades.Gerente;
import model.entidades.Itens;
import model.entidades.Produtos;
import model.entidades.Vendas;
import utils.Alerts;
import utils.Utils;

public class MenuFuncionarioViewController implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeTableViewCliente();
		initializeTableViewGerente();
		initializeTableViewFuncionario();
		initializeTableViewCardapio();
		initializeTableViewProdutos();
		initializeTableViewFornecedores();
		initializeTableViewVendas();
	}

	private ObservableList<Gerente> obsGerentes;
	private ObservableList<Funcionario> obsFuncionarios;
	private ObservableList<Itens> obsCardapio;
	private ObservableList<Produtos> obsProdutos;
	private ObservableList<Fornecedores> obsFornecedores;
	private ObservableList<Vendas> obsVendas;
	private ObservableList<Clientes> obsClientes;

	// Cliente
	// ----------------------------------------------------------------------------------------

	@FXML
	private Button btnAddCliente;
	@FXML
	private Button btnRemoverCliente;
	@FXML
	private Button btnRefreshTableCliente;

	@FXML
	private TableColumn<Clientes, String> tableColumnCodCliente;
	@FXML
	private TableColumn<Clientes, String> tableColumnNomeCliente;
	@FXML
	private TableColumn<Clientes, String> tableColumnCpfCliente;
	@FXML
	private TableColumn<Clientes, String> tableColumnEmailCliente;
	@FXML
	private TableColumn<Clientes, String> tableColumnTelefoneCliente;

	@FXML
	private TableView<Clientes> tableViewCliente;

	@FXML
	void onBtnAddClienteAction(ActionEvent event) {
		loadView("/view/crud/adicionar/AddClienteView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRemoverClienteAction(ActionEvent event) {
		loadView("/view/crud/remover/RemClienteView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRefreshTableClienteAction(ActionEvent event) {
		updateTableViewCliente();
	}

	private void initializeTableViewCliente() {
		tableColumnCodCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cod"));
		tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("nome"));
		tableColumnCpfCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cpf"));
		tableColumnEmailCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("email"));
		tableColumnTelefoneCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("telefone"));

		List<Clientes> list = Main.getMenu().getGerenciamentoClientes().getClientes();
		obsClientes = FXCollections.observableArrayList(list);
		tableViewCliente.setItems(obsClientes);
	}

	private void updateTableViewCliente() {
		List<Clientes> list = Main.getMenu().getGerenciamentoClientes().getClientes();
		obsClientes = FXCollections.observableArrayList(list);
		tableViewCliente.setItems(obsClientes);
	}

	// Gerente
	// ----------------------------------------------------------------------------------------
	@FXML
	private Button btnAdicionarGerente;
	@FXML
	private Button btnRemoverGerente;

	@FXML
	private TableColumn<Gerente, String> tableColumnCodGerente;
	@FXML
	private TableColumn<Gerente, String> tableColumnNomeGerente;
	@FXML
	private TableColumn<Gerente, String> tableColumnSenhaGerente;
	@FXML
	private TableView<Gerente> tableViewGerente;

	@FXML
	void onBtnAdicionarGerenteAction(ActionEvent event) {
		loadView("/view/crud/adicionar/AddGerenteView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRemoverGerenteAction(ActionEvent event) {
		loadView("/view/crud/remover/RemGerenteView.fxml", Utils.currentStage(event));
	}

	@FXML
	private Button btnRefreshTableGerente;

	@FXML
	void onBtnRefreshTableGerenteAction(ActionEvent event) {
		updateTableViewGerente();
	}

	public void updateTableViewGerente() {
		List<Gerente> list = Main.getMenu().getGerenciamentoGerentes().getGerentes();
		obsGerentes = FXCollections.observableArrayList(list);
		tableViewGerente.setItems(obsGerentes);
	}

	public void initializeTableViewGerente() {
		tableColumnCodGerente.setCellValueFactory(new PropertyValueFactory<Gerente, String>("cod"));
		tableColumnNomeGerente.setCellValueFactory(new PropertyValueFactory<Gerente, String>("nome"));
		tableColumnSenhaGerente.setCellValueFactory(new PropertyValueFactory<Gerente, String>("senha"));
		List<Gerente> list = Main.getMenu().getGerenciamentoGerentes().getGerentes();
		obsGerentes = FXCollections.observableArrayList(list);
		tableViewGerente.setItems(obsGerentes);
	}

	// Funcionario
	@FXML
	private Button btnAdicionarFuncionario;
	@FXML
	private Button btnRemoverFuncionario;

	@FXML
	private TableColumn<Funcionario, String> tableColumnCodFuncionario;
	@FXML
	private TableColumn<Funcionario, String> tableColumnNomeFuncionario;
	@FXML
	private TableColumn<Funcionario, String> tableColumnSenhaFuncionario;
	@FXML
	private TableView<Funcionario> tableViewFuncionario;

	@FXML
	void onBtnAdicionarFuncionarioAction(ActionEvent event) {
		loadView("/view/crud/adicionar/AddFuncionarioView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRemoverFuncionarioAction(ActionEvent event) {
		loadView("/view/crud/remover/RemFuncionarioView.fxml", Utils.currentStage(event));
	}

	@FXML
	private Button btnRefreshTableFuncionario;

	@FXML
	void onBtnRefreshTableFuncionarioAction(ActionEvent event) {
		updateTableViewFuncionario();
	}

	private void updateTableViewFuncionario() {
		List<Funcionario> list = Main.getMenu().getGerenciamentoFuncionarios().getFuncionarios();
		obsFuncionarios = FXCollections.observableArrayList(list);
		tableViewFuncionario.setItems(obsFuncionarios);
	}

	public void initializeTableViewFuncionario() {
		tableColumnCodFuncionario.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cod"));
		tableColumnNomeFuncionario.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
		tableColumnSenhaFuncionario.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("senha"));
		List<Funcionario> list = Main.getMenu().getGerenciamentoFuncionarios().getFuncionarios();
		obsFuncionarios = FXCollections.observableArrayList(list);
		tableViewFuncionario.setItems(obsFuncionarios);
	}

	// Cardapio

	@FXML
	private TableColumn<Itens, String> tableColumnCategoriaCardapio;
	@FXML
	private TableColumn<Itens, String> tableColumnCodCardapio;
	@FXML
	private TableColumn<Itens, String> tableColumnDescricaoCardapio;
	@FXML
	private TableColumn<Itens, String> tableColumnNomeCardapio;
	@FXML
	private TableColumn<Itens, String> tableColumnPrecoCardapio;

	@FXML
	private TableView<Itens> tableViewCardapio;

	@FXML
	private TableColumn<Itens, Itens> tableColumnProdutosCardapio;

	@FXML
	private Button btnRefreshTableCardapio;

	@FXML
	void onBtnRefreshTableCardapioAction(ActionEvent event) {
		updateTableViewCardapio();
	}

	public void initializeTableViewCardapio() {
		tableColumnCodCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("cod"));
		tableColumnNomeCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("nome"));
		tableColumnDescricaoCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("descricao"));
		tableColumnPrecoCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("preco"));
		tableColumnCategoriaCardapio.setCellValueFactory(new PropertyValueFactory<Itens, String>("categoria"));
		List<Itens> list = Main.getMenu().getCardapio().getItens();
		obsCardapio = FXCollections.observableArrayList(list);
		tableViewCardapio.setItems(obsCardapio);
		initVerButtonsCardapio();
	}

	private void updateTableViewCardapio() {
		List<Itens> list = Main.getMenu().getCardapio().getItens();
		obsCardapio = FXCollections.observableArrayList(list);
		tableViewCardapio.setItems(obsCardapio);
		initVerButtonsCardapio();
	}

	private void createListaDeProdutosCardapio(Itens obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			VerCardapioProdutosController controller = loader.getController();
			controller.initializeColumns(obj.getComponentesPrato());

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Componentes do prato");
			dialogStage.setScene(new Scene(pane));
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void initVerButtonsCardapio() {
		tableColumnProdutosCardapio.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnProdutosCardapio.setCellFactory(param -> new TableCell<Itens, Itens>() {
			private final Button button = new Button("Ver");

			@Override
			protected void updateItem(Itens obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createListaDeProdutosCardapio(obj, "/view/table/VerCardapioProdutos.fxml",
						Utils.currentStage(event)));
			}
		});
	}

	// Produtos
	@FXML
	private Button btnRefreshTableProdutos;

	@FXML
	private TableColumn<Produtos, String> tableColumnCodProduto;
	@FXML
	private TableColumn<Produtos, String> tableColumnNomeProduto;
	@FXML
	private TableColumn<Produtos, String> tableColumnPrecoProduto;
	@FXML
	private TableColumn<Produtos, String> tableColumnQuantidadeProduto;
	@FXML
	private TableColumn<Produtos, String> tableColumnValidadeProduto;
	@FXML
	private TableColumn<Produtos, Fornecedores> tableColumnFornecedorProduto;
	
	@FXML
	private TableView<Produtos> tableViewProduto;

	public void updateTableViewProdutos() {
		List<Produtos> list = new ArrayList<Produtos>();
		for (LinkedList<Produtos> aux : Main.getMenu().getGerenciamentoProdutos().getProdutos()) {
			for (Produtos aux2 : aux) {
				list.add(aux2);
			}
		}
		obsProdutos = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsProdutos);
	}
	
	@FXML
	void onBtnRefreshTableProdutosAction(ActionEvent event) {
		updateTableViewProdutos();
	}

	public void initializeTableViewProdutos() {
		tableColumnCodProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("cod"));
		tableColumnNomeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
		tableColumnPrecoProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("preco"));
		tableColumnValidadeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("validade"));
		tableColumnQuantidadeProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("quantidade"));

		tableColumnFornecedorProduto
				.setCellValueFactory(new PropertyValueFactory<Produtos, Fornecedores>("fornecedor"));
		tableColumnFornecedorProduto.setCellFactory(param -> new TableCell<Produtos, Fornecedores>() {
			@Override
			protected void updateItem(Fornecedores obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setText(null);
					return;
				}
				setText(obj.getNome());
			}
		});

		List<Produtos> list = new ArrayList<Produtos>();

		for (LinkedList<Produtos> aux : Main.getMenu().getGerenciamentoProdutos().getProdutos()) {
			for (Produtos aux2 : aux) {
				list.add(aux2);
			}
		}

		obsProdutos = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsProdutos);
	}

	// Fornecedores

	@FXML
	private TableColumn<Fornecedores, String> tableColumnCodFornecedor;
	@FXML
	private TableColumn<Fornecedores, String> tableColumnEnderecoFornecedor;
	@FXML
	private TableColumn<Fornecedores, String> tableColumnNomeFornecedor;
	@FXML
	private TableColumn<Fornecedores, Fornecedores> tableColumnProdutosFornecedor;
	@FXML
	private TableColumn<Fornecedores, String> tableColumnCnpjFornecedor;
	@FXML
	private TableView<Fornecedores> tableViewFornecedor;

	@FXML
	private Button btnRefreshTableFornecedor;

	@FXML
	void onBtnRefreshTableFornecedorAction(ActionEvent event) {
		updateTableViewFornecedor();
	}

	public void initializeTableViewFornecedores() {
		tableColumnCodFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cod"));
		tableColumnNomeFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("nome"));
		tableColumnCnpjFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cnpj"));
		tableColumnEnderecoFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("endereco"));
		List<Fornecedores> list = Main.getMenu().getGerenciamentoFornecedores().getFornecedores();
		obsFornecedores = FXCollections.observableArrayList(list);
		tableViewFornecedor.setItems(obsFornecedores);
		initVerButtonsFornecedor();
	}

	private void updateTableViewFornecedor() {
		List<Fornecedores> list = Main.getMenu().getGerenciamentoFornecedores().getFornecedores();
		obsFornecedores = FXCollections.observableArrayList(list);
		tableViewFornecedor.setItems(obsFornecedores);
		initVerButtonsFornecedor();
	}

	private void createListaDeProdutosFornecedor(Fornecedores obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			VerProdutosFornecedorViewController controller = loader.getController();
			controller.initializeColumns(obj.getProdutos());

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Produtos do fornecedor");
			dialogStage.setScene(new Scene(pane));
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void initVerButtonsFornecedor() {
		tableColumnProdutosFornecedor.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnProdutosFornecedor.setCellFactory(param -> new TableCell<Fornecedores, Fornecedores>() {
			private final Button button = new Button("Ver");

			@Override
			protected void updateItem(Fornecedores obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createListaDeProdutosFornecedor(obj,
						"/view/table/VerProdutosFornecedorView.fxml", Utils.currentStage(event)));
			}
		});
	}

	// Vendas
	@FXML
	private Button btnAddVenda;
	@FXML
	private Button btnRefreshTableVenda;

	@FXML
	private TableColumn<Vendas, String> tableColumnCodVenda;
	@FXML
	private TableColumn<Vendas, String> tableColumnPagamentoVenda;
	@FXML
	private TableColumn<Vendas, String> tableColumnPrecoVenda;
	@FXML
	private TableColumn<Vendas, String> tableColumnRegistroVenda;
	@FXML
	private TableView<Vendas> tableViewVenda;
	
	@FXML
	private TableColumn<Vendas, Vendas> tableColumnNotaFiscal;

	@FXML
	void onBtnAddVendaAction(ActionEvent event) {
		loadView("/view/crud/adicionar/AddVendaView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRefreshTableVendaAction(ActionEvent event) {
		updateTableViewVenda();
	}

	public void initializeTableViewVendas() {
		tableColumnCodVenda.setCellValueFactory(new PropertyValueFactory<Vendas, String>("cod"));
		tableColumnRegistroVenda.setCellValueFactory(new PropertyValueFactory<Vendas, String>("registro"));
		tableColumnPrecoVenda.setCellValueFactory(new PropertyValueFactory<Vendas, String>("precoTotal"));
		tableColumnPagamentoVenda.setCellValueFactory(new PropertyValueFactory<Vendas, String>("modPag"));
		List<Vendas> list = Main.getMenu().getGerenciamentoVendas().getVendas();
		obsVendas = FXCollections.observableArrayList(list);
		tableViewVenda.setItems(obsVendas);
		initNotaFiscalVendaButtons();
	}

	public void updateTableViewVenda() {
		LinkedList<Vendas> list = Main.getMenu().getGerenciamentoVendas().getVendas();
		obsVendas = FXCollections.observableArrayList(list);
		tableViewVenda.setItems(obsVendas);
		initNotaFiscalVendaButtons();
	}
	
	private void initNotaFiscalVendaButtons() {
		tableColumnNotaFiscal.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnNotaFiscal.setCellFactory(param -> new TableCell<Vendas, Vendas>() {
			private final Button button = new Button("Nota Fiscal");

			@Override
			protected void updateItem(Vendas obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> Main.getMenu().geraNotaFiscal(obj));
			}
		});
	}

	// ----------------------------------------------------------------------------------------

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

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	private void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			Parent parent = loader.load();

			Stage stage = new Stage();
			
			stage.setTitle(" ");
			stage.setScene(new Scene(parent));
			stage.show();
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private Button btnLogout;

	@FXML
	void onBtnLogoutAction(ActionEvent e) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Ir para a tela de login?");

		if (result.get() == ButtonType.OK) {
			Main.mudaTela("preLogin");
		}
	}
	
	//RELATORIO ---------------------------------------------------------------------------
	
	@FXML
    private MenuButton mbRelatorio;

    @FXML
    private MenuItem menuItemFGeral;

    @FXML
    private MenuItem menuItemFPorProduto;

    @FXML
    private MenuItem menuItemProdAVencer;

    @FXML
    private MenuItem menuItemQtdEstoqueGeral;

    @FXML
    private MenuItem menuItemQtdEstoqueProd;

    @FXML
    private MenuItem menuItemVendasGeral;

    @FXML
    private MenuItem menuItemVendasPeriodo;

    @FXML
    private MenuItem menuItemVendasPrato;
    

    @FXML
    void onMenuItemFGeralAction(ActionEvent event) {
    	Main.getMenu().geraRelatorioFornecedoresGeral();
    }

    @FXML
    void onMenuItemFPorProdutoAction(ActionEvent event) {
    	Main.getMenu().geraRelatorioFornecedoresProduto();;
    }

    @FXML
    void onMenuItemProdAVencerAction(ActionEvent event) {
    	Main.getMenu().geraRelatorioProdutoAVencer();
    }

    @FXML
    void onMenuItemQtdEstoqueGeralAction(ActionEvent event) {
    	Main.getMenu().geraRelatorioQtdTotalEstoque();
    }

    @FXML
    void onMenuItemQtdEstoqueProdAction(ActionEvent event) {
    	Main.getMenu().geraRelatorioQtdPorProduto();
    }

    @FXML
    void onMenuItemVendasGeralAction(ActionEvent event) {
    	Main.getMenu().geraRelatorioVendasGeral();
    }

    @FXML
    void onMenuItemVendasPratoAction(ActionEvent event) {
    	loadView("/view/relatorio/RelatorioVendaPratoView.fxml");
    }
    @FXML
    void OnMenuItemVendasPeriodoAction(ActionEvent event) {
    	loadView("/view/relatorio/RelatorioVendaTempoView.fxml");
    }

}
