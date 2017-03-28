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

public class ListBox implements EventHandler<ActionEvent> {

	static Stage stage;
	static String editorResult;
	private static String roundsN;
	private static String roundsL;
	private static String restL;
	private ChoiceBox<String>choiceRounds;
	private Button btnReady;
	/// static boolean btnNewClicked;

	public static   String show(String message, String title, String textReady, String textCancel) {

		editorResult = null;

		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setHeight(315);
		stage.setWidth(280);

		Label lbl = new Label();
		lbl.setText(message);
		
		ChoiceBox<String>choiceRounds = new ChoiceBox<String>();
		choiceRounds.getItems().add("1");
		choiceRounds.getItems().add("2");
		choiceRounds.getItems().add("3");
		choiceRounds.setValue("2");
		roundsN = "2";
		choiceRounds.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)-> resultRounds(newValue));
		
		Label lbl2 = new Label();
		lbl2.setText("Round's Length:");
		
		ChoiceBox<String>choiceRoundsLength = new ChoiceBox<String>();
		choiceRoundsLength.getItems().add("00:30");
		choiceRoundsLength.getItems().add("00:45");
		choiceRoundsLength.getItems().add("01:00");
		choiceRoundsLength.getItems().add("01:30");
		choiceRoundsLength.getItems().add("02:00");
		choiceRoundsLength.getItems().add("02:30");
		choiceRoundsLength.getItems().add("03:00");
		choiceRoundsLength.setValue("02:00");
		roundsL="02:00";
		choiceRoundsLength.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)-> resultRoundsLength(newValue));
		
		Label lbl3 = new Label();
		lbl3.setText("Rest's Length:");
		
		ChoiceBox<String>choiceRestsLength = new ChoiceBox<String>();
		choiceRestsLength.getItems().add("00:30");
		choiceRestsLength.getItems().add("00:45");
		choiceRestsLength.getItems().add("01:00");
		choiceRestsLength.getItems().add("01:30");
		choiceRestsLength.getItems().add("02:00");
		choiceRestsLength.getItems().add("02:30");
		choiceRestsLength.getItems().add("03:00");
		choiceRestsLength.setValue("00:30");
		restL="00:30";
		choiceRestsLength.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)-> resultRestsLength(newValue));

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
		pane.getChildren().addAll(lbl, choiceRounds,lbl2,choiceRoundsLength, lbl3, choiceRestsLength, paneBtn);
		pane.setAlignment(Pos.CENTER);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
		return editorResult;
	}

	private static Object resultRestsLength(String newValue) {
		//System.out.println("resultRestLength: " + newValue);
		restL = newValue;
		return null;
	}

	private static Object resultRoundsLength(String newValue) {
		roundsL = newValue;
		//System.out.println("resultRoundsLength: " + newValue);
		return null;
	}

	private static Object resultRounds(String newValue) {
		//System.out.println("resultRounds: "+ newValue);
		//editor[0] = newValue;
		//System.out.println("Array na poz 0: " + editor[0].toString());
		roundsN = newValue;
		return null;
	}

	private static void btnCancel_Clicked() {

		editorResult = null;
		stage.close();
		//btnOldClicked = false;

	}

	private static void btnReady_Clicked() {

	
		///getChoice();
		editorResult = roundsN+roundsL+restL;
		//new MainWindow();
		//System.out.println("BTN READY: " + editorResult);
		stage.close();
		//btnOldClicked = true;

		;
	}
	private void getChoice () {
		editorResult = choiceRounds.getValue();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource()== btnReady){
			editorResult = choiceRounds.getValue();
			stage.close();
		}
		
	}

}
