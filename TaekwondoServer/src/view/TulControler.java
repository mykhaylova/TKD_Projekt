package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ScoringTul.PointsListener;
import model.TkdServer;

public class TulControler implements Initializable, PointsListener {
	
	@FXML
	private ListView<String> listView;
	private Service<Void> backgroundhread;	
	
	static String mReferee1ID = "referee1";
	static String mReferee2ID = "referee2";
	static String mReferee3ID = "referee3";
	static String mReferee4ID = "referee4";

	ObservableList<String> items = FXCollections.observableArrayList (getIps());
	
	public String[] getIps()
	{
		ArrayList<String> ipsAll = TkdServer.getAllIPs();
		String[] entries = ipsAll.toArray(new String[ipsAll.size()]);
		return entries;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle rescources)
	{
		listView.setItems(items);
	}
	private int tempResultBlue;
	private int tempResultRed;
	private int tempResultDraw;

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
	private Label r1labelBlueLevel1;
	@FXML
	private Label r1labelRedLevel1;
	@FXML
	private Label r1labelBlueLevel2;
	@FXML
	private Label r1labelRedLevel2;
	@FXML
	private Label r1labelBlueLevel3;
	@FXML
	private Label r1labelRedLevel3;
	@FXML
	private Label r2labelBlueLevel1;
	@FXML
	private Label r2labelRedLevel1;
	@FXML
	private Label r2labelBlueLevel2;
	@FXML
	private Label r2labelRedLevel2;
	@FXML
	private Label r2labelBlueLevel3;
	@FXML
	private Label r2labelRedLevel3;
	@FXML
	private Label r3labelBlueLevel1;
	@FXML
	private Label r3labelRedLevel1;
	@FXML
	private Label r3labelBlueLevel2;
	@FXML
	private Label r3labelRedLevel2;
	@FXML
	private Label r3labelBlueLevel3;
	@FXML
	private Label r3labelRedLevel3;
	@FXML
	private Label r4labelBlueLevel1;
	@FXML
	private Label r4labelRedLevel1;
	@FXML
	private Label r4labelBlueLevel2;
	@FXML
	private Label r4labelRedLevel2;
	@FXML
	private Label r4labelBlueLevel3;
	@FXML
	private Label r4labelRedLevel3;
	@FXML
	private Label r5labelBlueLevel1;
	@FXML
	private Label r5labelRedLevel1;
	@FXML
	private Label r5labelBlueLevel2;
	@FXML
	private Label r5labelRedLevel2;
	@FXML
	private Label r5labelBlueLevel3;
	@FXML
	private Label r5labelRedLevel3;
	@FXML
	private Label r1labelBlueTotal;
	@FXML
	private Label r1labelRedTotal;
	@FXML
	private Label r2labelBlueTotal;
	@FXML
	private Label r2labelRedTotal;
	@FXML
	private Label r3labelBlueTotal;
	@FXML
	private Label r3labelRedTotal;
	@FXML
	private Label r4labelBlueTotal;
	@FXML
	private Label r4labelRedTotal;
	@FXML
	private Label r5labelBlueTotal;
	@FXML
	private Label r5labelRedTotal;
	@FXML
	private Label labelBlueResult;
	@FXML
	private Label labelDrawResult;
	@FXML
	private Label labelRedResult;

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

	}

	@FXML
	private void handleStartButtonAction(ActionEvent e) throws IOException {
		startButtonTul.setStyle("-fx-base: #32cd32");
		stopButtonTul.setStyle("-fx-base: #d0d0d0");
		/// TkdServer.StartServer();
		/// TkdServer.subscribe(this);

	}

	@FXML
	private void handleStopButtonAction(ActionEvent e) throws IOException {
		stopButtonTul.setStyle("-fx-base: #ff0000");
		startButtonTul.setStyle("-fx-base: #d0d0d0");
		/// TkdServer.StopServer();
	}
	
	


	private void sumPointsRef() {
		
		tempResultBlue = 0;
		tempResultRed = 0;
		tempResultDraw = 0;

		int r1BlueTotal = Integer.parseInt(r1labelBlueLevel1.getText()) + Integer.parseInt(r1labelBlueLevel2.getText())
				+ Integer.parseInt(r1labelBlueLevel3.getText());

		r1labelBlueTotal.setText(String.valueOf(r1BlueTotal));

		int r1RedTotal = Integer.parseInt(r1labelRedLevel1.getText()) + Integer.parseInt(r1labelRedLevel2.getText())
				+ Integer.parseInt(r1labelRedLevel3.getText());

		r1labelRedTotal.setText(String.valueOf(r1RedTotal));
		
		if (r1BlueTotal>r1RedTotal) {
			tempResultBlue++;
		} else if (r1BlueTotal<r1RedTotal){
			tempResultRed++;
		} else {
			tempResultDraw++;
		}

		int r2BlueTotal = Integer.parseInt(r2labelBlueLevel1.getText()) + Integer.parseInt(r2labelBlueLevel2.getText())
				+ Integer.parseInt(r2labelBlueLevel3.getText());

		r2labelBlueTotal.setText(String.valueOf(r2BlueTotal));

		int r2RedTotal = Integer.parseInt(r2labelRedLevel1.getText()) + Integer.parseInt(r2labelRedLevel2.getText())
				+ Integer.parseInt(r2labelRedLevel3.getText());

		r2labelRedTotal.setText(String.valueOf(r2RedTotal));

		if (r2BlueTotal>r2RedTotal) {
			tempResultBlue++;
		} else if (r2BlueTotal<r2RedTotal){
			tempResultRed++;
		} else {
			tempResultDraw++;
		}
		
		int r3BlueTotal = Integer.parseInt(r3labelBlueLevel1.getText()) + Integer.parseInt(r3labelBlueLevel2.getText())
				+ Integer.parseInt(r3labelBlueLevel3.getText());

		r3labelBlueTotal.setText(String.valueOf(r3BlueTotal));

		int r3RedTotal = Integer.parseInt(r3labelRedLevel1.getText()) + Integer.parseInt(r3labelRedLevel2.getText())
				+ Integer.parseInt(r3labelRedLevel3.getText());

		r3labelRedTotal.setText(String.valueOf(r3RedTotal));
		
		if (r3BlueTotal>r3RedTotal) {
			tempResultBlue++;
		} else if (r3BlueTotal<r3RedTotal){
			tempResultRed++;
		} else {
			tempResultDraw++;
		}

		int r4BlueTotal = Integer.parseInt(r4labelBlueLevel1.getText()) + Integer.parseInt(r4labelBlueLevel2.getText())
				+ Integer.parseInt(r4labelBlueLevel3.getText());

		r4labelBlueTotal.setText(String.valueOf(r4BlueTotal));

		int r4RedTotal = Integer.parseInt(r4labelRedLevel1.getText()) + Integer.parseInt(r4labelRedLevel2.getText())
				+ Integer.parseInt(r4labelRedLevel3.getText());

		r4labelRedTotal.setText(String.valueOf(r4RedTotal));
		
		if (r4BlueTotal>r4RedTotal) {
			tempResultBlue++;
		} else if (r4BlueTotal<r4RedTotal){
			tempResultRed++;
		} else {
			tempResultDraw++;
		}

		int r5BlueTotal = Integer.parseInt(r5labelBlueLevel1.getText()) + Integer.parseInt(r5labelBlueLevel2.getText())
				+ Integer.parseInt(r5labelBlueLevel3.getText());

		r5labelBlueTotal.setText(String.valueOf(r5BlueTotal));

		int r5RedTotal = Integer.parseInt(r5labelRedLevel1.getText()) + Integer.parseInt(r5labelRedLevel2.getText())
				+ Integer.parseInt(r5labelRedLevel3.getText());

		r5labelRedTotal.setText(String.valueOf(r5RedTotal));
		
		if (r5BlueTotal>r5RedTotal) {
			tempResultBlue++;
		} else if (r5BlueTotal<r5RedTotal){
			tempResultRed++;
		} else {
			tempResultDraw++;
		}
		
		labelBlueResult.setText(String.valueOf(tempResultBlue));
		labelDrawResult.setText(String.valueOf(tempResultDraw));
		labelRedResult.setText(String.valueOf(tempResultRed));
		
		
	}


}
