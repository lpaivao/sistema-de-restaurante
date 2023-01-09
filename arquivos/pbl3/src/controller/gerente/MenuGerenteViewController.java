package controller.gerente;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import controller.crud.editar.EditClienteViewController;
import controller.crud.editar.EditFornecedorViewController;
import controller.crud.editar.EditFuncionarioViewController;
import controller.crud.editar.EditGerenteViewController;
import controller.crud.editar.EditPratoViewController;
import controller.crud.editar.EditProdutoViewController;
import controller.crud.editar.EditVendaViewController;
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

/**
 * Controller do menu de funcionario
 * @author Lucas de Paiva
 *
 */
public class MenuGerenteViewController implements Initializable {

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
	private TableColumn<Clientes, Clientes> tableColumnEditCliente;

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
		initEditClienteButtons();
	}

	private void updateTableViewCliente() {
		List<Clientes> list = Main.getMenu().getGerenciamentoClientes().getClientes();
		obsClientes = FXCollections.observableArrayList(list);
		tableViewCliente.setItems(obsClientes);
		initEditClienteButtons();
	}

	/**
	 * Metodo para criar a tela de edicao do cliente
	 * 
	 * @param obj
	 * @param absoluteName
	 * @param parentStage
	 */
	private void createEditCliente(Clientes obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			EditClienteViewController controller = loader.getController();
			controller.preencheCampos(obj);

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo que define as propriedades da coluna de editar cliente e cria um botao
	 * de edicao
	 */
	private void initEditClienteButtons() {
		tableColumnEditCliente.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditCliente.setCellFactory(param -> new TableCell<Clientes, Clientes>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Clientes obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createEditCliente(obj, "/view/crud/editar/EditClienteView.fxml",
						Utils.currentStage(event)));
			}
		});
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
	private TableColumn<Gerente, Gerente> tableColumnEditGerente;
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
		initEditGerenteButtons();
	}

	/**
	 * Metodo para criar a tela de edicao do gerente
	 * 
	 * @param obj
	 * @param absoluteName
	 * @param parentStage
	 */
	private void createEditGerente(Gerente obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			EditGerenteViewController controller = loader.getController();
			controller.preencheCampos(obj);

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo que define as propriedades da coluna de editar gerente e cria um botao
	 * de edicao
	 */
	private void initEditGerenteButtons() {
		tableColumnEditGerente.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditGerente.setCellFactory(param -> new TableCell<Gerente, Gerente>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Gerente obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createEditGerente(obj, "/view/crud/editar/EditGerenteView.fxml",
						Utils.currentStage(event)));
			}
		});
	}

	public void initializeTableViewGerente() {
		tableColumnCodGerente.setCellValueFactory(new PropertyValueFactory<Gerente, String>("cod"));
		tableColumnNomeGerente.setCellValueFactory(new PropertyValueFactory<Gerente, String>("nome"));
		tableColumnSenhaGerente.setCellValueFactory(new PropertyValueFactory<Gerente, String>("senha"));
		List<Gerente> list = Main.getMenu().getGerenciamentoGerentes().getGerentes();
		obsGerentes = FXCollections.observableArrayList(list);
		tableViewGerente.setItems(obsGerentes);
		initEditGerenteButtons();
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
	private TableColumn<Funcionario, Funcionario> tableColumnEditFuncionario;
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
		initEditFuncionarioButtons();
	}

	/**
	 * Metodo para criar a tela de edicao do funcionario
	 * 
	 * @param obj
	 * @param absoluteName
	 * @param parentStage
	 */
	private void createEditFuncionario(Funcionario obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			EditFuncionarioViewController controller = loader.getController();
			controller.preencheCampos(obj);

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo que define as propriedades da coluna de editar cliente e cria um botao
	 * de edicao
	 */
	private void initEditFuncionarioButtons() {
		tableColumnEditFuncionario.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditFuncionario.setCellFactory(param -> new TableCell<Funcionario, Funcionario>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Funcionario obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createEditFuncionario(obj, "/view/crud/editar/EditFuncionarioView.fxml",
						Utils.currentStage(event)));
			}
		});
	}

	public void initializeTableViewFuncionario() {
		tableColumnCodFuncionario.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cod"));
		tableColumnNomeFuncionario.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
		tableColumnSenhaFuncionario.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("senha"));
		List<Funcionario> list = Main.getMenu().getGerenciamentoFuncionarios().getFuncionarios();
		obsFuncionarios = FXCollections.observableArrayList(list);
		tableViewFuncionario.setItems(obsFuncionarios);
		initEditFuncionarioButtons();
	}

	// Cardapio
	@FXML
	private Button btnAddPrato;
	@FXML
	private Button btnRemoverPrato;

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
	private TableColumn<Itens, Itens> tableColumnEditPrato;

	@FXML
	private TableView<Itens> tableViewCardapio;

	@FXML
	private TableColumn<Itens, Itens> tableColumnProdutosCardapio;

	@FXML
	void onBtnAddPratoAction(ActionEvent event) {
		loadView("/view/crud/adicionar/AddPratoView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRemoverPratoAction(ActionEvent event) {
		loadView("/view/crud/remover/RemPratoView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnEditarPratoAction(ActionEvent event) {
		loadView("/view/crud/editar/EditPratoView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnListarPratosAction(ActionEvent event) {

	}

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
		initEditPratoButtons();
	}

	private void updateTableViewCardapio() {
		List<Itens> list = Main.getMenu().getCardapio().getItens();
		obsCardapio = FXCollections.observableArrayList(list);
		tableViewCardapio.setItems(obsCardapio);
		initVerButtonsCardapio();
		initEditPratoButtons();
	}

	/**
	 * Metodo para criar a tela de visualizacao dos componentes do prato
	 * 
	 * @param obj
	 * @param absoluteName
	 * @param parentStage
	 */
	private void createListaDeProdutosCardapio(Itens obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			VerCardapioProdutosController controller = loader.getController();
			controller.initializeColumns(obj.getComponentesPrato());

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo para criar o botao de visualizacao dos componentes do prato
	 */
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

	/**
	 * Metodo para criar a tela de edicao de item do cardapio
	 * 
	 * @param obj
	 * @param absoluteName
	 * @param parentStage
	 */
	private void createEditPrato(Itens obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			EditPratoViewController controller = loader.getController();
			controller.preencheCampos(obj);

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo que define as propriedades da coluna de editar itens do cardapio e
	 * cria um botao de edicao
	 */
	private void initEditPratoButtons() {
		tableColumnEditPrato.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditPrato.setCellFactory(param -> new TableCell<Itens, Itens>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Itens obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createEditPrato(obj, "/view/crud/editar/EditPratoView.fxml",
						Utils.currentStage(event)));
			}
		});
	}

	// Produtos
	@FXML
	private Button btnAddProdutos;
	@FXML
	private Button btnRemoverProdutos;
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
	private TableColumn<Produtos, Produtos> tableColumnEditProduto;
	@FXML
	private TableView<Produtos> tableViewProduto;

	@FXML
	void onBtnAddProdutosAction(ActionEvent event) {
		loadView("/view/crud/adicionar/AddProdutoView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRemoverProdutosAction(ActionEvent event) {
		loadView("/view/crud/remover/RemProdutoView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRefreshTableProdutosAction(ActionEvent event) {
		updateTableViewProdutos();
	}

	public void updateTableViewProdutos() {
		List<Produtos> list = new ArrayList<Produtos>();
		for (LinkedList<Produtos> aux : Main.getMenu().getGerenciamentoProdutos().getProdutos()) {
			for (Produtos aux2 : aux) {
				list.add(aux2);
			}
		}
		obsProdutos = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsProdutos);
		initEditProdutoButtons();
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
		initEditProdutoButtons();
	}

	/**
	 * Metodo para criar a tela de edicao do produto
	 * 
	 * @param obj
	 * @param absoluteName
	 * @param parentStage
	 */
	private void createEditProduto(Produtos obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			EditProdutoViewController controller = loader.getController();
			controller.preencheCampos(obj);

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo que define as propriedades da coluna de editar produto e cria um botao
	 * de edicao
	 */
	private void initEditProdutoButtons() {
		tableColumnEditProduto.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditProduto.setCellFactory(param -> new TableCell<Produtos, Produtos>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Produtos obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createEditProduto(obj, "/view/crud/editar/EditProdutoView.fxml",
						Utils.currentStage(event)));
			}
		});
	}

	// Fornecedores
	@FXML
	private Button btnAddFornecedor;
	@FXML
	private Button btnRemoverFornecedor;

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
	private TableColumn<Fornecedores, Fornecedores> tableColumnEditFornecedor;

	@FXML
	void onBtnAddFornecedorAction(ActionEvent event) {
		loadView("/view/crud/adicionar/AddFornecedorView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRemoverFornecedorAction(ActionEvent event) {
		loadView("/view/crud/remover/RemFornecedorView.fxml", Utils.currentStage(event));
	}

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
		initEditFornecedorButtons();
	}

	private void updateTableViewFornecedor() {
		List<Fornecedores> list = Main.getMenu().getGerenciamentoFornecedores().getFornecedores();
		obsFornecedores = FXCollections.observableArrayList(list);
		tableViewFornecedor.setItems(obsFornecedores);
		initVerButtonsFornecedor();
		initEditFornecedorButtons();
	}

	/**
	 * Metodo para criar a tela de visualizacao dos produtos do fornecedor
	 * 
	 * @param obj
	 * @param absoluteName
	 * @param parentStage
	 */
	private void createListaDeProdutosFornecedor(Fornecedores obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			VerProdutosFornecedorViewController controller = loader.getController();
			controller.initializeColumns(obj.getProdutos());

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo para criar o botao de visualizacao dos produtos do fornecedor
	 */
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

	/**
	 * Metodo para criar a tela de edicao do fornecedor
	 * 
	 * @param obj
	 * @param absoluteName
	 * @param parentStage
	 */
	private void createEditFornecedor(Fornecedores obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			EditFornecedorViewController controller = loader.getController();
			controller.preencheCampos(obj);

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo que define as propriedades da coluna de editar fornecedor e cria um
	 * botao de edicao
	 */
	private void initEditFornecedorButtons() {
		tableColumnEditFornecedor.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditFornecedor.setCellFactory(param -> new TableCell<Fornecedores, Fornecedores>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Fornecedores obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createEditFornecedor(obj, "/view/crud/editar/EditFornecedorView.fxml",
						Utils.currentStage(event)));
			}
		});
	}

	// Vendas
	@FXML
	private Button btnAddVenda;
	@FXML
	private Button btnRemoverVenda;
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
	private TableColumn<Vendas, Vendas> tableColumnEditVenda;
	
	@FXML
	private TableColumn<Vendas, Vendas> tableColumnNotaFiscal;

	@FXML
	void onBtnAddVendaAction(ActionEvent event) {
		loadView("/view/crud/adicionar/AddVendaView.fxml", Utils.currentStage(event));
	}

	@FXML
	void onBtnRemoverVendaAction(ActionEvent event) {
		loadView("/view/crud/remover/RemVendaView.fxml", Utils.currentStage(event));
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
		initEditVendaButtons();
		initNotaFiscalVendaButtons();
	}

	public void updateTableViewVenda() {
		LinkedList<Vendas> list = Main.getMenu().getGerenciamentoVendas().getVendas();
		obsVendas = FXCollections.observableArrayList(list);
		tableViewVenda.setItems(obsVendas);
		initEditVendaButtons();
		initNotaFiscalVendaButtons();
	}

	/**
	 * Metodo que define as propriedades da coluna de gerar a nota fiscal,
	 * e coloca um bota nela
	 */
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
	
	private void createEditVenda(Vendas obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			EditVendaViewController controller = loader.getController();
			controller.preencheCampos(obj);

			Stage dialogStage = new Stage();
			dialogStage.setTitle(" ");
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

	/**
	 * Metodo que define as propriedades da coluna de editar venda e cria um botao
	 * de edicao
	 */
	private void initEditVendaButtons() {
		tableColumnEditVenda.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditVenda.setCellFactory(param -> new TableCell<Vendas, Vendas>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Vendas obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createEditVenda(obj, "/view/crud/editar/EditVendaView.fxml",
						Utils.currentStage(event)));
			}
		});
	}

	// ----------------------------------------------------------------------------------------

	/**
	 * Metodo que abre uma tela por cima da principal, e impede que qualquer outro
	 * comando seja feito ate que se feche a janela aberta
	 * 
	 * @param absoluteName
	 * @param parentStage
	 */
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

	/**
	 * Metodo que abre uma tela por cima da principal
	 * 
	 * @param absoluteName
	 */
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

	/**
	 * Acao do botao de logout que faz voltar ao inicio da tela
	 * @param e
	 */
	@FXML
	void onBtnLogoutAction(ActionEvent e) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Ir para a tela de login?");

		if (result.get() == ButtonType.OK) {
			Main.mudaTela("preLogin");
		}
	}

	// RELATORIO
	// ---------------------------------------------------------------------------

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
		Main.getMenu().geraRelatorioFornecedoresProduto();
		;
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
