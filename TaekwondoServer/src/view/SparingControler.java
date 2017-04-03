package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.Timer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ScoringTul;
import model.TkdServer;
import model.TkdServer.PointListener;

public class SparingControler implements Initializable, PointListener {
	private static boolean serverOnBool;
	@FXML
	private ListView<String> listView;
	private Service<Void> backgroundthread;	
	
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

	private int roundLength;
	private int roundLengthSet;
	private Timer timer;
	private int rblue;
	private int rred;
	private int draw;
	private boolean drawBool;
	private boolean extraBool;
	@FXML
	private Label labelRound;
	@FXML
	private Label labelRoundTime;
	@FXML
	private Label labelWarningBlue;
	@FXML
	private Label labelPenaltyBlue;
	@FXML
	private Label labelWarningRed;
	@FXML
	private Label labelPenaltyRed;
	@FXML
	private Label labelR1BlueScore;
	@FXML
	private Label labelR2BlueScore;
	@FXML
	private Label labelR3BlueScore;
	@FXML
	private Label labelR4RedScore;
	@FXML
	private Label labelR1RedScore;
	@FXML
	private Label labelR2RedScore;
	@FXML
	private Label labelR3RedScore;
	@FXML
	private Label labelR4BlueScore;
	@FXML
	private Label labelBlueResult;
	@FXML
	private Label labelDrawResult;
	@FXML
	private Label labelRedResult;
	@FXML
	private Label blueScore;
	@FXML
	private Label redScore;

	@FXML
	private Button minusWarningBlueButtonSparing;
	@FXML
	private Button plusWarningBlueButtonSparing;
	@FXML
	private Button minusPenaltyBlueButtonSparing;
	@FXML
	private Button plusPenaltyBlueButtonSparing;
	@FXML
	private Button minusWarningRedButtonSparing;
	@FXML
	private Button plusWarningRedButtonSparing;
	@FXML
	private Button minusPenaltyRedButtonSparing;
	@FXML
	private Button plusPenaltyRedButtonSparing;
	@FXML
	private Button newSparingButtonAction;
	@FXML 
	private Button buttonStopTimer;
	@FXML
	private Button buttonStartTimer;
	@FXML
	private Button buttonResetTimer;
	@FXML
	private Button buttonExtraTime;
	@FXML 
	private Button startButtonSparring;
	@FXML 
	private Button stopButtonSparring;
	@FXML 
	private Button exitButtonSparring;

	@FXML
	private ChoiceBox<String> numberOfRoundsBoxSparing;

	@FXML
	private ChoiceBox<String> roundsLengthBoxSparing;
	
    // private class constant and some variables

    private static final Integer STARTTIME = 120;
    private Integer STARTTIME1;
    //private Timeline timeline
    //private Integer timeSeconds = STARTTIME;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ae -> doSomething()));  
	//timeline.setCycleCount(Animation.INDEFINITE);

	/*
	 * @FXML private void initialize() {
	 * 
	 * numberOfRoundsBoxSparing.setValue("3");
	 * numberOfRoundsBoxSparing.setItems(numberOfRoundsList);
	 * 
	 * roundsLengthBoxSparing.setValue("02:00");
	 * roundsLengthBoxSparing.setItems(roundsLengthList); }
	 * 
	 * 
	 */
    
	private int time = 120;
	private int seconds = time;
	private int rounds = 2;
	private int round = 1;
	private int rest = 30;
	private int secondsRest = rest;
	
	private int roundsSet = 2;
	private int roundSet = 1;
	private int roundlengthSet = 120;
	private int restSet = 30;

