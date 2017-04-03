package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationBox {
	
		static Stage stage;
		static boolean btnYesClicked;
		///static boolean btnNewClicked;
		

	public static boolean show (String message, String title, String textYes, String textNo) {
		
		btnYesClicked = false;

		
		
		stage = new Stage ();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setWidth(500);
	
		
		
		Label lbl = new Label();
		lbl.setText(message);
		
		Button btnOld = new Button();
		btnOld.setText(textYes);
		btnOld.setOnAction(e-> btnYes_Clicked());
		
		Button btnNew = new Button();
		btnNew.setText(textNo);
		btnNew.setOnAction(e-> btnNo_Clicked());
		
		HBox paneBtn = new HBox(20);
		paneBtn.setAlignment(Pos.CENTER);
		paneBtn.getChildren().addAll(btnOld, btnNew);
		
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, paneBtn);
		pane.setAlignment(Pos.CENTER);
		
		
		
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
		return btnYesClicked;
	}


	private static void btnNo_Clicked() {
		
		stage.close();
		btnYesClicked = false;

	}


	private static void btnYes_Clicked() {
		
		stage.close();
		btnYesClicked = true;
		
	}
}
