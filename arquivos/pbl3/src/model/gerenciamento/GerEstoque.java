package model.gerenciamento;

import java.util.Hashtable;
import java.util.LinkedList;

import model.entidades.Itens;
import model.entidades.Produtos;

/**
 * Classe que gerencia o estoque de produtos
 * 
 * @author lucas
 *
 */
public class GerEstoque {

	/**
	 * Determina se os produtos dos itens (pratos) existem, e se a quantidade
	 * necess�ria para faz�-los est� dispon�vel no estoque
	 * 
	 * @param itens
	 * @param listP
	 * @return
	 */
	public boolean verificaItensAntesDaVenda(LinkedList<Itens> itens, LinkedList<LinkedList<Produtos>> listP) {
		

		Hashtable<String, Double> hash = inicializaHash(listP);

		try {
			if (!itens.isEmpty()) {
				for (Itens i : itens) {
					if (!i.getComponentesPrato().isEmpty()) {
						for (Produtos prod : i.getComponentesPrato()) {
							int index = pExists(prod.getNome(), listP);
							if (index > -1) {
								String nomeProduto = listP.get(index).get(0).getNome();
								if (!diminuiQuantidadeHash(hash, nomeProduto, prod.getQuantidade())) {
							//		Alerts.showAlertComum("Erro", "O produto nao é suficiente no estoque: " + prod.getNome(), AlertType.ERROR);
									return false;
								} else {
									continue;
								}
							} else
							//	Alerts.showAlertComum("Erro", "O produto nao existe no estoque:" + prod.getNome(), AlertType.ERROR);
								System.out.printf("O produto %s n�o existe no estoque\n", prod.getNome());
							return false;
						}
					} else {
						System.out.println("Lista de produtos do item vazia");
						return false;
					}
				}
				return true;
			} else {
				System.out.println("Lista de itens da venda vazia");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}

	}

	/**
	 * Retorna uma tabela hash com os estoques totais de cada produto, com a chave
	 * sendo o nome
	 * 
	 * @param listP
	 * @return
	 */
	public Hashtable<String, Double> inicializaHash(LinkedList<LinkedList<Produtos>> listP) {
		Hashtable<String, Double> hash = new Hashtable<String, Double>();

		try {
			for (LinkedList<Produtos> list : listP) {
				String nomeProduto = list.get(0).getNome();
				Double qtd = 0.0;

				for (Produtos p : list) {
					qtd = qtd + p.getQuantidade();
				}
				hash.put(nomeProduto, qtd);
			}
			return hash;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return null;
		}

	}

	/**
	 * Retira a quantiade do produto na tabela hash e atualiza // Caso a quantidade
	 * retirada for menor que 0, significa que não há quantidade suficiente no
	 * estoque, então retorna falso.
	 * 
	 * @param hash
	 * @param nomeProduto
	 * @param quantidadeATirar
	 * @return
	 */
	public boolean diminuiQuantidadeHash(Hashtable<String, Double> hash, String nomeProduto, Double quantidadeATirar) {
		Double qtdTirada = hash.get(nomeProduto) - quantidadeATirar;

		if (qtdTirada < 0) {
			return false;
		} else {
			hash.put(nomeProduto, qtdTirada);
			return true;
		}
	}

	/**
	 * Calcula a quantidade dispon�vel no estoque de um determinado produto
	 * 
	 * @param listP
	 * @return
	 */
	public Double calculaEstoqueDisponivel(LinkedList<Produtos> listP) {
		Double qtdEstoque = 0.0;
		try {
			for (Produtos aux : listP) {
				qtdEstoque = qtdEstoque + aux.getQuantidade();
			}
			return qtdEstoque;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return qtdEstoque;
		}

	}

	/**
	 * Atualiza apenas um item do estoque
	 * 
	 * @param prod  Produto da lista de produtos dos pratos da venda
	 * @param listP Lista de produtos do estabelecimento
	 * @return
	 */

	public boolean removeDoEstoque(Produtos prod, LinkedList<LinkedList<Produtos>> listP, GerProdutos gerP) {
		try {
			int index = this.pExists(prod.getNome(), listP);
			Double qtdProduto = prod.getQuantidade();
			if(index > -1) {
				
				while (qtdProduto > 0) {
					Produtos primeiroLista = listP.get(index).get(0);
					
					if (primeiroLista.getQuantidade().equals(qtdProduto)) {
						gerP.remove(primeiroLista.getCod());
						return true;
					} else if (primeiroLista.getQuantidade() > qtdProduto) {
						primeiroLista.setQuantidade(primeiroLista.getQuantidade() - qtdProduto);
						return true;
					} else {
						qtdProduto = qtdProduto - primeiroLista.getQuantidade();
						gerP.remove(primeiroLista.getCod());
					}
				}
				return true;	
			}else {
				return false;
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}

	}

	/**
	 * Atualiza todos os itens do estoque, um por um
	 * 
	 * @param itens Lista de pratos da venda
	 * @param listP Lista de produtos do estabelecimento
	 * @return
	 */
	public boolean removeTodosDoEstoque(LinkedList<Itens> itens, LinkedList<LinkedList<Produtos>> listP, GerProdutos gerP) {
		for (Itens i : itens) {
			for (Produtos prod : i.getComponentesPrato()) {
				if (removeDoEstoque(prod, listP, gerP) == false) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Verifica se o produto existe pelo nome na lista de produtos do
	 * estabelecimento e retorna o �ndice
	 * 
	 * @param nome
	 * @return
	 */
	public int pExists(String nome, LinkedList<LinkedList<Produtos>> listListP) {
		try {
			int index = 0;
			for (LinkedList<Produtos> listP : listListP) {
				if (listP.get(0).getNome().contains(nome)) {
					return index;
				}
				index++;
			}
			return -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return -1;
		}

	}
}
