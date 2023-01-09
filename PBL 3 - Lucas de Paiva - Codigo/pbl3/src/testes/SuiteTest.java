package testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({testGerClientes.class, testGerGerentes.class, testGerFuncionarios.class, testCardapio.class,
	testGerProdutos.class, TesteGerVendas.class, testGerFornecedores.class})

public class SuiteTest {

}
