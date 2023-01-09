package model.terminal;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedList;

import javafx.scene.control.Alert.AlertType;
import model.entidades.Clientes;
import model.entidades.Fornecedores;
import model.entidades.Funcionario;
import model.entidades.Gerente;
import model.entidades.Itens;
import model.entidades.Produtos;
import model.entidades.Vendas;
import model.gerenciamento.Cardapio;
import model.gerenciamento.GerClientes;
import model.gerenciamento.GerEstoque;
import model.gerenciamento.GerFornecedores;
import model.gerenciamento.GerFuncionarios;
import model.gerenciamento.GerGerentes;
import model.gerenciamento.GerProdutos;
import model.gerenciamento.GerVendas;
import model.gerenciamento.GeraRelatorio;
import utils.Alerts;

public class MenuFacade {

	private GerClientes clientes;

	private Cardapio card;
	private GerGerentes ger;
	private GerFuncionarios func;
	private GerFornecedores forn;
	private GerProdutos prod;
	private GerVendas vendas;
	private GeraRelatorio relatorio;
	private GerEstoque estoque;

	private LinkedList<Itens> listIPedido;
	private LinkedList<Produtos> pAux1, pAux2;
	private LinkedList<Produtos> pItens;

	private Produtos p0, p1, p2;
	private Produtos p3, p4, p5;

	private Produtos aux0, aux1;

	public MenuFacade() throws ParseException {
		clientes = new GerClientes();
		clientes.add("Cliente 0", "0000", "zero.email.com", "0000");
		clientes.add("Cliente 1", "1111", "um.email.com", "1111");
		clientes.add("Cliente 2", "2222", "dois.email.com", "2222");

		ger = new GerGerentes();
		func = new GerFuncionarios();
		card = new Cardapio();
		forn = new GerFornecedores();
		prod = new GerProdutos();
		vendas = new GerVendas();
		relatorio = new GeraRelatorio();
		estoque = new GerEstoque();

		forn.add("Frios distribuidora", "12345", "Rua A", new LinkedList<Produtos>());
		forn.add("Carnes distribuidora", "54321", "Rua B", new LinkedList<Produtos>());

		p0 = new Produtos("PROD0", "Queijo", 15.0, "22/11/2023", 5.0, forn.getFornecedores().get(0));
		p1 = new Produtos("PROD1", "Queijo", 20.0, "22/11/2022", 7.0, forn.getFornecedores().get(0));
		p2 = new Produtos("PROD2", "Queijo", 10.0, "22/11/2021", 10.0, forn.getFornecedores().get(0));
		p3 = new Produtos("PROD3", "Calabresa", 20.0, "23/11/2022", 4.0, forn.getFornecedores().get(1));
		p4 = new Produtos("PROD4", "Calabresa", 30.0, "22/11/2022", 6.0, forn.getFornecedores().get(1));
		p5 = new Produtos("PROD5", "Calabresa", 40.0, "21/11/2022", 9.0, forn.getFornecedores().get(1));

		pAux1 = new LinkedList<Produtos>();
		pAux2 = new LinkedList<Produtos>();

		pAux1.add(p0);
		pAux1.add(p1);
		pAux1.add(p2);
		pAux2.add(p3);
		pAux2.add(p4);
		pAux2.add(p5);

		forn.getFornecedores().get(0).setProdutos(pAux1);
		forn.getFornecedores().get(1).setProdutos(pAux2);

		// Parte do cardapio

		listIPedido = new LinkedList<Itens>();
		pItens = new LinkedList<Produtos>();

		LinkedList<Produtos> pItens2 = new LinkedList<Produtos>();

		aux0 = new Produtos("Queijo", 10.0);
		aux1 = new Produtos("Calabresa", 10.0);

		Produtos aux2 = new Produtos("Queijo", 5.0);
		Produtos aux3 = new Produtos("Calabresa", 5.0);

		pItens.add(aux0);
		pItens.add(aux1);

		pItens2.add(aux2);
		pItens2.add(aux3);

		card.addItens("Pizza de Calabresa Grande", "Calabresa, Mussarela", 45.0, "Massas", pItens);
		card.addItens("Pizza de Calabresa Media", "Calabresa, Mussarela", 30.0, "Massas", pItens2);

		listIPedido.add(card.getItens().get(0));

		// Parte do estoque

		prod.add(forn.getFornecedores(), "FORN0", "PROD0");
		prod.add(forn.getFornecedores(), "FORN0", "PROD1");
		prod.add(forn.getFornecedores(), "FORN0", "PROD2");

		prod.add(forn.getFornecedores(), "FORN1", "PROD3");
		prod.add(forn.getFornecedores(), "FORN1", "PROD4");
		prod.add(forn.getFornecedores(), "FORN1", "PROD5");

		func.add("LUCAS", "123");
	}

