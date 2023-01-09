package model.gerenciamento;

import java.util.LinkedList;

import model.entidades.Fornecedores;
import model.entidades.Produtos;

/**
 * Classe para gerenciar os produtos do estabelecimento
 * 
 * @author lucas
 *
 */
public class GerProdutos {

	/**
	 * Lista de produtos do estabelecimento
	 */
	private LinkedList<LinkedList<Produtos>> produtos;

	/**
	 * Contador crescente para gerar c�digo �nico
	 */
	private int countCod;

	public GerProdutos() {
		produtos = new LinkedList<LinkedList<Produtos>>();
		countCod = 0;
	}

	/**
	 * Metodo definitivo de adicionar produto ao estabelecimento. Recebe um produto
	 * do fornecedor e a quantidade a ser comprada.
	 * 
	 * @param prod
	 * @param quantidade
	 * @return
	 */
	public boolean add(Produtos prod, Double quantidade) {
		if (prod.getQuantidade() > 0) {
			if (prod.getQuantidade() - quantidade >= 0) {
				try {
					Produtos novo = prod.clone();
					novo.setQuantidade(quantidade);

					prod.setQuantidade(prod.getQuantidade() - quantidade);

					int aux = verificaIndexNaListaDeProdutos(novo.getNome());
					int indexAInserir = verificaIndexAInserir(produtos.get(aux), novo);

					novo.setCod(geraCod());
					produtos.get(aux).add(indexAInserir, novo);
					return true;
				} catch (Exception e) {
					return false;
				}
			} else {
				// Alerts.showAlertComum("Erro", "Quantidade maior que a disponivel",
				// AlertType.WARNING);
				return false;
			}
		} else if (prod.getQuantidade() == 0) {
			// Alerts.showAlertComum("Erro", "Quantidade do produto zerada",
			// AlertType.WARNING);
			return false;
		}
		return false;

	}

