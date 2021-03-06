package view;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
import model.ScoringTul;
import model.ScoringTul.PointsListener;


public class TulControler implements Initializable, PointsListener {
	
	@FXML
	private ListView<String> list;
	private Service<Void> backgroundhread;	
	
	static String mReferee1ID = "referee1";
	static String mReferee2ID = "referee2";
	static String mReferee3ID = "referee3";
	static String mReferee4ID = "referee4";
	static String mReferee5ID = "referee5";

	ObservableList<String> items = FXCollections.observableArrayList (getIps());
	
	public String[] getIps()
	{
		ArrayList<String> ipsAll = ScoringTul.getAllIPs();
		String[] entries = ipsAll.toArray(new String[ipsAll.size()]);
		return entries;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle rescources)
	{
		list.setItems(items);
	}
	
	private static boolean serverOnBool;
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
	private Label labelScoreBlue;
	@FXML
	private Label labelScoreRed;
	@FXML
	private Button tulEditorButton;
	@FXML
	private Button tulGetScoreButton;
	@FXML 
	private Button tulExtraTimeButton;
	@FXML
	private Label labelRoundTul;
	
	private int tulNumberOfRounds = 1;
	private int tulTotalRounds = 1;
	private boolean extraTimeBool = false;
	
	
	public static void exitTul () {
		boolean quitWindow = ConfirmationBox.show("Do you really want to exit? All the information will be lost!", "Confirm Exit", "Yes", "No");
		
		if (quitWindow) {
			if (serverOnBool) {
				serverOnBool = false;
				ScoringTul.StopServer();
			}
			Platform.exit();
		} else {
			
		}
	}
	

	@FXML
	private void handleTulEditorButtonAction (ActionEvent e) {
		String tulEditor = TulEditorBox.show("Number of Rounds:", "Tul Editor", "Ready", "Cancel");
		
		if (tulEditor== null){
			
		} else {
			//System.out.println("Tul controler received from Tul Editor: " + tulEditor);
			tulNumberOfRounds = Integer.parseInt(tulEditor);
			tulGetScoreButton.setDisable(false);
			labelRoundTul.setText("Round 1");
			tulExtraTimeButton.setDisable(true);
			resetScore();
			//setLabelText(2);
			//setRoundsNumber("1");
			//setRoundsLength(extraTime);
			//resetTemporaryResults();
			//resetWarningAndPenalty();
			//buttonStartTimer.setDisable(false);
		}
	}
	
	@FXML 
	private void handleTulExtraTimeButton (ActionEvent e) {
		String editorResults = TulExtraTimeBox.show("Extra Time?", "Extra Time Editor", "Ready", "Cancel");
		
		if (editorResults.equals("extra")) {
			labelRoundTul.setText("Extra Time");
			tulGetScoreButton.setDisable(false);
			tulNumberOfRounds++;
		}
	}
	
	private void resetScore () {
		labelScoreRed.setText("0");
		labelScoreBlue.setText("0");
	}
	
	@FXML
	private void handleTulGetScoreButton (ActionEvent e){
		
	
		
		int drawScore = Integer.parseInt(labelDrawResult.getText());
		int blueScore = Integer.parseInt(labelBlueResult.getText());
		int redScore = Integer.parseInt(labelRedResult.getText());
		
		if ((blueScore > redScore) && (blueScore > drawScore)) {
			///System.out.println("Blue wins");
			updateScoreLabels(1);
			
		} else if ((blueScore < redScore) && (redScore > drawScore)) {
			///System.out.println("Red wins");
			updateScoreLabels(3);
			
		} else if (((blueScore == redScore) && (blueScore > drawScore)) || ((drawScore>blueScore)&&(drawScore>redScore))){
			///System.out.println("Draw");
			updateScoreLabels(2);
		}
		tulNumberOfRounds--;
		System.out.println("Tul number of Rounds: " + tulNumberOfRounds);
		if (tulNumberOfRounds == 0) {
			tulGetScoreButton.setDisable(true);
			checkDraw();
		} else {
			labelRoundTul.setText("Round 2");
		}
	}
	
	private void checkDraw() {
		int blue = Integer.parseInt(labelScoreBlue.getText());
		int red = Integer.parseInt(labelScoreRed.getText());
		
		if (blue == red) {
			tulExtraTimeButton.setDisable(false);
		}
	}
	
	private void updateScoreLabels (int blueDrawRed){
		
		if (blueDrawRed == 1) {
		int tempBlue = Integer.parseInt(labelScoreBlue.getText());
		tempBlue=tempBlue++;
		labelScoreBlue.setText(String.valueOf(tempBlue));
			
		} else if (blueDrawRed == 3) {
			int tempRed = Integer.parseInt(labelScoreRed.getText());
			tempRed=tempRed++;
			labelScoreRed.setText(String.valueOf(tempRed));
			
		} else {
			int tempBlue = Integer.parseInt(labelScoreBlue.getText());
			tempBlue++;
			labelScoreBlue.setText(String.valueOf(tempBlue));
			int tempRed = Integer.parseInt(labelScoreRed.getText());
			tempRed++;
			labelScoreRed.setText(String.valueOf(tempRed));			
		}
	}

