package model.entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Classe para objetos do tipo Produtos
 * 
 * @author Lucas de Paiva
 *
 */
public class Produtos implements Cloneable {

	private String cod;
	private String nome;
	private Double preco;
	private String validade;
	private Double quantidade;
	
	private Fornecedores fornecedor;
	
	/**
	 * Atributo da data para compara��o
	 */
	private Date validadeDate;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Produtos(String cod, String nome, Double preco, String validade, Double quantidade, Fornecedores fornecedor) throws ParseException {
		this.cod = cod;
		this.nome = nome;
		this.preco = preco;
		this.validade = validade;
		this.quantidade = quantidade;
		validadeDate = sdf.parse(validade);
		this.fornecedor = fornecedor;
	}
	
	public Produtos(String nome, Double quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	/**
	 * M�todo que mostra os atributos do produto
	 */
	public void mostraProduto() {
		System.out.println(cod);
		System.out.println(nome);
		System.out.println(preco);
		System.out.println(validade);
		System.out.println(quantidade);
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
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	public Date getValidadeDate() {
		return validadeDate;
	}

	public void setValidadeDate(Date validadeDate) {
		this.validadeDate = validadeDate;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedores getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	/**
	 * Metodo que retona um clone do objeto
	 */
	@Override
	public Produtos clone() throws CloneNotSupportedException{
		return (Produtos) super.clone();	
	}
	
	
}