	@FXML 
	private void handleButtonStartTimer(ActionEvent e) {
		//int r = 120;
		//countDown(r);
		buttonExtraTime.setDisable(true);
		buttonStartTimer.setDisable(true);
		buttonStopTimer.setDisable(false);
		System.out.println("StartTimer");
		timeline.setCycleCount(Animation.INDEFINITE);
		// timeline.play();
		testTimer();
	}

	
	private void testTimer() {
		//System.out.println(timeline);
		if (timeline!=null) {
			//timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		} else {		
			//System.out.println(timeline);
			//this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), ae -> doSomething())); 
			//timeline.setCycleCount(Animation.INDEFINITE);
			//timeline.play();
	}}	
	private void doSomething(){

		///System.out.println("Rounds: " + rounds);

		System.out.println("time: "+ time + " seconds: " + seconds + "rounds left: " + rounds + " rest: " + rest + " secondsRest: " + secondsRest);
		if (time>0) {
			///System.out.println("Czas>0");
			if (extraBool!= true){
				labelRound.setText("Round " + round);
			}
			display(time);
			time--;
		}
		else if (time==0){
			///System.out.println("time==0");
			display(time);
			time--;
			rounds--;
			System.out.println("seconds: " + seconds+ "rounds left: " + rounds);
			rest=secondsRest;
			updateScore();
		}
		else if (rounds>0 && rest>0){
			disableWarningAndPenaltyB();
			///System.out.println("rest>0");
			labelRound.setText("Rest");
			display(rest);
			rest--;
		}
		else if (rounds>=0 && rest==0) {
			///System.out.println("rest==0");
				labelRound.setText("Rest");
				display(rest);
				rest--;
				time=seconds;
				round++;
				// RESET THE REFEREES RESULT'S AND RESULT
				resetTemporaryResults();
				resetWarningAndPenalty();
				enableWarningAndPenaltyB();
		}
		else {
			///System.out.println("else Time line stop");
			timeline.stop();
			buttonStopTimer.setDisable(true);
			checkDraw();
			enableExtraTime();
			extraBool = false;
		}
	}
	

	/*MERGE CONFLICT
	 * public void display (int time) {
		int counter = time;*/

	private void updateScore() {
		if (rblue> rred) {
			givePoint("blue");
		} else if (rblue< rred){
			givePoint("red");
		} else {
			givePoint("blue");
			givePoint("red");
		}
		
	}
	
	private void givePoint(String f) {
		if (f.equals("blue")) {
			int blueS = Integer.parseInt(blueScore.getText());
			blueS++;
			blueScore.setText(String.valueOf(blueS));
		} else {
			int redS = Integer.parseInt(redScore.getText());
			redS++;
			redScore.setText(String.valueOf(redS));
		}
	}
	
	private void checkDraw() {
		int blueS = Integer.parseInt(blueScore.getText());
		int redS = Integer.parseInt(redScore.getText());
		///System.out.println("BlueS: " + blueS + " RedS: " + redS);
		if (blueS==redS){
			drawBool = true;
		}
		///System.out.println("DrawB: " + drawBool);
	}
	
	private void resetTemporaryResults(){

			labelR1BlueScore.setText("0");
			labelR2BlueScore.setText("0");
			labelR3BlueScore.setText("0");
			labelR4BlueScore.setText("0");
			labelR4RedScore.setText("0");
			labelR1RedScore.setText("0");
			labelR2RedScore.setText("0");
			labelR3RedScore.setText("0");
			
			updateResult();
	}
	
	private void enableExtraTime () {
		if(drawBool){
			buttonExtraTime.setDisable(false);
		}
	}
	
	


	public void display (int czas) {
		int counter = czas;

			int minutes = counter / 60;
			int seconds = counter % 60;
			if (counter >= 1) {
				if (seconds >= 10) {
					labelRoundTime.setText(String.valueOf("0" + minutes + ":" + seconds));
					roundLength = counter;
				} else {
					labelRoundTime.setText(String.valueOf("0" + minutes + ":0" + seconds));
					roundLength = counter;
				}
			} else if (counter == 0) {
				labelRoundTime.setText(String.valueOf("0" + minutes + ":0" + seconds));
				roundLength = counter;
			}

		}
	/*
	///// TEST TIMER 2 //////////////////////////////
	public void testTimer2() {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds = STARTTIME;
 
        // update timerLabel
        labelRoundTime.setText(timeSeconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), new EventHandler() {
                    // KeyFrame event handler
                    public void handle(ActionEvent event) {
                        timeSeconds--;
                        // update timerLabel
                        labelRoundTime.setText(
                              timeSeconds.toString());
                        if (timeSeconds <= 0) {
                            timeline.stop();
                        }
                      }
                }));
        timeline.playFromStart();
    }
	*/
	// Make timeSeconds a Property
	//private IntegerProperty timeSeconds1 =
	//        new SimpleIntegerProperty(STARTTIME);
	/*
	private void odliczaj() {
	    labelRoundTime.textProperty().bind(timeSeconds1.asString());
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds1.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                new KeyValue(timeSeconds1, 0)));
        timeline.playFromStart();
    }
    */
	/*
	private void odliczaj(){
		final Timeline timeline = new Timeline();
		 timeline.setCycleCount(2);
		 timeline.setAutoReverse(true);
		 timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
		   new KeyValue (node.translateXProperty(), 25)));
		 timeline.play();
	}

	////////////// //////////////// RESET///////////////////////////////////////////////77
*/	@FXML 
	private void handleButtonResetTimer (ActionEvent e){
		pauseAction();
		time = roundlengthSet;
		rounds = roundsSet;
		round = roundSet;
		rest = restSet;
		resetTemporaryResults();
		resetWarningAndPenalty();
		resetBlueRedScore();
		buttonExtraTime.setDisable(true);
		buttonStartTimer.setDisable(false);
		buttonStopTimer.setDisable(true);
		setLabelText(1);
		display(time);
		System.out.println("RESET time: " + time + "roundlengthSet: " + roundlengthSet + "rounds: "+rounds + "roundsSet: " + roundsSet );
	
	}
	@FXML 
	private void handleButtonStopTimer(ActionEvent e) {
		pauseAction();
		System.out.println("Stop Timer");
	}
	
