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
		static boolean btnOldClicked;
		///static boolean btnNewClicked;
		

	public static boolean show (String message, String title, String textOld, String textNew) {
		
		btnOldClicked = false;

		
		
		stage = new Stage ();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setWidth(250);
	
		
		
		Label lbl = new Label();
		lbl.setText(message);
		
		Button btnOld = new Button();
		btnOld.setText(textOld);
		btnOld.setOnAction(e-> btnOld_Clicked());
		
		Button btnNew = new Button();
		btnNew.setText(textNew);
		btnNew.setOnAction(e-> btnNew_Clicked());
		
		HBox paneBtn = new HBox(20);
		paneBtn.getChildren().addAll(btnOld, btnNew);
		
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, paneBtn);
		pane.setAlignment(Pos.CENTER);
		
		
		
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
		return btnOldClicked;
	}


	private static void btnNew_Clicked() {
		
		stage.close();
		btnOldClicked = false;

	}


	private static void btnOld_Clicked() {
		
		new MainWindow();
		stage.close();
		btnOldClicked = true;
		


;
	}
}
