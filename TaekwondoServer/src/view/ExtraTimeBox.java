package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExtraTimeBox implements EventHandler<ActionEvent> {

	static Stage stage;
	static String editorExtraTime;
	private static String extraTimeL;

	private ChoiceBox<String>extraTimeLenghtChoice;
	private Button btnReady;
	/// static boolean btnNewClicked;

	public static   String show(String message, String title, String textReady, String textCancel) {

		editorExtraTime = null;

		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setHeight(315);
		stage.setWidth(280);

		Label lbl = new Label();
		lbl.setText(message);
		
		ChoiceBox<String>extraTimeLengthChoice = new ChoiceBox<String>();
		extraTimeLengthChoice.getItems().add("00:30");
		extraTimeLengthChoice.getItems().add("00:45");
		extraTimeLengthChoice.getItems().add("01:00");
		extraTimeLengthChoice.setValue("00:30");
		extraTimeL = "00:30";
		extraTimeLengthChoice.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)-> extraTimeLength(newValue));
		
		Button btnReady = new Button();
		btnReady.setText(textReady);
		btnReady.setOnAction(e-> btnReady_Clicked());

		Button btnCancel = new Button();
		btnCancel.setText(textCancel);
		btnCancel.setOnAction(e -> btnCancel_Clicked());

		HBox paneBtn = new HBox();
        ///paneBtn.setPadding(new Insets(5));
        paneBtn.setAlignment(Pos.CENTER);
		paneBtn.getChildren().addAll(btnReady, btnCancel);

		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, extraTimeLengthChoice, paneBtn);
		pane.setAlignment(Pos.CENTER);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
		return editorExtraTime;
	}

	private static Object extraTimeLength(String newValue) {
		System.out.println("extraTimeLength: " + newValue);
		extraTimeL = newValue;
		return null;
	}

	private static void btnCancel_Clicked() {

		editorExtraTime = null;
		stage.close();
		//btnOldClicked = false;

	}

	private static void btnReady_Clicked() {

	
		///getChoice();
		editorExtraTime = extraTimeL;
		//new MainWindow();
		System.out.println("BTN READY: " + editorExtraTime);
		stage.close();
		//btnOldClicked = true;

		;
	}
	/*
	private void getChoice () {
		editorExtraTime = choiceRounds.getValue();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource()== btnReady){
			editorExtraTimeChoice = extraTimeL.getValue();
			stage.close();
		}
		
	}
	 */

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}
