package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TulControler {

	@FXML
	private Button startButtonTul;
	@FXML
	private Button stopButtonTul;
	@FXML
	private Button backButtonTul;
	@FXML
	private Button resetButtonTul;
	@FXML
	private Button exitButtonTul;

	@FXML
	private Label topLeftLabelTul;
	@FXML
	private Label topRightLabelTul;
	@FXML
	private Label middleLeftLabelTul;
	@FXML
	private Label middleRightLabelTul;
	@FXML
	private Label bottomLeftLabelTul;
	@FXML
	private Label bottomRightLabelTul;

	@FXML
	private void handleBackButtonAction(ActionEvent e) throws IOException {
		/// stop server ???
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("Menu.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene2 = new Scene(root, 1200, 650);
		Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
		stage.setTitle("Taekwondo");
		stage.setResizable(false);
		stage.setScene(scene2);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.show();
	}

	@FXML
	private void handleExitButtonAction(ActionEvent e) throws IOException {
		Stage stage = (Stage) exitButtonTul.getScene().getWindow();
		stage.close();

	}

	@FXML
	private void handleResetButtonAction(ActionEvent e) throws IOException {
		topLeftLabelTul.setText("10");
		topRightLabelTul.setText("10");
	}

	@FXML
	private void handleStartButtonAction(ActionEvent e) throws IOException {
		startButtonTul.setStyle("-fx-base: #32cd32");
		stopButtonTul.setStyle("-fx-base: #d0d0d0");

	}

	@FXML
	private void handleStopButtonAction(ActionEvent e) throws IOException {
		stopButtonTul.setStyle("-fx-base: #ff0000");
		startButtonTul.setStyle("-fx-base: #d0d0d0");
	}

	public void topLeftLabelTulSetText(String text) {
		topLeftLabelTul.setText(text);
	}

	public void topRightLabelTulSetText(String text) {
		topRightLabelTul.setText(text);
	}

	public void middleLeftLabelTulSetText(String text) {
		middleLeftLabelTul.setText(text);
	}

	public void middleRightLabelTulSetText(String text) {
		middleRightLabelTul.setText(text);
	}

	public void bottomLeftLabelTulSetText(String text) {
		middleLeftLabelTul.setText(text);
	}

	public void bottomRightLabelTulSetText(String text) {
		middleRightLabelTul.setText(text);
	}

}
