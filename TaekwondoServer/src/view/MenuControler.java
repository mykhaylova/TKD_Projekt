package view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuControler {

	@FXML private Button sparingButton;
	@FXML private Button tulButton;
	
	@FXML
	private void handleSparingButtonAction (ActionEvent e) throws IOException {
		///Parent home_page_parent = FXMLLoader.load(getClass().getResource("main.fxml"));
		///Scene home_page_scene = new Scene(home_page_parent);
		///Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		new MainWindow();
		doExit();
	}
	
	private void doExit () {
		Platform.exit();
	}
	@FXML
	private void handleTulButtonAction (ActionEvent e) throws IOException {
		///((Node) e.getSource()).getScene().getWindow().hide();
		
		Stage stage = (Stage) tulButton.getScene().getWindow();
        stage.close();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Tul.fxml"));
		Scene scene = new Scene(root, 1200, 650);
		Stage primaryStage = new Stage();
		///scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Taekwondo Server");
		// prevent from resizing
		primaryStage.setResizable(false);
		
		primaryStage.setScene(scene);
		
		
		primaryStage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	    primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
	    primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);	}
	
}
