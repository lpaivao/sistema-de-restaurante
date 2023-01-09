package model.entidades;

import java.util.LinkedList;

/** Classe para objetos do tipo Fornecedores
 * 
 * @author Lucas de Paiva
 *
 */

public class Fornecedores {

	private String cod;
	private String cnpj;
	private String nome;
	private String endereco;
	private LinkedList<Produtos> produtos;
	
	public Fornecedores(String cod, String nome,String cnpj, String endereco) {
		this.cod = cod;
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
		produtos = new LinkedList<Produtos>();
	}
	
	public Fornecedores(String cod, String nome, String cnpj, String endereco, LinkedList<Produtos> produtos) {
		this.cod = cod;
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
		this.produtos = produtos;
	}
	
	public Fornecedores() {
		
	}
	
	/**
	 * M�todo que mostra os atributos do fornecedor
	 */
	public void mostraFornecedor() {
		System.out.println(cod);
		System.out.println(nome);
		System.out.println(cnpj);
		System.out.println(endereco);
	}
	
	/**
	 * M�todo que lista os itens do fornecedor
	 */
	public void mostraListaProdutos() {
		for(Produtos pro : produtos) {
			pro.mostraProduto();
		}
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LinkedList<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(LinkedList<Produtos> produtos) {
		this.produtos = produtos;
	}

	
}
