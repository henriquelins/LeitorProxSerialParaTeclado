package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static String style = "/application/darktheme.css";
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LeitorProxView.fxml"));
			BorderPane borderPane = loader.load();
			Scene mainScene = new Scene(borderPane);
			
			//css
			mainScene.getStylesheets().add(style);
			//setUserAgentStylesheet(STYLESHEET_MODENA);
			//setUserAgentStylesheet(STYLESHEET_CASPIAN);
			
			primaryStage.setScene(mainScene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Prox - Serial / Teclado");
			primaryStage.show();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public static void main(String[] args) {
		
		launch(args);
		
	}

	byte[] buffer = null;
}