	@FXML
	private void handleBackButtonAction(ActionEvent e) throws IOException {
		
		boolean quitWindow = ConfirmationBox.show("Do you really want to go back to the main menu? All the information will be lost!", "Confirm Exit", "Yes", "No");
		
		if (quitWindow) {
			if (serverOnBool) {
				ScoringTul.StopServer();
				serverOnBool = false;
			}
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
		} else {
			
		}
		
	}

	@FXML
	private void handleExitButtonAction(ActionEvent e) throws IOException {
		
		boolean quitWindow = ConfirmationBox.show("Do you really want to exit? All the information will be lost!", "Confirm Exit", "Yes", "No");
		
		if (quitWindow) {
			if (serverOnBool) {
				serverOnBool = false;
				ScoringTul.StopServer();
			}
			Stage stage = (Stage) exitButtonTul.getScene().getWindow();
			stage.close();
		} else {
			
		}
	}

	@FXML
	private void handleResetButtonAction(ActionEvent e) throws IOException {
		

	}

	@FXML
	private void handleStartButtonAction(ActionEvent e) throws IOException {
		startButtonTul.setStyle("-fx-base: #32cd32");
		stopButtonTul.setStyle("-fx-base: #d0d0d0");
		ScoringTul.StartServer();
		ScoringTul.subscribe(this);
		serverOnBool = true;

	}

	@FXML
	private void handleStopButtonAction(ActionEvent e) throws IOException {
		stopButtonTul.setStyle("-fx-base: #ff0000");
		startButtonTul.setStyle("-fx-base: #d0d0d0");
		ScoringTul.StopServer();
		serverOnBool = false ;
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
		 
	@Override
	public void updatePoints(AtomicInteger points1, AtomicInteger points2, String refereeId, String level) {
		backgroundhread = new Service<Void>() 
		{
			@Override
			protected Task<Void> createTask() 
			{				
				return new Task<Void>() 
				{				
					@Override
					protected Void call() throws Exception 
					{
						Platform.runLater(() -> 
						{             
							if (level.equals("one"))
							{
                               	if (refereeId.equals(mReferee1ID)) 
                               	{   
                            		SimpleFloatProperty nPoints1 = new SimpleFloatProperty();
                            		float newPoints1 = Float.intBitsToFloat(points1.get());
                            		nPoints1.set(newPoints1);
                            		SimpleFloatProperty nPoints2 = new SimpleFloatProperty();
                            		float newPoints2 = Float.intBitsToFloat(points2.get());
                            		nPoints2.set(newPoints2);                                    
                            		r1labelBlueLevel1.textProperty().bind(nPoints1.asString("%.1f"));
        							r1labelRedLevel1.textProperty().bind(nPoints2.asString("%.1f"));        									
        						}        						
        						if (refereeId.equals(mReferee2ID))
        						{        						 
        							SimpleFloatProperty nPoints1 = new SimpleFloatProperty();
                            		float newPoints1 = Float.intBitsToFloat(points1.get());
                            		nPoints1.set(newPoints1);
                            		SimpleFloatProperty nPoints2 = new SimpleFloatProperty();
                            		float newPoints2 = Float.intBitsToFloat(points2.get());
                            		nPoints2.set(newPoints2);                                    
                            		r2labelBlueLevel1.textProperty().bind(nPoints1.asString("%.1f"));
        							r2labelRedLevel1.textProperty().bind(nPoints2.asString("%.1f"));    
        						}
        						if (refereeId.equals(mReferee3ID)) 
        						{        						
        							SimpleFloatProperty nPoints1 = new SimpleFloatProperty();
                            		float newPoints1 = Float.intBitsToFloat(points1.get());
                            		nPoints1.set(newPoints1);
                            		SimpleFloatProperty nPoints2 = new SimpleFloatProperty();
                            		float newPoints2 = Float.intBitsToFloat(points2.get());
                            		nPoints2.set(newPoints2);                                    
                            		r3labelBlueLevel1.textProperty().bind(nPoints1.asString("%.1f"));
        							r3labelRedLevel1.textProperty().bind(nPoints2.asString("%.1f"));     
        						}
        						if (refereeId.equals(mReferee4ID)) 
        						{        						
        							SimpleFloatProperty nPoints1 = new SimpleFloatProperty();
                            		float newPoints1 = Float.intBitsToFloat(points1.get());
                            		nPoints1.set(newPoints1);
                            		SimpleFloatProperty nPoints2 = new SimpleFloatProperty();
                            		float newPoints2 = Float.intBitsToFloat(points2.get());
                            		nPoints2.set(newPoints2);                                    
                            		r4labelBlueLevel1.textProperty().bind(nPoints1.asString("%.1f"));
        							r4labelRedLevel1.textProperty().bind(nPoints2.asString("%.1f"));    
        						}
        						if (refereeId.equals(mReferee5ID)) 
        						{        						
        							SimpleFloatProperty nPoints1 = new SimpleFloatProperty();
                            		float newPoints1 = Float.intBitsToFloat(points1.get());
                            		nPoints1.set(newPoints1);
                            		SimpleFloatProperty nPoints2 = new SimpleFloatProperty();
                            		float newPoints2 = Float.intBitsToFloat(points2.get());
                            		nPoints2.set(newPoints2);                                    
                            		r5labelBlueLevel1.textProperty().bind(nPoints1.asString("%.1f"));
        							r5labelRedLevel1.textProperty().bind(nPoints2.asString("%.1f"));    
        						}
							}
        					});							
						return null;
						}
					};
				}
			};			
		backgroundhread.restart();		
	}
}
