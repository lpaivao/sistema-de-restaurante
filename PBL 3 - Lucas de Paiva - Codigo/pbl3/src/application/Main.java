package application;

import java.io.IOException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.terminal.MenuFacade;

public class Main extends Application {
	
	private static Scene loginScene;
	
	private static Scene menuFuncionario;
	
	private static Scene menuGerente;
	
	private static Stage stage;

	private static MenuFacade m;
	
	/**
	 * GERENTE PADRAO: Usuario: MESTRE, Senha: mestre
	 * FUNCIONARIO PADRAO: Usuario: FUNC0, Senha: 123
	 */
	// * GERENTE PADRAO - Usuario: MESTRE, Senha: mestre
	// * FUNCIONARIO PADRAO - Usuario: FUNC0, Senha: 123
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		
		try {
			setMenu(new MenuFacade());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {

			FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("/view/login/PreLoginView.fxml"));
			Parent parentLogin = loaderLogin.load();
			loginScene = new Scene(parentLogin);
			
			FXMLLoader loaderMenuGerente = new FXMLLoader(getClass().getResource("/view/gerente/MenuGerenteView.fxml"));
			Parent parentMenuGerente = loaderMenuGerente.load();
			menuGerente = new Scene(parentMenuGerente);
			
			FXMLLoader loaderMenuFuncionario = new FXMLLoader(getClass().getResource("/view/funcionario/MenuFuncionarioView.fxml"));
			Parent parentMenuFuncionario = loaderMenuFuncionario.load();
			menuFuncionario = new Scene(parentMenuFuncionario);

			primaryStage.setTitle(" ");
			primaryStage.setScene(loginScene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);	
	}
	
	/**
	 * Metodo estatico que atualiza a scene principal
	 * @param nomeTela
	 */
	public static void mudaTela(String nomeTela) {
		switch(nomeTela) {
		case "preLogin":
			stage.setScene(loginScene);
			break;
		case "menuGerente":
			stage.setScene(menuGerente);
			break;
		case "menuFuncionario":
			stage.setScene(menuFuncionario);
			break;
		}
	}

	public static MenuFacade getMenu() {
		return m;
	}

	public static void setMenu(MenuFacade m) {
		Main.m = m;
	}

}