	private void pauseAction () {
		timeline.pause();
		buttonStopTimer.setDisable(true);
		buttonStartTimer.setDisable(false);
	}

	@FXML
	void handleBackButtonAction(ActionEvent e) throws IOException {
		boolean quitWindow = ConfirmationBox.show("Do you really want to go back to the main menu? All the information will be lost!", "Confirm Exit", "Yes", "No");
		
		if (quitWindow) {
			if (serverOnBool) {
				TkdServer.StopServer();
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
		}
	}
	
	@FXML
	void handleListView(ActionEvent event) {

	}

	@FXML
	void handleExitButtonAction(ActionEvent event) {
		boolean quitWindow = ConfirmationBox.show("Do you really want to exit? All the information will be lost!", "Confirm Exit", "Yes", "No");
		
		if (quitWindow) {
			if (serverOnBool) {
				serverOnBool = false;
				ScoringTul.StopServer();
			}
			Stage stage = (Stage) exitButtonSparring.getScene().getWindow();
			stage.close();
		} else {
			
		}
		

	}

	@FXML
	void handleResetButtonAction(ActionEvent event) {

	}

	@FXML
	void handleStartButtonAction(ActionEvent event) {
		startButtonSparring.setStyle("-fx-base: #32cd32");
		stopButtonSparring.setStyle("-fx-base: #d0d0d0");
		TkdServer.StartServer();
		TkdServer.subscribe(this);	
		serverOnBool = true;

	}

	@FXML
	void handleStopButtonAction(ActionEvent event) {
		stopButtonSparring.setStyle("-fx-base: #ff0000");
		startButtonSparring.setStyle("-fx-base: #d0d0d0");
		TkdServer.StopServer();
		serverOnBool = false;
	}

	@FXML
	private void handleMinusWarningBlueButtonSparing(ActionEvent event) {
		System.out.println("handleMinusWarningBlueButtonSparing");
		int warningsBlue = Integer.parseInt(labelWarningBlue.getText());
		if (warningsBlue > 1) {
			warningsBlue--;
			String warningBlueUpdated = Integer.toString(warningsBlue);
			labelWarningBlue.setText(warningBlueUpdated);
			if ((warningsBlue - 2) % 3 == 0) {
				plusPoint("Blue");
			}

		} else if (warningsBlue == 1) {
			warningsBlue--;
			String warningBlueUpdated = Integer.toString(warningsBlue);
			labelWarningBlue.setText(warningBlueUpdated);
			minusWarningBlueButtonSparing.setDisable(true);
			if ((warningsBlue - 2) % 3 == 0) {
				plusPoint("Blue");
			}
		}
		updateResult();

	}

	@FXML
	private void handlePlusWarningBlueButtonSparing(ActionEvent event) {
		System.out.println("handlePlusWarningBlueButtonSparing");
		int warningsBlue = Integer.parseInt(labelWarningBlue.getText());
		if (warningsBlue == 0) {
			warningsBlue++;
			String warningBlueUpdated = Integer.toString(warningsBlue);
			labelWarningBlue.setText(warningBlueUpdated);
			minusWarningBlueButtonSparing.setDisable(false);

		} else {
			warningsBlue++;
			String warningBlueUpdated = Integer.toString(warningsBlue);
			labelWarningBlue.setText(warningBlueUpdated);
			if (warningsBlue % 3 == 0) {
				minusPoint("Blue");
			}
		}
		updateResult();
	}

	private void minusPoint(String color) {
		if (color.equalsIgnoreCase("Blue")) {

			int r1BlueScore = Integer.parseInt(labelR1BlueScore.getText());
			r1BlueScore--;
			String r1BlueScoreUpdated = Integer.toString(r1BlueScore);
			labelR1BlueScore.setText(r1BlueScoreUpdated);

			int r2BlueScore = Integer.parseInt(labelR2BlueScore.getText());
			r2BlueScore--;
			String r2BlueScoreUpdated = Integer.toString(r2BlueScore);
			labelR2BlueScore.setText(r2BlueScoreUpdated);

			int r3BlueScore = Integer.parseInt(labelR3BlueScore.getText());
			r3BlueScore--;
			String r3BlueScoreUpdated = Integer.toString(r3BlueScore);
			labelR3BlueScore.setText(r3BlueScoreUpdated);

			int r4BlueScore = Integer.parseInt(labelR4BlueScore.getText());
			r4BlueScore--;
			String r4BlueScoreUpdated = Integer.toString(r4BlueScore);
			labelR4BlueScore.setText(r4BlueScoreUpdated);

		} else {
			int r1RedScore = Integer.parseInt(labelR1RedScore.getText());
			r1RedScore--;
			String r1RedScoreUpdated = Integer.toString(r1RedScore);
			labelR1RedScore.setText(r1RedScoreUpdated);

			int r2RedScore = Integer.parseInt(labelR2RedScore.getText());
			r2RedScore--;
			String r2RedScoreUpdated = Integer.toString(r2RedScore);
			labelR2RedScore.setText(r2RedScoreUpdated);

			int r3RedScore = Integer.parseInt(labelR3RedScore.getText());
			r3RedScore--;
			String r3RedScoreUpdated = Integer.toString(r3RedScore);
			labelR3RedScore.setText(r3RedScoreUpdated);

			int r4RedScore = Integer.parseInt(labelR4RedScore.getText());
			r4RedScore--;
			String r4RedScoreUpdated = Integer.toString(r4RedScore);
			labelR4RedScore.setText(r4RedScoreUpdated);

		}

	}

	private void plusPoint(String color) {
		if (color.equalsIgnoreCase("Blue")) {

			int r1BlueScore = Integer.parseInt(labelR1BlueScore.getText());
			r1BlueScore++;
			String r1BlueScoreUpdated = Integer.toString(r1BlueScore);
			labelR1BlueScore.setText(r1BlueScoreUpdated);

			int r2BlueScore = Integer.parseInt(labelR2BlueScore.getText());
			r2BlueScore++;
			String r2BlueScoreUpdated = Integer.toString(r2BlueScore);
			labelR2BlueScore.setText(r2BlueScoreUpdated);

			int r3BlueScore = Integer.parseInt(labelR3BlueScore.getText());
			r3BlueScore++;
			String r3BlueScoreUpdated = Integer.toString(r3BlueScore);
			labelR3BlueScore.setText(r3BlueScoreUpdated);

			int r4BlueScore = Integer.parseInt(labelR4BlueScore.getText());
			r4BlueScore++;
			String r4BlueScoreUpdated = Integer.toString(r4BlueScore);
			labelR4BlueScore.setText(r4BlueScoreUpdated);

		} else {
			int r1RedScore = Integer.parseInt(labelR1RedScore.getText());
			r1RedScore++;
			String r1RedScoreUpdated = Integer.toString(r1RedScore);
			labelR1RedScore.setText(r1RedScoreUpdated);

			int r2RedScore = Integer.parseInt(labelR2RedScore.getText());
			r2RedScore++;
			String r2RedScoreUpdated = Integer.toString(r2RedScore);
			labelR2RedScore.setText(r2RedScoreUpdated);

			int r3RedScore = Integer.parseInt(labelR3RedScore.getText());
			r3RedScore++;
			String r3RedScoreUpdated = Integer.toString(r3RedScore);
			labelR3RedScore.setText(r3RedScoreUpdated);

			int r4RedScore = Integer.parseInt(labelR4RedScore.getText());
			r4RedScore++;
			String r4RedScoreUpdated = Integer.toString(r4RedScore);
			labelR4RedScore.setText(r4RedScoreUpdated);

		}

	}

	@FXML
	private void handleMinusPenaltyBlueButtonSparing(ActionEvent event) {
		System.out.println("handleMinusPenaltyBlueButtonSparing");
		int penaltyBlue = Integer.parseInt(labelPenaltyBlue.getText());
		if (penaltyBlue > 1) {
			penaltyBlue--;
			String penaltyBlueUpdated = Integer.toString(penaltyBlue);
			labelPenaltyBlue.setText(penaltyBlueUpdated);
		} else if (penaltyBlue == 1) {
			penaltyBlue--;
			String penaltyBlueUpdated = Integer.toString(penaltyBlue);
			labelPenaltyBlue.setText(penaltyBlueUpdated);
			minusPenaltyBlueButtonSparing.setDisable(true);
		}
		plusPoint("Blue");
		updateResult();
	}

	@FXML
	private void handlePlusPenaltyBlueButtonSparing(ActionEvent event) {
		System.out.println("handlePlusWarningBlueButtonSparing");
		int penaltyBlue = Integer.parseInt(labelPenaltyBlue.getText());
		if (penaltyBlue == 0) {
			penaltyBlue++;
			String penaltyBlueUpdated = Integer.toString(penaltyBlue);
			labelPenaltyBlue.setText(penaltyBlueUpdated);
			minusPenaltyBlueButtonSparing.setDisable(false);
		} else {
			penaltyBlue++;
			String penaltyBlueUpdated = Integer.toString(penaltyBlue);
			labelPenaltyBlue.setText(penaltyBlueUpdated);
		}
		minusPoint("Blue");
		updateResult();
	}

	@FXML
	private void handleMinusWarningRedButtonSparing(ActionEvent event) {
		System.out.println("handleMinusWarningRedButtonSparing");
		int warningsRed = Integer.parseInt(labelWarningRed.getText());
		if (warningsRed > 1) {
			warningsRed--;
			String warningsRedUpdated = Integer.toString(warningsRed);
			labelWarningRed.setText(warningsRedUpdated);
			if ((warningsRed - 2) % 3 == 0) {
				plusPoint("Red");
			}
		} else if (warningsRed == 1) {
			warningsRed--;
			String warningsRedUpdated = Integer.toString(warningsRed);
			labelWarningRed.setText(warningsRedUpdated);
			minusWarningRedButtonSparing.setDisable(true);
			if ((warningsRed - 2) % 3 == 0) {
				plusPoint("Red");
			}
		}
		updateResult();
	}

	@FXML
	private void handlePlusWarningRedButtonSparing(ActionEvent event) {
		System.out.println("handlePlusWarningRedButtonSparing");
		int warningsRed = Integer.parseInt(labelWarningRed.getText());
		if (warningsRed == 0) {
			warningsRed++;
			String warningRedUpdated = Integer.toString(warningsRed);
			labelWarningRed.setText(warningRedUpdated);
			minusWarningRedButtonSparing.setDisable(false);
		} else {
			warningsRed++;
			String warningRedUpdated = Integer.toString(warningsRed);
			labelWarningRed.setText(warningRedUpdated);
			if (warningsRed % 3 == 0) {
				minusPoint("Red");
			}
		}
		updateResult();
	}

	@FXML
	private void handleMinusPenaltyRedButtonSparing(ActionEvent event) {
		System.out.println("handleMinusPenaltyRedButtonSparing");
		int penaltyRed = Integer.parseInt(labelPenaltyRed.getText());
		if (penaltyRed > 1) {
			penaltyRed--;
			String penaltyRedUpdated = Integer.toString(penaltyRed);
			labelPenaltyRed.setText(penaltyRedUpdated);
		} else if (penaltyRed == 1) {
			penaltyRed--;
			String penaltyRedUpdated = Integer.toString(penaltyRed);
			labelPenaltyRed.setText(penaltyRedUpdated);
			minusPenaltyRedButtonSparing.setDisable(true);
		}
		plusPoint("Red");
		updateResult();
	}

	@FXML
	private void handlePlusPenaltyRedButtonSparing(ActionEvent event) {
		System.out.println("handlePlusWarningRedButtonSparing");
		int penaltyRed = Integer.parseInt(labelPenaltyRed.getText());
		if (penaltyRed == 0) {
			penaltyRed++;
			String penaltyRedUpdated = Integer.toString(penaltyRed);
			labelPenaltyRed.setText(penaltyRedUpdated);
			minusPenaltyRedButtonSparing.setDisable(false);
		} else {
			penaltyRed++;
			String penaltyRedUpdated = Integer.toString(penaltyRed);
			labelPenaltyRed.setText(penaltyRedUpdated);
		}
		minusPoint("Red");
		updateResult();

	}

	//////// UPDATE RESULT /////////////////////////////////////////
	private void updateResult() {

		int r1BlueScore = Integer.parseInt(labelR1BlueScore.getText());
		int r1RedScore = Integer.parseInt(labelR1RedScore.getText());
		int r2BlueScore = Integer.parseInt(labelR2BlueScore.getText());
		int r2RedScore = Integer.parseInt(labelR2RedScore.getText());
		int r3BlueScore = Integer.parseInt(labelR3BlueScore.getText());
		int r3RedScore = Integer.parseInt(labelR3RedScore.getText());
		int r4BlueScore = Integer.parseInt(labelR4BlueScore.getText());
		int r4RedScore = Integer.parseInt(labelR4RedScore.getText());

		rblue = 0;
		rred = 0;
		draw = 0;

		if (r1BlueScore > r1RedScore) {
			rblue++;
		} else if (r1BlueScore < r1RedScore) {
			rred++;
		} else {
			draw++;
		}
		if (r2BlueScore > r2RedScore) {
			rblue++;
		} else if (r2BlueScore < r2RedScore) {
			rred++;
		} else {
			draw++;
		}
		if (r3BlueScore > r3RedScore) {
			rblue++;
		} else if (r3BlueScore < r3RedScore) {
			rred++;
		} else {
			draw++;
		}
		if (r4BlueScore > r4RedScore) {
			rblue++;
		} else if (r4BlueScore < r4RedScore) {
			rred++;
		} else {
			draw++;
		}
		/// System.out.println(rblue + "-" + draw + "-" + rred);
		/**
		 * labelBlueResult.setText(Integer.toString(rblue));
		 * labelRedResult.setText(Integer.toString(rred));
		 * labelDrawResult.setText(Integer.toString(draw));
		 *///
		String bresult = Integer.toString(rblue);
		labelBlueResult.setText(bresult);

		String rresult = Integer.toString(rred);
		labelRedResult.setText(rresult);

		String rdraw = Integer.toString(draw);
		labelDrawResult.setText(rdraw);

	}

	//////// WHICH ROUND ////////////////////////
	private void whichRound(int roundNumber) {
		if (roundNumber == 4) {
			labelRound.setText("Rest ");
		} else {
			labelRound.setText("Round: " + roundNumber);
		}
	}

	/////// ROUND TIME /////////////////////////
	private void roundTime(int rlength) {
		if (rlength == 30) {
			labelRoundTime.setText("00:30");
			this.roundLength = 30;
			this.roundLengthSet = 30;
		} else if (rlength == 1) {
			labelRoundTime.setText("01:00");
			this.roundLength = 60;
			this.roundLengthSet = 60;
		} else if (rlength == 90) {
			labelRoundTime.setText("01:30");
			this.roundLength = 90;
			this.roundLengthSet = 90;
		} else if (rlength == 2) {
			labelRoundTime.setText("02:00");
			this.roundLength = 120;
			this.roundLengthSet = 120;
		} else if (rlength == 3) {
			labelRoundTime.setText("03:00");
			this.roundLength = 180;
			this.roundLengthSet = 180;
		}
	}

	/////// COUNTDOWN ///////////////////
	private void countDown(int roundLength) {
		int rl = roundLength;
		//timeLeft.setEnabled(false);
		//countdownStart.setEnabled(false);
		//countdownStop.setEnabled(true);
		//countdownReset.setEnabled(false);
		//TimeClass tc = new TimeClass(rl);
		//timer = new Timer(1000, tc);
		//timer.start();

	}

	


	///////// COUNTDOWN STOP //////////////////////////////
	private void countDownStop() {
		timer.stop();
		//timeLeft.setEnabled(true);
		//countdownStart.setEnabled(true);
		//countdownStop.setEnabled(false);
		//countdownReset.setEnabled(true);
	}

	///////// RESET COUNTDOWN //////////////////////
	private void reset() {
		if (roundLengthSet == 30) {
			roundTime(30);
		} else if (roundLengthSet == 60) {
			roundTime(1);
		} else if (roundLengthSet == 90) {
			roundTime(90);
		} else if (roundLengthSet == 120) {
			roundTime(2);
		} else if (roundLengthSet == 180) {
			roundTime(3);
		}
		//countdownReset.setEnabled(false);
		//countdownStart.setEnabled(true);
	}
	
	@FXML
	private void handleNewSparingButtonAction(ActionEvent e) throws IOException {
		
		pauseAction();
		String editorResults = ListBox.show("Noumber of Rounds:", "Sparring Editor", "Ready", "Cancel");

		if (editorResults == null) {
			
		} else {
			//System.out.println("TRUE");
			//System.out.println("Sparing controler received from listBox: " + editorResults);
			setLabelText(1);
			String roundsN = editorResults.substring(0, 1);
			//System.out.println("roundsN String: " + roundsN );
			setRoundsNumber(roundsN);
			String roundsL = editorResults.substring(1, 6);
			//System.out.println("roundsL String: " + roundsL);
			setRoundsLength(roundsL);
			String restsL = editorResults.substring(6, 11);
			//System.out.println("restL String: " + restsL);
			setRestsLength(restsL);
			//doExit();
			resetTemporaryResults();
			resetBlueRedScore();
			resetWarningAndPenalty();
			updateResult();
		}
	}
	
	
	
	@FXML
	private void handleExtraTimeButtonAction (ActionEvent e) {
		String extraTime = ExtraTimeBox.show("Extra Time:", "Extra Time Editor", "Ready", "Cancel");
		
		if (extraTime== null){
			
		} else {
			//System.out.println("Sparing controler received from ExtraTimeBox: " + extraTime);
			setLabelText(2);
			setRoundsNumber("1");
			setRoundsLength(extraTime);
			resetTemporaryResults();
			resetWarningAndPenalty();
			buttonStartTimer.setDisable(false);
		}
	}
	
	private void resetBlueRedScore () {
		blueScore.setText("0");
		redScore.setText("0");
	}
	
	private void resetWarningAndPenalty() {
		
		labelWarningBlue.setText("0");
		labelPenaltyBlue.setText("0");
		labelWarningRed.setText("0");
		labelPenaltyRed.setText("0");
	}
	
	private void setLabelText (int x){
		if (x==1)
		{
			labelRound.setText("Round 1");
		}else {
			labelRound.setText("Extra Time");
			extraBool = true;
		}
	}
	
	private void setRoundsNumber(String roundsN) {
		//labelRound.setText("Round 1");
		switch (roundsN) {
		case "1":
			rounds = 1;
			roundsSet = 1;
			round = 1;
			roundSet = 1;
			break;
		case "2":
			rounds = 2;
			roundsSet =2;
			round = 1;
			roundSet =1;
			break;
		case "3":
			rounds = 3;
			roundsSet = 3;
			round = 1;
			roundSet =1;
		//default:
		//	rounds = 2;
		//	round = 1;
		}
		System.out.println("SET ROUNDS NUMBER - rounds: " + rounds);
	}


	private void setRestsLength(String restsL) {
		// TODO Auto-generated method stub

		switch (restsL) {
		case "00:30":
			rest = 30;
			restSet = 30;
			secondsRest = 30;
			break;
		case "00:45":
			rest = 45;
			restSet = 45;
			secondsRest = 45;
			break;
		case "01:00":
			rest = 60;
			restSet = 60;
			secondsRest = 60;
			break;
		case "01:30":
			rest = 90;
			restSet = 90;
			secondsRest = 90;
			break;
		case "02:00":
			rest = 120;
			restSet = 120;
			secondsRest = 120;
			break;
		case "02:30":
			rest = 150;
			secondsRest = 150;
			break;
		case "03:00":
			rest = 180;
			restSet = 180;
			secondsRest = 180;
			break;
		default:
			rest = 30; 
			//////////////////////// ???
			secondsRest = 30;
			break;
		}
	}


	public void setRoundsLength (String roundsL) {
	
		labelRoundTime.setText(roundsL);
		switch (roundsL) {
		case "00:30":
			time = 30;
			roundlengthSet = 30;
			seconds = 30;
			break;
		case "00:45":
			time = 45;
			roundlengthSet = 45;
			seconds = 45;
			break;
		case "01:00":
			time = 60;
			roundlengthSet = 60;
			seconds = 60;
			break;
		case "01:30":
			time = 90;
			roundlengthSet = 90;
			seconds = 90;
			break;
		case "02:00":
			time = 120;
			roundlengthSet = 120;
			seconds = 120;
			break;
		case "02:30":
			time = 150;
			roundlengthSet = 150;
			seconds = 150;
			break;
		case "03:00":
			time = 180;
			roundlengthSet = 180;
			seconds = 180;
			break;
		default:
			time = 120; 
			roundlengthSet = 120;
			seconds = 120;
			break;
		}

	System.out.println("time: " + time);	
	}	
	
	public void updatePoints(AtomicInteger points1, AtomicInteger points2, String refereeId) 
	{						
		backgroundthread = new Service<Void>() 
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
                               	if (refereeId.equals(mReferee1ID)) 
                               	{        							
                            		SimpleIntegerProperty nPoints1 = new SimpleIntegerProperty();
                            		SimpleIntegerProperty nPoints2 = new SimpleIntegerProperty();
                            		nPoints1.set(points1.get());
                            		nPoints2.set(points2.get());        						        						       					
        							labelR1RedScore.textProperty().bind(nPoints1.asString());
        							labelR1BlueScore.textProperty().bind(nPoints2.asString());        									
        						}        						
        						if (refereeId.equals(mReferee2ID))
        						{        						 
        							SimpleIntegerProperty nPoints1 = new SimpleIntegerProperty();
                            		SimpleIntegerProperty nPoints2 = new SimpleIntegerProperty();
                            		nPoints1.set(points1.get());
                            		nPoints2.set(points2.get());        						        						       					
        							labelR2BlueScore.textProperty().bind(nPoints1.asString());
        							labelR2RedScore.textProperty().bind(nPoints2.asString());  
        						}
        						if (refereeId.equals(mReferee3ID)) 
        						{        						
        							SimpleIntegerProperty nPoints1 = new SimpleIntegerProperty();
                            		SimpleIntegerProperty nPoints2 = new SimpleIntegerProperty();
                            		nPoints1.set(points1.get());
                            		nPoints2.set(points2.get());        						        						       					
        							labelR3BlueScore.textProperty().bind(nPoints1.asString());
        							labelR3RedScore.textProperty().bind(nPoints2.asString());  
        						}
        						if (refereeId.equals(mReferee4ID)) 
        						{        						
        							SimpleIntegerProperty nPoints1 = new SimpleIntegerProperty();
                            		SimpleIntegerProperty nPoints2 = new SimpleIntegerProperty();
                            		nPoints1.set(points1.get());
                            		nPoints2.set(points2.get());        						        						       					
        							labelR4BlueScore.textProperty().bind(nPoints1.asString());
        							labelR4RedScore.textProperty().bind(nPoints2.asString());  
        						}
        					});							
						return null;
						}
					};
				}
			};			
		backgroundthread.restart();
	}

	private void disableWarningAndPenaltyB () {
		minusWarningBlueButtonSparing.setDisable(true);
		plusWarningBlueButtonSparing.setDisable(true);
		minusPenaltyBlueButtonSparing.setDisable(true);
		plusPenaltyBlueButtonSparing.setDisable(true);
		minusWarningRedButtonSparing.setDisable(true);
		plusWarningRedButtonSparing.setDisable(true);
		minusPenaltyRedButtonSparing.setDisable(true);
		plusPenaltyRedButtonSparing.setDisable(true);
	}
	private void enableWarningAndPenaltyB () {
		minusWarningBlueButtonSparing.setDisable(false);
		plusWarningBlueButtonSparing.setDisable(false);
		minusPenaltyBlueButtonSparing.setDisable(false);
		plusPenaltyBlueButtonSparing.setDisable(false);
		minusWarningRedButtonSparing.setDisable(false);
		plusWarningRedButtonSparing.setDisable(false);
		minusPenaltyRedButtonSparing.setDisable(false);
		plusPenaltyRedButtonSparing.setDisable(false);
	}

	public static void exitSparring() {
		boolean quitWindow = ConfirmationBox.show("Do you really want to exit? All the information will be lost!", "Confirm Exit", "Yes", "No");
		
		if (quitWindow) {
			if (serverOnBool) {
				serverOnBool = false;
				TkdServer.StopServer();
			}
			Platform.exit();
		} else {
			
		}
	}
}
