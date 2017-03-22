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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.TkdServer;
import model.TkdServer.PointListener;

public class SparingControler implements Initializable, PointListener {
	
	@FXML
	private ListView<String> listView;
	
	static String mReferee1ID = "referee1";
	static String mReferee2ID = "referee2";
	static String mReferee3ID = "referee3";
	static String mReferee4ID = "referee4";

	ObservableList<String> items =FXCollections.observableArrayList (getIps());
	
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
	private int rounds = 3;
	private int round = 1;
	private int rest = 5;
	private int secondsRest = rest;
	@FXML 
	private void handleButtonStartTimer(ActionEvent e) {
		//int r = 120;
		//countDown(r);
		
		System.out.println("StartTimer");
		timeline.setCycleCount(Animation.INDEFINITE);
		// timeline.play();
		testTimer();
	}

	
	private void testTimer() {
		System.out.println(timeline);
		if (timeline!=null) {
			//timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		} else {		
			System.out.println(timeline);
			//this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), ae -> doSomething())); 
			//timeline.setCycleCount(Animation.INDEFINITE);
			//timeline.play();
	}}	
	private void doSomething(){
		System.out.println("czas: "+ time + " seconds: " + seconds + "rounds left: " + rounds + " rest: " + rest + " secondsRest: " + secondsRest);
		if (time>0) {
			labelRound.setText("Round " + round);
			display(time);
			time--;
		}
		else if (time==0){
			display(time);
			time--;
			rounds--;
			System.out.println("seconds: " + seconds+ "rounds left: " + rounds);
			rest=secondsRest;
		}
		else if (rounds>0 && rest>0){
			labelRound.setText("Rest");
			display(rest);
			rest--;
		}
		else if (rounds>0 && rest==0) {
				labelRound.setText("Rest");
				display(rest);
				rest--;
				time=seconds;
				round++;
		}
		else {
			timeline.stop();
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

*/	
	@FXML 
	private void handleButtonStopTimer(ActionEvent e) {
		timeline.pause();
	
		System.out.println("Stop Timer");
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
	void handleListView(ActionEvent event) {

	}
	@FXML
	void handleExitButtonAction(ActionEvent event) {

	}

	@FXML
	void handleResetButtonAction(ActionEvent event) {

	}

	@FXML
	void handleStartButtonAction(ActionEvent event) {
		TkdServer.StartServer();
		TkdServer.subscribe(this);
	}

	@FXML
	void handleStopButtonAction(ActionEvent event) {
		TkdServer.StopServer();
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

	private void updateResult() {

		int r1BlueScore = Integer.parseInt(labelR1BlueScore.getText());
		int r1RedScore = Integer.parseInt(labelR1RedScore.getText());
		int r2BlueScore = Integer.parseInt(labelR2BlueScore.getText());
		int r2RedScore = Integer.parseInt(labelR2RedScore.getText());
		int r3BlueScore = Integer.parseInt(labelR3BlueScore.getText());
		int r3RedScore = Integer.parseInt(labelR3RedScore.getText());
		int r4BlueScore = Integer.parseInt(labelR4BlueScore.getText());
		int r4RedScore = Integer.parseInt(labelR4RedScore.getText());

		int rblue = 0;
		int rred = 0;
		int draw = 0;

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
		System.out.println(rblue + "-" + draw + "-" + rred);
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

		String editorResults = ListBox.show("Number of Rounds:", "Sparring Editor", "Ready", "Cancel");

		if (editorResults == null) {
			
		} else {
			System.out.println("TRUE");
			System.out.println("Sparing controler received from listBox: " + editorResults);
			
			String roundsN = editorResults.substring(0, 1);
			System.out.println("roundsN String: " + roundsN );
			setRoundsNumber(roundsN);
			String roundsL = editorResults.substring(1, 6);
			System.out.println("roundsL String: " + roundsL);
			setRoundsLength(roundsL);
			String restsL = editorResults.substring(6, 11);
			System.out.println("restL String: " + restsL);
			setRestsLength(restsL);
			//doExit();
		}
	}
	
	private void setRoundsNumber(String roundsN) {
		labelRound.setText("Round 1");
		switch (roundsN) {
		case "1":
			rounds = 1;
			round = 1;
			break;
		case "2":
			rounds = 2;
			round = 1;
			break;
		case "3":
			rounds = 3;
			round = 1;
		default:
			rounds = 2;
			round = 1;
		}
		
	}


	private void setRestsLength(String restsL) {
		// TODO Auto-generated method stub
		switch (restsL) {
		case "00:30":
			rest = 30;
			secondsRest = 30;
			break;
		case "00:45":
			rest = 45;
			secondsRest = 45;
			break;
		case "01:00":
			rest = 60;
			secondsRest = 60;
			break;
		case "01:30":
			rest = 90;
			secondsRest = 90;
			break;
		case "02:00":
			rest = 120;
			secondsRest = 120;
			break;
		case "02:30":
			rest = 150;
			secondsRest = 150;
			break;
		case "03:00":
			rest = 180;
			secondsRest = 180;
			break;
		default:
			rest = 30; 
			secondsRest = 30;
			break;
		}
	}


	public void setRoundsLength (String roundsL) {
	
		labelRoundTime.setText(roundsL);
		switch (roundsL) {
		case "00:30":
			time = 30;
			seconds = 30;
			break;
		case "00:45":
			time = 45;
			seconds = 45;
			break;
		case "01:00":
			time = 60;
			seconds = 60;
			break;
		case "01:30":
			time = 90;
			seconds = 90;
			break;
		case "02:00":
			time = 120;
			seconds = 120;
			break;
		case "02:30":
			time = 150;
			seconds = 150;
			break;
		case "03:00":
			time = 180;
			seconds = 180;
			break;
		default:
			time = 120; 
			seconds = 120;
			break;
		}
	System.out.println("time: " + time);	
	}
	
//	public void resetPoints() {
//		TkdServer.resetPoints();
//		f1points.setText("0");
//		f2points.setText("0");
//		drawResult.setText("4");
//		f1WarningScore.setText("0");
//		f1Warnings = 0;
//		f2WarningScore.setText("0");
//		f2Warnings = 0;
//		f1PenaltyScore.setText("0");
//		f1Penalties = 0;
//		f2PenaltyScore.setText("0");
//		f2Penalties = 0;
//	}

	@Override
	public void updatePoints(AtomicInteger points1, AtomicInteger points2, String refereeId) {
		if (refereeId.equals(mReferee1ID)) {
			int nPoints1 = points1.get();
			int nPoints2 = points2.get();
			String f1update = Integer.toString(nPoints1);
			labelR1RedScore.setText(f1update);
			String f2update = Integer.toString(nPoints2);
			labelR1BlueScore.setText(f2update);			
		}
		if (refereeId.equals(mReferee2ID)) {
			int nPoints1 = points1.get();
			int nPoints2 = points2.get();
			String f1update = Integer.toString(nPoints1);
			labelR2RedScore.setText(f1update);
			String f2update = Integer.toString(nPoints2);
			labelR2BlueScore.setText(f2update);
		}
		if (refereeId.equals(mReferee3ID)) {
			int nPoints1 = points1.get();
			int nPoints2 = points2.get();
			String f1update = Integer.toString(nPoints1);
			labelR3RedScore.setText(f1update);
			String f2update = Integer.toString(nPoints2);
			labelR3BlueScore.setText(f2update);
		}
		if (refereeId.equals(mReferee4ID)) {
			int nPoints1 = points1.get();
			int nPoints2 = points2.get();
			String f1update = Integer.toString(nPoints1);
			labelR4RedScore.setText(f1update);
			String f2update = Integer.toString(nPoints2);
			labelR4BlueScore.setText(f2update);
		}		
	};

	
}