	// ---------------------------------------- Gerenciamento de Clientes
	// ----------------------------------------

	public boolean addCliente(String nome, String cpf, String email, String telefone) {
		if (clientes.verificaAtributosCliente(nome, cpf, email, telefone)) {
			return clientes.add(nome, cpf, email, telefone);
		} else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return false;
		}

	}

	public Clientes remCliente(String cod) {
		if (cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		return clientes.remove(cod);
	}

	public Clientes editCliente(Clientes c, String nome, String cpf, String email, String telefone) {
		if (c != null) {
			return clientes.edit(c, nome, cpf, email, telefone);
		}
		return null;
	}

	// ---------------------------------------- Gerenciamento de gerentes
	// ----------------------------------------

	public boolean addGerente(String nome, String senha) {
		if (ger.verificaAtributosGerente(nome, senha)) {
			return ger.add(nome, senha);
		} else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return false;
		}
	}

	public Gerente remGerente(String cod) {
		if (cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		return ger.remove(cod);
	}

	public Gerente editGerente(Gerente g, String nome, String senha) {
		if (g != null) {
			return ger.edit(g, nome, senha);
		}
		return null;
	}

	public void showGerente() {
		ger.show();
	}

	// ---------------------------------------- Gerenciamento de funcionários
	// ----------------------------------------

	public boolean addFuncionario(String nome, String senha) {
		if (func.verificaAtributosFuncionario(nome, senha)) {
			return func.add(nome, senha);
		} else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return false;
		}

	}

	public Funcionario remFuncionario(String cod) {
		if (cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		return func.remove(cod);
	}

	public Funcionario editFuncionario(Funcionario f, String nome, String senha) {
		if (f != null) {
			return func.edit(f, nome, senha);
		}
		return null;
	}

	public void showFuncionario() {
		func.show();
	}

	// ---------------------------------------- Gerenciamento de cardápio
	// ----------------------------------------

	public boolean addPratoCardapio(String nome, String descricao, Double preco, String categoria,
			LinkedList<Produtos> componentesPrato) {
		if (card.verificaAtributosItem(nome, descricao, preco, categoria)) {
			return card.addItens(nome, descricao, preco, categoria, componentesPrato);
		} else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return false;
		}
	}

	public Itens remPratoCardapio(String cod) {
		if (cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		return card.removeItens(cod);
	}

	public Itens editPratoCardapio(Itens i, String nome, String descricao, Double preco, String categoria) {
		if (i != null) {
			return card.edit(i, nome, descricao, preco, categoria);
		}
		return null;
	}

	public void showPratoCardapio() {
		card.show();
	}

	// ---------------------------------------- Gerenciamento de fornecedores
	// ----------------------------------------

	public boolean addFornecedor(String nome, String cnpj, String endereco, LinkedList<Produtos> p) {
		if (forn.verificaAtributosForn(nome, cnpj, endereco)) {
			return forn.add(nome, cnpj, endereco, p);
		} else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return false;
		}
	}

	public Fornecedores remFornecedor(String cod) {
		if (cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		return forn.remove(cod);
	}

	public Fornecedores editFornecedor(Fornecedores f, String nome, String cnpj, String endereco) {
		if (f != null) {
			return forn.edit(f, nome, cnpj, endereco);
		}
		return null;
	}

	public void showFornecedor() {
		forn.show();
	}

	// ---------------------------------------- Gerenciamento de produtos
	// ----------------------------------------

	public boolean addProduto(Produtos p, Double quantidade) {
		if (prod.verificaAtributosProdEdit(quantidade, quantidade)) {
			return prod.add(p, quantidade);
		} else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return false;
		}
	}

	public Produtos remProduto(String cod) {
		if (cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		return prod.remove(cod);
	}

	public Produtos editProduto(Produtos p, Double preco, Double quantidade) {
		if (p != null) {
			return prod.edit(p, preco, quantidade);
		}
		return null;
	}

	public void showProduto() {
		prod.show();
	}

	// ---------------------------------------- Gerenciamento de vendas
	// ----------------------------------------

	public boolean addVenda(String modPag, LinkedList<Itens> itens) {
		if (vendas.verificaAtributosVenda(modPag)) {
			return vendas.addVenda(modPag, itens, prod.getProdutos(), estoque, prod);
		} else {
			Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return false;
		}
	}

	public Vendas remVenda(String cod) {
		if (cod.isBlank()) {
			Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		return vendas.remove(cod);
	}

	public Vendas editVenda(Vendas v, Double precoTotal, String modPag) {
		if (v != null) {
			return vendas.edit(v, precoTotal, modPag);
		}
		return null;
	}

	// ---------------------------------------- Gerenciamento de relatorio
	// ----------------------------------------

	public boolean geraRelatorioVendasGeral() {
		if (vendas.getVendas().isEmpty()) {
			Alerts.showAlertComum("Erro", "Nao ha vendas no sistema", AlertType.WARNING);
			return false;
		} else {
			relatorio.geraRelatorioVendasGeral(vendas.getVendas());
			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
			return true;
		}

	}

	public boolean geraRelatorioVendasPeriodo(LocalDate date1, LocalDate date2) {
		if (vendas.getVendas().isEmpty()) {
			Alerts.showAlertComum("Erro", "Nao ha vendas no sistema", AlertType.WARNING);
			return false;
		} else if (relatorio.geraRelatorioVendasPeriodo(vendas.getVendas(), date1, date2)) {
			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
			return true;
		}
		return false;
	}

	public boolean geraRelatorioVendasPrato(String tipoPrato) {
		if (vendas.getVendas().isEmpty()) {
			Alerts.showAlertComum("Erro", "Nao ha vendas no sistema", AlertType.WARNING);
			return false;
		} else if (relatorio.geraRelatorioVendasPrato(vendas.getVendas(), tipoPrato)) {
			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
			return true;
		}
		return false;
	}

	public boolean geraRelatorioQtdTotalEstoque() {
		if (prod.getProdutos().isEmpty()) {
			Alerts.showAlertComum("Erro", "Nao ha produtos no sistema", AlertType.WARNING);
			return false;
		} else {
			relatorio.geraRelatorioQtdTotalEstoque(prod.getProdutos());
			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
			return true;
		}
	}

	public boolean geraRelatorioQtdPorProduto() {
		if (prod.getProdutos().isEmpty()) {
			Alerts.showAlertComum("Erro", "Nao ha produtos no sistema", AlertType.WARNING);
			return false;
		} else if (relatorio.geraRelatorioQtdPorProduto(prod.getProdutos())) {
			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
			return true;
		}
		return false;
	}

	public boolean geraRelatorioProdutoAVencer() {
		if (prod.getProdutos().isEmpty()) {
			Alerts.showAlertComum("Erro", "Nao ha produtos no sistema", AlertType.WARNING);
			return false;
		} else if (relatorio.geraRelatorioProdutoAVencer(prod.getProdutos())) {
			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
			return true;
		}
		return false;
	}

	public boolean geraRelatorioFornecedoresProduto() {
		if (prod.getProdutos().isEmpty()) {
			Alerts.showAlertComum("Erro", "Nao ha produtos no sistema", AlertType.WARNING);
			return false;
		} else {
			relatorio.geraRelatorioFornecedoresProduto(prod.getProdutos());
			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
			return true;
		}
	}

	public boolean geraRelatorioFornecedoresGeral() {
		if (forn.getFornecedores().isEmpty()) {
			Alerts.showAlertComum("Erro", "Nao ha fornecedores no sistema", AlertType.WARNING);
			return false;
		} else {
			relatorio.geraRelatorioFornecedoresGeral(forn.getFornecedores());
			Alerts.showAlertComum("Confirmacao", "Relatorio gerado", AlertType.CONFIRMATION);
			return true;
		}
	}

	public boolean geraNotaFiscal(Vendas v) {
		if (v != null) {
			if (relatorio.geraNotaFiscal(v)) {
				Alerts.showAlertComum("Confirmacao", "Nota fiscal gerada", AlertType.CONFIRMATION);
				return true;
			}
			Alerts.showAlertComum("Erro", "Nota fiscal nao foi gerada", AlertType.WARNING);
			return false;
		}
		Alerts.showAlertComum("Erro", "Nota fiscal nao foi gerada", AlertType.WARNING);
		return false;
	}

	// GETS

	public Cardapio getCardapio() {
		return card;
	}

	public GerGerentes getGerenciamentoGerentes() {
		return ger;
	}

	public GerFuncionarios getGerenciamentoFuncionarios() {
		return func;
	}

	public GerFornecedores getGerenciamentoFornecedores() {
		return forn;
	}

	public GerProdutos getGerenciamentoProdutos() {
		return prod;
	}

	public GerVendas getGerenciamentoVendas() {
		return vendas;
	}

	public GeraRelatorio getGerenciamentoRelatorios() {
		return relatorio;
	}

	public GerClientes getGerenciamentoClientes() {
		return clientes;
	}

	public GerEstoque Estoque() {
		return estoque;
	}
}
