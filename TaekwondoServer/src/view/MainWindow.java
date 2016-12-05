package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import model.TkdServer;
import model.TkdServer.PointListener;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements PointListener {
	private JButton buttonStart, buttonStop, buttonReset, buttonExit, buttonTotal, f1ButtonWarning, f2ButtonWarning,
			f1ButtonPenalty, f2ButtonPenalty, buttonStartTimer, buttonStopTimer, buttonResetTimer;
	private JLabel points, connection, f1Warning, f2Warning, f1Penalty, f2Penalty;
	private JLabel f1Warn, f2Warn, f1Pen, f2Pen, f1WarningScore, f2WarningScore, f1PenaltyScore, f2PenaltyScore;
	private LineBorder border;
	private int f1Warnings = 0;
	private int f2Warnings = 0;
	private int f1Penalties = 0;
	private int f2Penalties = 0;
	private JList<String> ips;
	static String mReferee1ID = "referee1";
	static String mReferee2ID = "referee2";
	static String mReferee3ID = "referee3";
	static String mReferee4ID = "referee4";
	private JLabel fighter1, f1points, r1Label, r2Label, r3Label, r4Label, draw, drawResult;
	private JLabel fighter2, f2points, r1ResultRed, r1ResultBlue, r2ResultRed, r2ResultBlue, r3ResultRed, r3ResultBlue,
			r4ResultRed, r4ResultBlue;

	// JMenu
	private JMenuBar menuBar;
	private JMenu data, about;
	private JMenu roundNumber, timeLeft, countdown;
	private JMenuItem roundOne, roundTwo, roundThree, rest, halfMinute, oneMinute, oneHalfMinute, twoMinutes, threeMinutes,
			countdownStart, countdownStop, countdownReset, taekwondoDevelopers, exitServer;

	// Timer & Round 1 JLabel
	private JLabel round, timeLabel;
	private int roundLength;
	private int roundLengthSet;
	private Timer timer;

	//////// CONSTRUCTOR ///////////////////
	public MainWindow() {
		initBasics();
		initComponents();
		addComponents();
		addListeners();
		setVisible(true);
	}

	/////// INIT BASICS /////////////////////
	private void initBasics() {
		setSize(1200, 650);
		setLocationRelativeTo(null);
		setTitle("Taekwondo Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	/////// INIT COMPONENTS ///////////////////////
	private void initComponents() {
		
		/// --- JMenuBar and MenuItems
		menuBar = new JMenuBar();
		data = new JMenu("Data");
		data.setMnemonic(KeyEvent.VK_D);
		roundNumber = new JMenu("Round");
		roundNumber.setMnemonic(KeyEvent.VK_R);
		timeLeft = new JMenu("Time");
		timeLeft.setMnemonic(KeyEvent.VK_T);
		countdown = new JMenu("Countdown");
		countdown.setMnemonic(KeyEvent.VK_C);

		roundOne = new JMenuItem("Round 1", KeyEvent.VK_1);
		roundTwo = new JMenuItem("Round 2", KeyEvent.VK_2);
		roundThree = new JMenuItem("Round 3", KeyEvent.VK_3);
		rest = new JMenuItem("Rest", KeyEvent.VK_E);

		halfMinute = new JMenuItem("00:30");
		halfMinute.setEnabled(true);
		oneMinute = new JMenuItem("01:00");
		oneMinute.setEnabled(true);
		oneHalfMinute = new JMenuItem("01:30");
		oneHalfMinute.setEnabled(true);
		twoMinutes = new JMenuItem("02:00");
		twoMinutes.setEnabled(true);
		threeMinutes = new JMenuItem("03:00");
		threeMinutes.setEnabled(true);

		countdownStart = new JMenuItem("Start", KeyEvent.VK_A);
		KeyStroke ctrlSKeyStroke = KeyStroke.getKeyStroke("control S");
		countdownStart.setAccelerator(ctrlSKeyStroke);

		countdownStop = new JMenuItem("Stop", KeyEvent.VK_O);
		KeyStroke ctrlXKeyStroke = KeyStroke.getKeyStroke("control X");
		countdownStop.setAccelerator(ctrlXKeyStroke);

		countdownReset = new JMenuItem("Reset", KeyEvent.VK_S);
		KeyStroke ctrlRKeyStroke = KeyStroke.getKeyStroke("control R");
		countdownReset.setAccelerator(ctrlRKeyStroke);
		
		
		exitServer = new JMenuItem("Exit", KeyEvent.VK_X);
		KeyStroke ctrlCKeyStroke = KeyStroke.getKeyStroke("control C");
		countdownReset.setAccelerator(ctrlCKeyStroke);

		
		about = new JMenu("About");
		about.setMnemonic(KeyEvent.VK_B);
		taekwondoDevelopers = new JMenuItem("Taekwondo Developers");
		taekwondoDevelopers.setMnemonic(KeyEvent.VK_V);
		

		/// --- ROUND 1 LABEL
		round = new JLabel("Round 1", JLabel.CENTER);
		round.setOpaque(true);
		round.setBackground(Color.black);
		round.setFont(new Font("Tahoma", Font.PLAIN, 56));
		round.setForeground(Color.yellow);

		/// --- TIME LABEL
		timeLabel = new JLabel("02:00", JLabel.RIGHT);
		this.roundLength = 120;
		this.roundLengthSet = 120;
		timeLabel.setOpaque(true);
		timeLabel.setBackground(Color.black);
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 56));
		timeLabel.setForeground(Color.yellow);

		
		points = new JLabel("Points");
		points.setBorder(border);
		
		buttonStart = new JButton("START");
		buttonStop = new JButton("STOP");
		buttonStop.setBackground(new Color(238, 44, 44));
		buttonReset = new JButton("RESET");
		buttonExit = new JButton("EXIT");
		buttonTotal = new JButton("GET TOTAL");
		
		buttonStartTimer = new JButton("Start Timer");
		buttonStartTimer.setFont(new Font("Tahoma", Font.BOLD, 24));
		buttonStopTimer = new JButton("Stop Timer");
		buttonStopTimer.setFont(new Font("Tahoma", Font.BOLD, 24));
		buttonResetTimer = new JButton("Reset");
		buttonResetTimer.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		f1ButtonWarning = new JButton("DEDUCT");
		f2ButtonWarning = new JButton("DEDUCT");
		f1ButtonPenalty = new JButton("DEDUCT");
		f2ButtonPenalty = new JButton("DEDUCT");

		ArrayList<String> ipsal = TkdServer.getAllIPs();
		String[] entries = ipsal.toArray(new String[ipsal.size()]);
		/// String[] entries = {"Test1", "Test2", "Test3", "Test4"};
		ips = new JList<String>(entries);

		fighter1 = new JLabel("F1", JLabel.RIGHT);
		fighter1.setOpaque(true);
		fighter1.setBackground(new Color(238, 44, 44));
		fighter1.setFont(new Font("Tahoma", Font.BOLD, 56));

		f1points = new JLabel("0", JLabel.CENTER);
		f1points.setOpaque(true);
		f1points.setBackground(new Color(238, 44, 44));
		f1points.setFont(new Font("Tahoma", Font.BOLD, 56));

		fighter2 = new JLabel("F2", JLabel.RIGHT);
		fighter2.setOpaque(true);
		fighter2.setBackground(new Color(0, 0, 205));
		fighter2.setFont(new Font("Tahoma", Font.BOLD, 56));

		f2points = new JLabel("0", JLabel.CENTER);
		f2points.setOpaque(true);
		f2points.setBackground(new Color(0, 0, 205));
		f2points.setFont(new Font("Tahoma", Font.BOLD, 56));

		draw = new JLabel("Draw", JLabel.CENTER);
		draw.setOpaque(true);
		draw.setBackground(Color.WHITE);
		draw.setFont(new Font("Tahoma", Font.BOLD, 56));

		drawResult = new JLabel("4", JLabel.CENTER);
		drawResult.setOpaque(true);
		drawResult.setBackground(Color.WHITE);
		drawResult.setFont(new Font("Tahoma", Font.BOLD, 56));

		connection = new JLabel("Connection:", JLabel.RIGHT);
		connection.setOpaque(true);
		connection.setBackground(Color.white);
		connection.setFont(new Font("Tahoma", Font.BOLD, 24));

		f1Warn = new JLabel("F1", JLabel.CENTER);
		f1Warn.setOpaque(true);
		f1Warn.setBackground(new Color(238, 44, 44));
		f1Warn.setFont(new Font("Tahoma", Font.BOLD, 24));

		f1Warning = new JLabel("Warnings", JLabel.CENTER);
		f1Warning.setOpaque(true);
		f1Warning.setBackground(new Color(238, 44, 44));
		f1Warning.setFont(new Font("Tahoma", Font.BOLD, 24));

		f2Warn = new JLabel("F2", JLabel.CENTER);
		f2Warn.setOpaque(true);
		f2Warn.setBackground(new Color(0, 0, 205));
		f2Warn.setFont(new Font("Tahoma", Font.BOLD, 24));

		f2Warning = new JLabel("Warnings", JLabel.CENTER);
		f2Warning.setOpaque(true);
		f2Warning.setBackground(new Color(0, 0, 205));
		f2Warning.setFont(new Font("Tahoma", Font.BOLD, 24));

		f1WarningScore = new JLabel("0", JLabel.CENTER);
		f1WarningScore.setOpaque(true);
		f1WarningScore.setBackground(Color.WHITE);
		f1WarningScore.setFont(new Font("Tahoma", Font.BOLD, 24));

		f2WarningScore = new JLabel("0", JLabel.CENTER);
		f2WarningScore.setOpaque(true);
		f2WarningScore.setBackground(Color.WHITE);
		f2WarningScore.setFont(new Font("Tahoma", Font.BOLD, 24));

		f1Pen = new JLabel("F1", JLabel.CENTER);
		f1Pen.setOpaque(true);
		f1Pen.setBackground(new Color(238, 44, 44));
		f1Pen.setFont(new Font("Tahoma", Font.BOLD, 24));

		f1Penalty = new JLabel("Penalty", JLabel.CENTER);
		f1Penalty.setOpaque(true);
		f1Penalty.setBackground(new Color(238, 44, 44));
		f1Penalty.setFont(new Font("Tahoma", Font.BOLD, 24));

		f2Pen = new JLabel("F2", JLabel.CENTER);
		f2Pen.setOpaque(true);
		f2Pen.setBackground(new Color(0, 0, 205));
		f2Pen.setFont(new Font("Tahoma", Font.BOLD, 24));

		f2Penalty = new JLabel("Penalty", JLabel.CENTER);
		f2Penalty.setOpaque(true);
		f2Penalty.setBackground(new Color(0, 0, 205));
		f2Penalty.setFont(new Font("Tahoma", Font.BOLD, 24));

		f1PenaltyScore = new JLabel("0", JLabel.CENTER);
		f1PenaltyScore.setOpaque(true);
		f1PenaltyScore.setBackground(Color.WHITE);
		f1PenaltyScore.setFont(new Font("Tahoma", Font.BOLD, 24));

		f2PenaltyScore = new JLabel("0", JLabel.CENTER);
		f2PenaltyScore.setOpaque(true);
		f2PenaltyScore.setBackground(Color.WHITE);
		f2PenaltyScore.setFont(new Font("Tahoma", Font.BOLD, 24));

		/// --- REF 1
		r1Label = new JLabel("R1", JLabel.CENTER);
		r1Label.setOpaque(true);
		r1Label.setBackground(Color.WHITE);
		r1Label.setFont(new Font("Tahoma", Font.PLAIN, 36));

		r1ResultRed = new JLabel("0", JLabel.CENTER);
		r1ResultRed.setOpaque(true);
		r1ResultRed.setBackground(new Color(238, 44, 44));
		r1ResultRed.setFont(new Font("Tahoma", Font.PLAIN, 36));

		r1ResultBlue = new JLabel("0", JLabel.CENTER);
		r1ResultBlue.setOpaque(true);
		r1ResultBlue.setBackground(new Color(0, 0, 205));
		r1ResultBlue.setFont(new Font("Tahoma", Font.PLAIN, 36));

		/// --- REF 2
		r2Label = new JLabel("R2", JLabel.CENTER);
		r2Label.setOpaque(true);
		r2Label.setBackground(Color.WHITE);
		r2Label.setFont(new Font("Tahoma", Font.PLAIN, 36));

		r2ResultRed = new JLabel("0", JLabel.CENTER);
		r2ResultRed.setOpaque(true);
		r2ResultRed.setBackground(new Color(238, 44, 44));
		r2ResultRed.setFont(new Font("Tahoma", Font.PLAIN, 36));

		r2ResultBlue = new JLabel("0", JLabel.CENTER);
		r2ResultBlue.setOpaque(true);
		r2ResultBlue.setBackground(new Color(0, 0, 205));
		r2ResultBlue.setFont(new Font("Tahoma", Font.PLAIN, 36));

		/// --- REF 3
		r3Label = new JLabel("R3", JLabel.CENTER);
		r3Label.setOpaque(true);
		r3Label.setBackground(Color.WHITE);
		r3Label.setFont(new Font("Tahoma", Font.PLAIN, 36));

		r3ResultRed = new JLabel("0", JLabel.CENTER);
		r3ResultRed.setOpaque(true);
		r3ResultRed.setBackground(new Color(238, 44, 44));
		r3ResultRed.setFont(new Font("Tahoma", Font.PLAIN, 36));

		r3ResultBlue = new JLabel("0", JLabel.CENTER);
		r3ResultBlue.setOpaque(true);
		r3ResultBlue.setBackground(new Color(0, 0, 205));
		r3ResultBlue.setFont(new Font("Tahoma", Font.PLAIN, 36));

		/// --- REF 4
		r4Label = new JLabel("R4", JLabel.CENTER);
		r4Label.setOpaque(true);
		r4Label.setBackground(Color.WHITE);
		r4Label.setFont(new Font("Tahoma", Font.PLAIN, 36));

		r4ResultRed = new JLabel("0", JLabel.CENTER);
		r4ResultRed.setOpaque(true);
		r4ResultRed.setBackground(new Color(238, 44, 44));
		r4ResultRed.setFont(new Font("Tahoma", Font.PLAIN, 36));

		r4ResultBlue = new JLabel("0", JLabel.CENTER);
		r4ResultBlue.setOpaque(true);
		r4ResultBlue.setBackground(new Color(0, 0, 205));
		r4ResultBlue.setFont(new Font("Tahoma", Font.PLAIN, 36));
	}

	//////// ADD COMPONENTS ////////////////////
	private void addComponents() {
		/// --- JMenuBar
		menuBar.add(data);
		roundNumber.add(roundOne);
		roundNumber.add(roundTwo);
		roundNumber.add(roundThree);
		roundNumber.add(rest);
		data.add(roundNumber);
		data.addSeparator();
		timeLeft.add(halfMinute);
		timeLeft.add(oneMinute);
		timeLeft.add(oneHalfMinute);
		timeLeft.add(twoMinutes);
		timeLeft.add(threeMinutes);
		data.add(timeLeft);
		data.addSeparator();
		countdown.add(countdownStart);
		countdown.add(countdownStop);
		countdown.add(countdownReset);
		data.add(countdown);
		data.addSeparator();
		data.add(exitServer);
		
		about.add(taekwondoDevelopers);
		menuBar.add(about);
		setJMenuBar(menuBar);

		add(getTop(), BorderLayout.NORTH);
		add(getMiddle(), BorderLayout.CENTER);
		add(getBottom(), BorderLayout.SOUTH);
		/// add(points, BorderLayout.SOUTH);
		/// add(ips);

	}

	//////// CREATE TOP PANEL ////////////////////
	private JPanel getTop() {
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		JPanel top1 = new JPanel();
		top1.add(buttonStart);
		top1.add(buttonStop);
		top.add(top1, BorderLayout.NORTH);

		JPanel top2 = new JPanel();
		top2.setLayout(new GridLayout(1, 5));
		top2.add(buttonStartTimer);
		top2.add(buttonStopTimer);
		top2.add(buttonResetTimer);
		top2.add(round);
		top2.add(timeLabel);
		top.add(top2, BorderLayout.CENTER);
		/// top.setLayout(new GridLayout(2,2));
		top.setBorder(BorderFactory.createEtchedBorder());
		/// top.add(buttonStart);
		/// top.add(buttonStop);
		/// top.add(round);
		/// top.add(timeLabel);
		return top;
	}

	///////// CREATE MIDDLE PANEL ////////////////
	private JPanel getMiddle() {
		JPanel middle = new JPanel();
		middle.setLayout(new BorderLayout());
		middle.setBorder(BorderFactory.createEtchedBorder());
		middle.add(getTotalPoints(), BorderLayout.NORTH);
		middle.add(getRefPoints(), BorderLayout.CENTER);
		middle.add(getConnection(), BorderLayout.SOUTH);
		/*
		 * middle.add(fighter1); middle.add(f1points); middle.add(draw);
		 * middle.add(drawResult); middle.add(fighter2); middle.add(f2points);
		 */
		/*
		 * middle.add(getRef1()); middle.add(getRef2()); middle.add(getRef3());
		 * middle.add(getRef4()); middle.add(connection); middle.add(ips);
		 */
		return middle;
	}

	private JPanel getConnection() {
		JPanel rConPanel = new JPanel();
		rConPanel.setLayout(new GridLayout(1, 2));
		rConPanel.add(connection);
		rConPanel.add(ips);
		rConPanel.setBorder(BorderFactory.createEtchedBorder());
		return rConPanel;
	}

	private JPanel getRefPoints() {
		JPanel rPanel = new JPanel();
		rPanel.setLayout(new GridLayout(2, 4));
		rPanel.add(getRef1());
		rPanel.add(getRef2());
		rPanel.add(getRef3());
		rPanel.add(getRef4());
		rPanel.add(getWarnings1());
		rPanel.add(getWarnings2());
		rPanel.add(getPenalties1());
		rPanel.add(getPenalties2());
		rPanel.setBorder(BorderFactory.createEtchedBorder());
		return rPanel;
	}

	private JPanel getTotalPoints() {
		JPanel pointsPanel = new JPanel();
		pointsPanel.setLayout(new GridLayout(1, 6));
		pointsPanel.add(fighter1);
		pointsPanel.add(f1points);
		pointsPanel.add(draw);
		pointsPanel.add(drawResult);
		pointsPanel.add(fighter2);
		pointsPanel.add(f2points);
		pointsPanel.setBorder(BorderFactory.createEtchedBorder());
		return pointsPanel;
	}

	private JPanel getWarnings1() {
		JPanel rPanel = new JPanel();
		rPanel.setLayout(new GridLayout(2, 4));
		rPanel.add(f1Warn);
		rPanel.add(f1Warning);
		rPanel.add(f1ButtonWarning);
		rPanel.add(f1WarningScore);
		rPanel.setBorder(BorderFactory.createEtchedBorder());
		return rPanel;
	}

	private JPanel getWarnings2() {
		JPanel rPanel = new JPanel();
		rPanel.setLayout(new GridLayout(2, 4));
		rPanel.add(f2Warn);
		rPanel.add(f2Warning);
		rPanel.add(f2ButtonWarning);
		rPanel.add(f2WarningScore);
		rPanel.setBorder(BorderFactory.createEtchedBorder());
		return rPanel;
	}

	private JPanel getPenalties1() {
		JPanel rPanel = new JPanel();
		rPanel.setLayout(new GridLayout(2, 4));
		rPanel.add(f1Pen);
		rPanel.add(f1Penalty);
		rPanel.add(f1ButtonPenalty);
		rPanel.add(f1PenaltyScore);
		rPanel.setBorder(BorderFactory.createEtchedBorder());
		return rPanel;
	}

	private JPanel getPenalties2() {
		JPanel rPanel = new JPanel();
		rPanel.setLayout(new GridLayout(2, 4));
		rPanel.add(f2Pen);
		rPanel.add(f2Penalty);
		rPanel.add(f2ButtonPenalty);
		rPanel.add(f2PenaltyScore);
		rPanel.setBorder(BorderFactory.createEtchedBorder());
		return rPanel;
	}

	///////// GET REF METHODS ////////////////////
	private JPanel getRef1() {
		JPanel r1Panel = new JPanel();
		r1Panel.setLayout(new GridLayout(1, 3));
		r1Panel.add(r1Label);
		r1Panel.add(r1ResultRed);
		r1Panel.add(r1ResultBlue);
		r1Panel.setBorder(BorderFactory.createEtchedBorder());
		return r1Panel;
	}

	private JPanel getRef2() {
		JPanel r2Panel = new JPanel();
		r2Panel.setLayout(new GridLayout(1, 3));
		r2Panel.add(r2ResultRed);
		r2Panel.add(r2ResultBlue);
		r2Panel.add(r2Label);
		r2Panel.setBorder(BorderFactory.createEtchedBorder());
		return r2Panel;
	}

	private JPanel getRef3() {
		JPanel r3Panel = new JPanel();
		r3Panel.setLayout(new GridLayout(1, 3));
		r3Panel.add(r3Label);
		r3Panel.add(r3ResultRed);
		r3Panel.add(r3ResultBlue);
		r3Panel.setBorder(BorderFactory.createEtchedBorder());
		return r3Panel;
	}

	private JPanel getRef4() {
		JPanel r4Panel = new JPanel();
		r4Panel.setLayout(new GridLayout(1, 3));
		r4Panel.add(r4ResultRed);
		r4Panel.add(r4ResultBlue);
		r4Panel.add(r4Label);
		r4Panel.setBorder(BorderFactory.createEtchedBorder());
		return r4Panel;
	}

	///////// CREATE BOTTOM PANEL ////////////////
	private JPanel getBottom() {
		JPanel bottom = new JPanel();
		bottom.setBorder(BorderFactory.createEtchedBorder());
		bottom.add(buttonTotal);
		bottom.add(buttonReset);
		bottom.add(buttonExit);
		return bottom;
	}

	/////// ADD LISTENERS ////////////////////////
	private void addListeners() {
		TkdServer.getAllIPs();
		TkdServer.subscribe(this);
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				start();
			}
		});
		buttonTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				showTotal();
			}
		});
		buttonStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				stop();
			}
		});
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetPoints();
			}
		});
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				exit();
			}
		});
		f1ButtonWarning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f1Warning();
			}
		});
		f2ButtonWarning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f2Warning();
			}
		});
		f1ButtonPenalty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f1Penalty();
			}
		});
		f2ButtonPenalty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f2Penalty();
			}
		});
		roundOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				whichRound(1);
			}
		});
		roundTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				whichRound(2);
			}
		});
		roundThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				whichRound(3);
			}
		});
		rest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				whichRound(4);
			}
		});
		halfMinute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				roundTime(30);
			}
		});
		oneMinute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				roundTime(1);
			}
		});
		oneHalfMinute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				roundTime(90);
			}
		});
		twoMinutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				roundTime(2);
			}
		});
		threeMinutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				roundTime(3);
			}
		});
		countdownStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				countDown(roundLength);
			}
		});
		countdownStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				countDownStop();
			}
		});
		countdownReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				reset();
			}
		});
		buttonStartTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				countDown(roundLength);
			}
		});		
		buttonStopTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				countDownStop();
			}
		});
		buttonResetTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				reset();
			}
		});
		exitServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				exit();
			}
		});
		taekwondoDevelopers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				taekwondoDevelopers();
			}
		});
	}
	
	//////// TAEKWONDO DEVELOPERS ///////////////
	private void taekwondoDevelopers () {
		StringBuilder sb = new StringBuilder();
		sb.append("HTL SPENGERGASSE 2016/17");
		sb.append(System.getProperty("line.separator"));
		sb.append("Mariya Mykhaylova");
		sb.append(System.getProperty("line.separator"));
		sb.append("Marcin Puka");
		sb.append(System.getProperty("line.separator"));
		sb.append("Marlon Gallardo");
		sb.append(System.getProperty("line.separator"));
		sb.append("Yuriy Kryvonos");
		
		
		JOptionPane.showMessageDialog(this, sb, "Taekwondo Development Team", JOptionPane.INFORMATION_MESSAGE);
	}
	

	//////// WHICH ROUND ////////////////////////
	private void whichRound(int roundNumber) {
		if (roundNumber == 4) {
			round.setText("Rest ");
		} else {
			round.setText("Round: " + roundNumber);
		}
	}

	/////// ROUND TIME /////////////////////////
	private void roundTime(int rlength) {
		if (rlength == 30) {
			timeLabel.setText("00:30");
			this.roundLength = 30;
			this.roundLengthSet = 30;
		} else if (rlength == 1) {
			timeLabel.setText("01:00");
			this.roundLength = 60;
			this.roundLengthSet = 60;
		} else if (rlength == 90) {
			timeLabel.setText("01:30");
			this.roundLength = 90;
			this.roundLengthSet = 90;
		}else if (rlength == 2) {
			timeLabel.setText("02:00");
			this.roundLength = 120;
			this.roundLengthSet = 120;
		} else if (rlength == 3) {
			timeLabel.setText("03:00");
			this.roundLength = 180;
			this.roundLengthSet = 180;
		}
	}

	/////// COUNTDOWN ///////////////////
	private void countDown(int roundLength) {
		int rl = roundLength;
		timeLeft.setEnabled(false);
		countdownStart.setEnabled(false);
		countdownStop.setEnabled(true);
		countdownReset.setEnabled(false);
		TimeClass tc = new TimeClass(rl);
		timer = new Timer(1000, tc);
		timer.start();

	}

	public class TimeClass implements ActionListener {
		int counter;

		public TimeClass(int counter) {
			this.counter = counter;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			counter--;
			///System.out.println(counter);
			int minutes = counter / 60;
			int seconds = counter % 60;

			if (counter >= 1) {
				if (seconds >= 10) {
					timeLabel.setText(String.valueOf("0" + minutes + ":" + seconds));
					roundLength = counter;
				} else {
					timeLabel.setText(String.valueOf("0" + minutes + ":0" + seconds));
					roundLength = counter;
				}
			} else if (counter == 0) {
				timeLabel.setText(String.valueOf("0" + minutes + ":0" + seconds));
				roundLength = counter;
				timer.stop();
				countdownStop.setEnabled(false);
				countdownReset.setEnabled(true);
				Toolkit.getDefaultToolkit().beep();
			}
		}

	}

	///////// COUNTDOWN STOP //////////////////////////////
	private void countDownStop() {
		timer.stop();
		timeLeft.setEnabled(true);
		countdownStart.setEnabled(true);
		countdownStop.setEnabled(false);
		countdownReset.setEnabled(true);
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
		countdownReset.setEnabled(false);
		countdownStart.setEnabled(true);
	}

	private void f1Warning() {
		f1Warnings = f1Warnings + 1;
		f1WarningScore.setText(Integer.toString(f1Warnings));
	}

	private void f2Warning() {
		f2Warnings = f2Warnings + 1;
		f2WarningScore.setText(Integer.toString(f2Warnings));
	}

	private void f1Penalty() {
		f1Penalties = f1Penalties + 1;
		f1PenaltyScore.setText(Integer.toString(f1Penalties));
	}

	private void f2Penalty() {
		f2Penalties = f2Penalties + 1;
		f2PenaltyScore.setText(Integer.toString(f2Penalties));
	}

	///////// EXIT /////////////////////////////////
	private void exit() {
		System.exit(0);
	}

	///////// RESET /////////////////////////////////
	public void resetPoints() {
		TkdServer.resetPoints();
		f1points.setText("0");
		f2points.setText("0");
		drawResult.setText("4");
		f1WarningScore.setText("0");
		f1Warnings = 0;
		f2WarningScore.setText("0");
		f2Warnings = 0;
		f1PenaltyScore.setText("0");
		f1Penalties = 0;
		f2PenaltyScore.setText("0");
		f2Penalties = 0;
	};

	//////// START /////////////////////////////////
	public void start() {
		resetPoints();
		buttonStart.setBackground(new Color(50, 205, 50));
		buttonStop.setBackground(null);
		TkdServer.StartServer();
	}

	/////// STOP //////////////////////////////////
	public void stop() {
		buttonStart.setBackground(null);
		buttonStop.setBackground(new Color(238, 44, 44));
		TkdServer.StopServer();
	}

	///////// MAIN ////////////////////////////////
	public static void main(String[] params) {
		new MainWindow();
	}

	////////// UPDATE POINTS //////////////////////
	public void updatePoints(AtomicInteger points1, AtomicInteger points2, String refereeId) {
		if (refereeId.equals(mReferee1ID)) {
			float nPoints1 = Float.intBitsToFloat(points1.get());
			float nPoints2 = Float.intBitsToFloat(points2.get());
			/// String newData = "Fighter1" + " :"
			/// +Float.toString(nPoints1)+"\n" + "Fighter2" + " :" +
			/// Float.toString(nPoints2);
			String f1update = Float.toString(nPoints1);
			r1ResultRed.setText(f1update);
			String f2update = Float.toString(nPoints2);
			r1ResultBlue.setText(f2update);
			// r1Label.setText(refereeId);
			/// points.setText(newData);
		}
		if (refereeId.equals(mReferee2ID)) {
			float nPoints1 = Float.intBitsToFloat(points1.get());
			float nPoints2 = Float.intBitsToFloat(points2.get());
			String f1update = Float.toString(nPoints1);
			r2ResultRed.setText(f1update);
			String f2update = Float.toString(nPoints2);
			r2ResultBlue.setText(f2update);
			// r2Label.setText(refereeId);
		}
		if (refereeId.equals(mReferee3ID)) {
			float nPoints1 = Float.intBitsToFloat(points1.get());
			float nPoints2 = Float.intBitsToFloat(points2.get());
			String f1update = Float.toString(nPoints1);
			r3ResultRed.setText(f1update);
			String f2update = Float.toString(nPoints2);
			r3ResultBlue.setText(f2update);
			// r3Label.setText(refereeId);
		}
		if (refereeId.equals(mReferee4ID)) {
			float nPoints1 = Float.intBitsToFloat(points1.get());
			float nPoints2 = Float.intBitsToFloat(points2.get());
			String f1update = Float.toString(nPoints1);
			r4ResultRed.setText(f1update);
			String f2update = Float.toString(nPoints2);
			r4ResultBlue.setText(f2update);
			// r4Label.setText(refereeId);
		}
	}

	public void showTotal() {
		float totalRed = 0.0f;
		float totalBlue = 0.0f;
		int drawResult = 4;

		if (Float.parseFloat(r1ResultRed.getText()) > Float.parseFloat(r1ResultBlue.getText())) {
			totalRed = totalRed + 1.0f;
			drawResult = drawResult - 1;
		} else if (Float.parseFloat(r1ResultRed.getText()) < Float.parseFloat(r1ResultBlue.getText())) {
			totalBlue = totalBlue + 1.0f;
			drawResult = drawResult - 1;
		}

		if (Float.parseFloat(r2ResultRed.getText()) > Float.parseFloat(r2ResultBlue.getText())) {
			totalRed = totalRed + 1.0f;
			drawResult = drawResult - 1;
		} else if (Float.parseFloat(r2ResultRed.getText()) < Float.parseFloat(r2ResultBlue.getText())) {
			totalBlue = totalBlue + 1.0f;
			drawResult = drawResult - 1;
		}

		if (Float.parseFloat(r3ResultRed.getText()) > Float.parseFloat(r3ResultBlue.getText())) {
			totalRed = totalRed + 1.0f;
			drawResult = drawResult - 1;
		} else if (Float.parseFloat(r3ResultRed.getText()) < Float.parseFloat(r3ResultBlue.getText())) {
			totalBlue = totalBlue + 1.0f;
			drawResult = drawResult - 1;
		}

		if (Float.parseFloat(r4ResultRed.getText()) > Float.parseFloat(r4ResultBlue.getText())) {
			totalRed = totalRed + 1.0f;
			drawResult = drawResult - 1;
		} else if (Float.parseFloat(r4ResultRed.getText()) < Float.parseFloat(r4ResultBlue.getText())) {
			totalBlue = totalBlue + 1.0f;
			drawResult = drawResult - 1;
		}
		f1points.setText(Float.toString(totalRed));
		f2points.setText(Float.toString(totalBlue));
		this.drawResult.setText(Integer.toString(drawResult));
	}

	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}
}