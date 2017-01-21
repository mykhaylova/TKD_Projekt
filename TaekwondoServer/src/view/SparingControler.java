package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SparingControler {
	
	ObservableList<String> roundsLengthList = FXCollections.observableArrayList("1", "2", "3");
	ObservableList<String> numberOfRoundsList = FXCollections.observableArrayList("00:30", "01:00", "01:30", "02:00", "02:30", "03:00");
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
    private Label midleLeftLabelTul;

    @FXML
    private Label bottomLeftLabelTul;

    @FXML
    private Label topRightLabelTul;

    @FXML
    private Label midleRightLabelTul;

    @FXML
    private Label bottomRightLabelTul;

    @FXML
    private ChoiceBox<String> numberOfRoundsBoxSparing;

    @FXML
    private ChoiceBox<String> roundsLengthBoxSparing;
    
    @FXML 
    private void initialize() {
    	
    	numberOfRoundsBoxSparing.setValue("3");
    	numberOfRoundsBoxSparing.setItems(numberOfRoundsList);
    	
    	roundsLengthBoxSparing.setValue("02:00");
    	roundsLengthBoxSparing.setItems(roundsLengthList);
    }


    @FXML
    void handleBackButtonAction(ActionEvent e) throws IOException {
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
    void handleExitButtonAction(ActionEvent event) {

    }

    @FXML
    void handleResetButtonAction(ActionEvent event) {

    }

    @FXML
    void handleStartButtonAction(ActionEvent event) {

    }

    @FXML
    void handleStopButtonAction(ActionEvent event) {

    }

}
