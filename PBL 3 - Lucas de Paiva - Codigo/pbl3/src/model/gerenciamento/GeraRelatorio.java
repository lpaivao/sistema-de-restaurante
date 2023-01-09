package model.gerenciamento;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.entidades.Fornecedores;
import model.entidades.Itens;
import model.entidades.Produtos;
import model.entidades.Vendas;

/**
 * Classe que gera os relat�rios
 * 
 * @author lucas
 *
 */
public class GeraRelatorio {

	/**
	 * Metodo que gera relatorio geral de vendas
	 * 
	 * @param list
	 */
	public void geraRelatorioVendasGeral(LinkedList<Vendas> list) {
		Document d = new Document();

		try {

			PdfWriter.getInstance(d, new FileOutputStream("Relatorio geral de vendas.pdf"));
			d.open();

			d.add(new Paragraph("VENDAS GERAL"));
			d.add(new Paragraph(" "));

			PdfPTable t = new PdfPTable(4);
			t.addCell("Cod");
			t.addCell("Preco total");
			t.addCell("Registro");
			t.addCell("Pagamento");
			d.add(t);

			for (Vendas v : list) {
				PdfPTable table = new PdfPTable(4);
				table.addCell(v.getCod());
				table.addCell(String.valueOf(v.getPrecoTotal()));
				table.addCell(v.getRegistro());
				table.addCell(v.getModPag());
				d.add(table);
			}

		} catch (FileNotFoundException | DocumentException e) {
			e.getMessage();
			e.printStackTrace();
		}

		finally {
			d.close();
		}
	}

	/**
	 * Metodo que gera relatorio de vendas por um determinado per�odo
	 * 
	 * @param list  Lista de vendas
	 * @param date1 Primeira data do per�odo
	 * @param date2 Segunda data do per�odo
	 * @throws ParseException
	 */
	public boolean geraRelatorioVendasPeriodo(LinkedList<Vendas> list, LocalDate date1, LocalDate date2) {
		Document d = new Document();
		DateTimeFormatter formatBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ArrayList<Vendas> aux = organizaPorPeriodo(date1, date2, list);
		
		if (!aux.isEmpty()) {
			try {

				PdfWriter.getInstance(d, new FileOutputStream("Vendas por periodo.pdf"));
				d.open();
				d.add(new Paragraph(
						"VENDAS POR PERIODO ->  " + date1.format(formatBarra) + " a " + date2.format(formatBarra)));

				PdfPTable t = new PdfPTable(4);
				t.addCell("Cod");
				t.addCell("Preco total");
				t.addCell("Registro");
				t.addCell("Pagamento");
				d.add(t);

				for (Vendas v : aux) {
					PdfPTable table = new PdfPTable(4);
					table.addCell(v.getCod());
					table.addCell(String.valueOf(v.getPrecoTotal()));
					table.addCell(v.getRegistro());
					table.addCell(v.getModPag());
					d.add(table);
				}
				return true;

			} catch (FileNotFoundException | DocumentException e) {
				e.getMessage();
				e.printStackTrace();
				return false;
			}

			finally {
				d.close();
			}
		}
		return false;

	}

	/**
	 * Metodo que gera relatorio de vendas por tipo de prato
	 * 
	 * @param list      Lista de vendas
	 * @param tipoPrato Categoria do prato do relat�rio
	 */
	public boolean geraRelatorioVendasPrato(LinkedList<Vendas> list, String tipoPrato) {
		Document d = new Document();
		
		ArrayList<Vendas> aux = organizaPorTipoDePrato(tipoPrato, list);
			
		if (!aux.isEmpty()) {
			try {
				PdfWriter.getInstance(d, new FileOutputStream("Venda por tipo de prato.pdf"));
				d.open();
				d.add(new Paragraph("VENDAS POR TIPO DE PRATO -> " + tipoPrato));
				d.add(new Paragraph(" "));
				
				PdfPTable t = new PdfPTable(4);
				t.addCell("Cod");
				t.addCell("Preco total");
				t.addCell("Registro");
				t.addCell("Pagamento");
				d.add(t);

				for (Vendas v : aux) {
					PdfPTable table = new PdfPTable(4);
					table.addCell(v.getCod());
					table.addCell(String.valueOf(v.getPrecoTotal()));
					table.addCell(v.getRegistro());
					table.addCell(v.getModPag());
					d.add(table);
				}
				return true;

			} catch (FileNotFoundException | DocumentException e) {
				e.getMessage();
				e.printStackTrace();
				return false;
			}

			finally {
				d.close();
			}
		}
		else {
			return false;
		}
	}

	/**
	 * M�todo que retorna um ArrayList ordenada de todas as vendas do per�odo
	 * especificado
	 * 
	 * @param date1
	 * @param date2
	 * @param list
	 * @return Lista de vendas do per�odo, ordenada da mais nova para a mais antiga
	 */
	private ArrayList<Vendas> organizaPorPeriodo(LocalDate date1, LocalDate date2, LinkedList<Vendas> list) {

		ArrayList<Vendas> v = new ArrayList<Vendas>();

		for (Vendas aux : list) {
			if (aux.getDataRelatorio().compareTo(date1) >= 0 && aux.getDataRelatorio().compareTo(date2) <= 0) {
				v.add(aux);
			}
		}

		return v;
	}

