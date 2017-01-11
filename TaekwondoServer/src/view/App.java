package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {
	

	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
			Scene scene = new Scene(root, 1200, 650);
			
			///scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Taekwondo Server");
			// prevent from resizing
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e->primaryStage.close());
			primaryStage.setScene(scene);
			
			
			primaryStage.show();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		    primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
		    primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
		    
	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}


	
	public static void main(String[] args) {
		launch(args);
	}
		


}
