package model.entidades;

import java.util.LinkedList;

/** Classe para objetos do tipo Itens
 * 
 * @author Lucas de Paiva
 *
 */
public class Itens {

	private String cod;
	private String nome;
	private String descricao;
	private Double preco;
	private String categoria;
	
	/**
	 * Lista de produtos que comp�em o item e sua respectiva quantidade
	 */
	private LinkedList<Produtos> componentesPrato;
	
	public Itens(String cod, String nome, String descricao, Double preco, String categoria, LinkedList<Produtos> componentesPrato) {
		this.cod = cod;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
		this.componentesPrato = componentesPrato;
	}
	
	/**
	 * M�todo que mostra os atributos do item
	 */
	public void mostraItem() {
		System.out.println(cod);
		System.out.println(nome);
		System.out.println(categoria);
		System.out.println(descricao);
		System.out.println(preco);
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LinkedList<Produtos> getComponentesPrato() {
		return componentesPrato;
	}

	public void setComponentesPrato(LinkedList<Produtos> componentesPrato) {
		this.componentesPrato = componentesPrato;
	}
	
	
	
}