	/**
	 * M�todo que retorna um ArrayList ordenada de todas as vendas de uma
	 * determinada categoria de prato
	 * 
	 * @param tipoPrato Categoria do prato
	 * @param list
	 * @return
	 */
	private ArrayList<Vendas> organizaPorTipoDePrato(String tipoPrato, LinkedList<Vendas> list) {
		
		ArrayList<Vendas> v = new ArrayList<Vendas>();

		for (Vendas aux : list) {
			for (Itens i : aux.getItens()) {
				if (i.getCategoria().equalsIgnoreCase(tipoPrato)) {
					v.add(aux);
					break;
				}
			}
		}
		return v;
	}

	/**
	 * M�todo que gera um relat�rio detalhado de todos os itens do estoque
	 * 
	 * @param list
	 */
	public void geraRelatorioQtdTotalEstoque(LinkedList<LinkedList<Produtos>> list) {
		Document d = new Document();

		try {

			PdfWriter.getInstance(d, new FileOutputStream("Quantidade total do estoque.pdf"));
			d.open();

			d.add(new Paragraph("Relatorio total do estoque"));
			d.add(new Paragraph(" "));

			PdfPTable t = new PdfPTable(5);
			t.addCell("Codigo");
			t.addCell("Nome");
			t.addCell("Validade");
			t.addCell("Preço");
			t.addCell("Quantidade");
			d.add(t);

			for (LinkedList<Produtos> p : list) {
				for (Produtos aux : p) {
					PdfPTable table = new PdfPTable(5);
					table.addCell(aux.getCod());
					table.addCell(aux.getNome());
					table.addCell(aux.getValidade());
					table.addCell(String.valueOf(aux.getPreco()));
					table.addCell(String.valueOf(aux.getQuantidade()));
					d.add(table);
				}
			}

		} catch (FileNotFoundException | DocumentException e) {
			e.getMessage();
			e.printStackTrace();
		}

		finally {
			d.close();
		}
	}

	/**
	 * M�todo que gera um relat�rio da quantidade total dos produtos, sem a
	 * necessidade de mostrar todos os produtos da lista de lista
	 * 
	 * @param list
	 */
	public boolean geraRelatorioQtdPorProduto(LinkedList<LinkedList<Produtos>> list) {
		Document d = new Document();

		ArrayList<Produtos> listProd = this.OrganizaQtdDoProdutoTotal(list);

		if (!listProd.isEmpty()) {
			try {

				PdfWriter.getInstance(d, new FileOutputStream("Quantidade por produto.pdf"));
				d.open();

				d.add(new Paragraph("Relatorio da quantidade do produto"));
				d.add(new Paragraph(" "));
				
				PdfPTable t = new PdfPTable(2);
				t.addCell("Nome");
				t.addCell("Quantidade");
				d.add(t);
				
				for (Produtos aux : listProd) {
					PdfPTable table = new PdfPTable(2);
					table.addCell(aux.getNome());
					table.addCell(String.valueOf(aux.getQuantidade()));
					d.add(table);
				}
				return true;

			} catch (FileNotFoundException | DocumentException e) {
				e.getMessage();
				e.printStackTrace();
				return false;
			}

			finally {
				d.close();
			}
		}
		return false;
	}

	/**
	 * M�todo que gera um relat�rio de todos os produtos, na ordem dos que est�o
	 * mais pr�ximos de vencer
	 * 
	 * @param list
	 */
	public boolean geraRelatorioProdutoAVencer(LinkedList<LinkedList<Produtos>> list) {
		Document d = new Document();
		ArrayList<Produtos> listProd = OrganizaProdutosAVencer(list);
		if (!listProd.isEmpty()) {
			try {

				PdfWriter.getInstance(d, new FileOutputStream("Produtos a vencer.pdf"));
				d.open();

				d.add(new Paragraph("Relatorio de produtos a vencer:"));
				d.add(new Paragraph(" "));
				
				PdfPTable t = new PdfPTable(4);
				t.addCell("Código");
				t.addCell("Nome");
				t.addCell("Quantidade");
				t.addCell("Validade");
				d.add(t);

				for (Produtos aux : listProd) {
					PdfPTable table = new PdfPTable(4);
					table.addCell(aux.getCod());
					table.addCell(aux.getNome());
					table.addCell(String.valueOf(aux.getQuantidade()));
					table.addCell(aux.getValidade());
					d.add(table);
				}
				return true;

			} catch (FileNotFoundException | DocumentException e) {
				e.getMessage();
				e.printStackTrace();
				return false;
			}

			finally {
				d.close();
			}
		}
		return false;
	}

