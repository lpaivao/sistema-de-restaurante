package model.entidades;
/** Classe para objetos do tipo Gerente
 * 
 * @author Lucas de Paiva
 *
 */
public class Gerente {

	private String cod;
	private String nome;
	private String senha;
	
	public Gerente(String cod, String nome, String senha) {
		this.cod = cod;
		this.nome = nome;
		this.senha = senha;
	}
	
	public void mostraGerente() {
		System.out.println(this.getCod());
		System.out.println(this.getNome());
		System.out.println(this.getSenha());
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
}
