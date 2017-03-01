package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuControler extends Application {

	@FXML
	private Button sparingButton;
	@FXML
	private Button tulButton;

	@FXML
	private void handleSparingButtonAction(ActionEvent e) throws IOException {

		boolean oldSparingWindow = ConfirmationBox.show("Which sparing window?", "Confirmation", "Old", "New");

		if (oldSparingWindow == false) {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("Sparing.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene2 = new Scene(root, 1200, 650);
			Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
			stage.setTitle("Taekwondo Sparring");
			stage.setResizable(false);
			stage.setScene(scene2);
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
			stage.show();
		} else {
			doExit();
		}
	}

	private void doExit() {
		Platform.exit();
	}

	@FXML
	private void handleTulButtonAction(ActionEvent e) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("Tul2.fxml"));
		Parent root = (Parent) loader.load();

		Scene scene2 = new Scene(root, 1200, 650);
		Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
		stage.setTitle("Taekwondo Tul");
		stage.setResizable(false);
		stage.setScene(scene2);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.show();

	}

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
			Scene scene = new Scene(root, 1200, 650);
			primaryStage.setTitle("Taekwondo");
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> primaryStage.close());
			primaryStage.setScene(scene);
			primaryStage.show();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
			primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