	/**
	 * M�todo que retorna um ArrayList ordenada de produtos, com a quantidade total
	 * do estoque de forma coletiva dos produtos
	 * 
	 * @param list
	 * @return
	 */
	private ArrayList<Produtos> OrganizaQtdDoProdutoTotal(LinkedList<LinkedList<Produtos>> list) {

		ArrayList<Produtos> p = new ArrayList<Produtos>();

		for (LinkedList<Produtos> listP : list) {
			Double qtdEstoque = 0.0;
			for (Produtos aux : listP) {
				qtdEstoque = qtdEstoque + aux.getQuantidade();
			}
			Produtos temp = new Produtos(listP.getFirst().getNome(), qtdEstoque);
			p.add(temp);
		}

		p.sort(Comparator.comparing(Produtos::getNome));

		return p;
	}

	/**
	 * M�todo que retorna um ArrayList de produtos, ordenada pela sua validade
	 * 
	 * @param list
	 * @return
	 */
	private ArrayList<Produtos> OrganizaProdutosAVencer(LinkedList<LinkedList<Produtos>> list) {

		ArrayList<Produtos> p = new ArrayList<Produtos>();

		for (LinkedList<Produtos> listP : list) {
			for (Produtos aux : listP) {
				p.add(aux);
			}
		}

		p.sort(Comparator.comparing(Produtos::getValidadeDate));

		return p;
	}

	/**
	 * M�todo que gera um relat�rio de todos os fornecedores dos produtos que
	 * existem no restaurante
	 * 
	 * @param list
	 */
	public void geraRelatorioFornecedoresProduto(LinkedList<LinkedList<Produtos>> list) {

		Document d = new Document();

		try {

			PdfWriter.getInstance(d, new FileOutputStream("Fornecedores por produto.pdf"));
			d.open();

			d.add(new Paragraph("Relatorio de fornecedores por produto"));
			d.add(new Paragraph(" "));
			
			PdfPTable t = new PdfPTable(5);
			t.addCell("Produto");
			t.addCell("Código");
			t.addCell("Nome");
			t.addCell("CNPJ");
			t.addCell("Endereço");
			d.add(t);

			for (LinkedList<Produtos> listP : list) {
				for (Produtos aux : listP) {
					PdfPTable table = new PdfPTable(5);
					table.addCell(listP.get(0).getNome());
					table.addCell(aux.getFornecedor().getCod());
					table.addCell(aux.getFornecedor().getNome());
					table.addCell(aux.getFornecedor().getCnpj());
					table.addCell(aux.getFornecedor().getEndereco());
					d.add(table);
				}
			}

		} catch (FileNotFoundException | DocumentException e) {
			e.getMessage();
			e.printStackTrace();
		}

		finally {
			d.close();
		}

	}

	/**
	 * M�todo que gera um relat�rio de todos os fornecedores dispon�veis
	 * 
	 * @param list
	 */
	public void geraRelatorioFornecedoresGeral(LinkedList<Fornecedores> list) {

		Document d = new Document();

		try {

			PdfWriter.getInstance(d, new FileOutputStream("Relatorio geral de fornecedores.pdf"));
			d.open();

			d.add(new Paragraph("Relatorio geral de fornecedores"));
			d.add(new Paragraph(" "));
			
			PdfPTable t = new PdfPTable(4);
			t.addCell("Código");
			t.addCell("Nome");
			t.addCell("CNPJ");
			t.addCell("Endereço");
			d.add(t);

			for (Fornecedores f : list) {
				PdfPTable table = new PdfPTable(4);
				table.addCell(f.getCod());
				table.addCell(f.getNome());
				table.addCell(f.getCnpj());
				table.addCell(f.getEndereco());
				d.add(table);

			}

		} catch (FileNotFoundException | DocumentException e) {
			e.getMessage();
			e.printStackTrace();
		}

		finally {
			d.close();
		}
	}
	
	/**
	 * Metodo que gera relatorio geral de vendas
	 * 
	 * @param list
	 */
	public boolean geraNotaFiscal(Vendas vendas) {
		Document d = new Document();

		if(vendas == null || vendas.getItens().isEmpty()) {
			return false;
		}
		
		try {

			PdfWriter.getInstance(d, new FileOutputStream("Nota fiscal " + vendas.getCod() + ".pdf"));
			d.open();

			d.add(new Paragraph("Nota fiscal -> " + vendas.getCod() +  " - " + vendas.getRegistro() + " - " + vendas.getModPag() + " - " + vendas.getPrecoTotal()));
			d.add(new Paragraph(" "));

			PdfPTable t = new PdfPTable(2);
			t.addCell("Nome do item");
			t.addCell("Preco do item");
			d.add(t);

			for (Itens i : vendas.getItens()) {
				PdfPTable table = new PdfPTable(2);
				table.addCell(i.getNome());
				table.addCell(String.valueOf(i.getPreco()));
				d.add(table);
			}
			
			return true;

		} catch (FileNotFoundException | DocumentException e) {
			e.getMessage();
			e.printStackTrace();
			return false;
		}

		finally {
			d.close();
		}
	}
}
