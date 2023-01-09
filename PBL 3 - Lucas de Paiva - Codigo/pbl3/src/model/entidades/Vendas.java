package model.entidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

/** Classe para objetos do tipo Vendas
 * 
 * @author Lucas de Paiva
 *
 */
public class Vendas {
	
	private LocalDate dataRelatorio;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Registro do dia e hora
	 */
	private String registro;
	
	private String cod;
	private String modPag; 
	
	/**
	 * Lista de itens da venda 
	 */
	private LinkedList<Itens> itens;
	
	private Double precoTotal;

	public Vendas(String cod, String modPag, LinkedList<Itens> itens) {
		dataRelatorio = LocalDate.now();
		registro = DateFormat.getInstance().format(new Date());
		this.cod = cod;
		this.modPag = modPag;
		this.itens = itens;
		precoTotal = calculaPrecoTotal();
	}

	/**
	 * M�todo que calcula o pre�o total percorrendo a lista de itens da venda
	 * @return
	 */
	private Double calculaPrecoTotal() {
		if(!itens.isEmpty()) {
			Double precoT = 0.0;
			for(Itens item : itens) {
				precoT = precoT + item.getPreco();
			}
			return precoT;
		}
		return 0.0;
	}
	
	/**
	 * M�todo que mostra a os atributos da venda e os atributos (apenas nome e pre�o)  dos itens da lista
	 */
	public void mostraVenda() {
		System.out.println(cod);
		System.out.println(registro);
		System.out.println(modPag);
		for(Itens item : itens){
			System.out.println(item.getNome() + " " + item.getPreco());
		}	
		System.out.println("Pre�o total: " + this.calculaPrecoTotal());
	}
	
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getModPag() {
		return modPag;
	}

	public void setModPag(String modPag) {
		this.modPag = modPag;
	}

	public LinkedList<Itens> getItens() {
		return itens;
	}

	public void setItens(LinkedList<Itens> itens) {
		this.itens = itens;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public LocalDate getDataRelatorio() {
		return dataRelatorio;
	}

	public void setDataRelatorio(LocalDate dataRelatorio) {
		this.dataRelatorio = dataRelatorio;
	}
		
}