	/**
	 * Metodo de adicionar auxiliar, para ser usado na instanciacao do menu
	 * 
	 * @param list
	 * @param codForn
	 * @param codProd
	 * @return
	 */
	public boolean add(LinkedList<Fornecedores> list, String codForn, String codProd) {
		try {
			Fornecedores buscaForn = this.buscaFornecedor(codForn, list);
			if (buscaForn != null) {
				Produtos prod = this.buscaProdutoNoFornecedor(buscaForn, codProd);
				if (prod != null) {

					Produtos novo = prod.clone();

					int aux = verificaIndexNaListaDeProdutos(novo.getNome());
					int indexAInserir = verificaIndexAInserir(produtos.get(aux), novo);

					novo.setCod(geraCod());
					novo.setFornecedor(buscaForn);
					produtos.get(aux).add(indexAInserir, novo);
					return true;

				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * M�todo para remover um produto da lista do estabelecimento
	 * 
	 * @param cod C�digo �nico do produto do estabelecimento
	 * @return retorna o produto removido
	 */
	public Produtos remove(String cod) {
		if (cod.isBlank()) {
			// Alerts.showAlertComum("Erro", "Codigo vazio", AlertType.WARNING);
			return null;
		}
		try {
			int index1 = indexProdutoDoRestaurante1(cod);
			if (index1 > -1) {
				int index2 = indexProdutoDoRestaurante2(cod, index1);
				return produtos.get(index1).remove(index2);
			}
			// Alerts.showAlertComum("Erro", "Produto nao encontrado", AlertType.WARNING);
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Recebe um produto existente como parametro e faz um Set nos atributos novos
	 * 
	 * @param p
	 * @param preco
	 * @param quantidade
	 * @return
	 */
	public Produtos edit(Produtos p, Double preco, Double quantidade) {
		if (verificaAtributosProdEdit(preco, quantidade)) {
			try {
				p.setPreco(preco);
				p.setQuantidade(quantidade);
				return p;
			} catch (NullPointerException e) {
				return null;
			}
		} else {
			// Alerts.showAlertComum("Erro", "Um ou mais campos vazios", AlertType.WARNING);
			return null;
		}
	}

	/**
	 * Mostra todos os produtos da lista
	 */
	public void show() {
		for (LinkedList<Produtos> listProd : produtos) {
			System.out.println("-----------------------");
			for (Produtos prod : listProd) {
				prod.mostraProduto();
			}
		}

	}

	/**
	 * M�todo para buscar um produto de um fornecedor e retorn�-lo
	 * 
	 * @param forn Fornecedor a ter a lista de produtos percorrida
	 * @param cod  C�digo do produto a ser buscado
	 * @return Retorna o produto (caso exista)
	 */
	public Produtos buscaProdutoNoFornecedor(Fornecedores forn, String cod) {
		try {
			if (!forn.getProdutos().isEmpty()) {
				for (Produtos prod : forn.getProdutos()) {
					if (prod.getCod().contains(cod)) {
						return prod;
					}
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * M�todo para buscar um fornecedor a partir de uma lista de fornecedores e seu
	 * c�digo
	 * 
	 * @param cod  C�digo do fornecedor a ser buscado
	 * @param list Lista da classe GerFornecedores
	 * @return Retorna o fornecedor (caso exista)
	 */
	public Fornecedores buscaFornecedor(String cod, LinkedList<Fornecedores> list) {
		try {
			if (!list.isEmpty()) {
				for (Fornecedores forn : list) {
					if (forn.getCod().contains(cod)) {
						return forn;
					}
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Verifica se j� existe o produto na lista de produtos geral de acordo com o
	 * nome, e caso exista, retorna o �ndice. Caso n�o exista, retorna -1.
	 * 
	 * @param nomeProduto Nome do produto a ser buscado
	 * @return
	 */
	public int verificaIndexNaListaDeProdutos(String nomeProduto) {
		int index = 0;

		if (produtos.isEmpty()) {
			produtos.add(new LinkedList<Produtos>());
			return 0;
		} else {
			for (LinkedList<Produtos> prod : produtos) {
				if (prod.get(0).getNome() == nomeProduto) {
					return index;
				}
				index++;
			}
			produtos.add(new LinkedList<Produtos>());
			return index;
		}

	}

	/**
	 * Verifica a posi��o em que o item novo ser� inserido de acordo com a validade
	 * (Produtos mais pr�ximos do vencimento t�m prioridade)
	 * 
	 * @param p           Lista de produtos espec�fica da lista de produtos geral
	 * @param produtoNovo Produto novo a ser adicionado
	 * @return
	 */
	public int verificaIndexAInserir(LinkedList<Produtos> p, Produtos produtoNovo) {

		int index = 0;

		for (Produtos prod : p) {
			if (produtoNovo.getValidadeDate().compareTo(prod.getValidadeDate()) == 0
					|| produtoNovo.getValidadeDate().compareTo(prod.getValidadeDate()) < 0) {
				return index;
			} else
				index++;
		}
		return index;
	}

	/**
	 * Busca o produto em todas as listas de produtos da lista principal
	 * 
	 * @param cod
	 * @return Retorna o produto encontrado, ou null se n�o encontrar
	 */
	public int indexProdutoDoRestaurante1(String cod) {
		try {
			int index = 0;
			for (LinkedList<Produtos> listP : produtos) {
				for (Produtos prod : listP) {
					if (prod.getCod().equals(cod)) {
						return index;
					}
				}
				index++;
			}
			return -1;
		} catch (Exception e) {
			return -1;
		}

	}

	public int indexProdutoDoRestaurante2(String cod, int indexAux) {
		try {
			int index = 0;
			for (Produtos prod : produtos.get(indexAux)) {
				if (prod.getCod().equals(cod)) {
					return index;
				}
				index++;
			}

			return -1;
		} catch (Exception e) {
			return -1;
		}

	}

	/**
	 * M�todo para gerar um c�digo automaticamente
	 * 
	 * @return retorna o c�digo do produto novo
	 */
	public String geraCod() {
		String s = "PROD" + countCod;
		countCod++;
		return s;
	}

	/**
	 * M�todo que verifica se os atributos s�o v�lidos
	 * 
	 * @param nome
	 * @param preco
	 * @param validade
	 * @return
	 */
	public boolean verificaAtributosProdEdit(Double preco, Double quantidade) {
		if (quantidade != null && preco != null) {
			return true;
		}
		return false;
	}

	/**
	 * M�todo que verifica se j� existe uma lista daquele produto pelo nome
	 */

	public LinkedList<LinkedList<Produtos>> getProdutos() {
		return produtos;
	}

	public void setProdutos(LinkedList<LinkedList<Produtos>> produtos) {
		this.produtos = produtos;
	}

}
