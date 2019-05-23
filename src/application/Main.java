package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {



	public static Stage  primaryStage;

	/**
	 * A classe principal da aplica��o em JavaFX
	 */
	/**
	 * Inicia o layout da aplica��o
	 */
	//Estou usando crud sem indice, verificar como fazer a altera��o de forma correta
	@Override
	public void start(Stage primaryStage) {

		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("Principal.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Bem Vindo!");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			Main.primaryStage = primaryStage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Inicia outra p�gina
	 * 
	 * @param nameFile
	 * @param titlePage
	 */
	public void loadScene(String nameFile, String titlePage) {

		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource(nameFile));
			Scene scene = new Scene(root);
			primaryStage.setTitle(titlePage);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
