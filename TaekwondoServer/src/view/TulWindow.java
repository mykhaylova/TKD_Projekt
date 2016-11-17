package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.TkdServer;
import model.TkdServer.PointListener;

@SuppressWarnings("serial")
public class TulWindow extends JFrame implements PointListener{

	private JButton buttonStart, buttonStop, buttonReset, buttonExit; 
	private JLabel points, connection;
	private LineBorder border;
	private JList<String> ips;
	private JLabel fighter1, f1points, r1Label, r2Label, r3Label, r4Label;
	private JLabel fighter2, f2points, r1ResultRed, r1ResultBlue, r2ResultRed,r2ResultBlue, r3ResultRed, r3ResultBlue, r4ResultRed, r4ResultBlue;
	
	//private JMenuBar  menuBar;

	
	
	//////// CONSTRUCTOR ///////////////////
	public TulWindow()
	{
		initBasics();
		initComponents();
		addComponents();
		addListeners();
		setVisible(true);
	}
	
	/////// INIT BASICS /////////////////////
	private void initBasics()
	{
		setSize(700, 450);
		setLocationRelativeTo(null);
		setTitle("Taekwondo Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout ());
	}
	
	/////// INIT COMPONENTS ///////////////////////
	private void initComponents()
	{
		points = new JLabel("Points");
	
		points.setBorder(border);	
		buttonStart = new JButton("START");
		buttonStop = new JButton("STOP");
		buttonStop.setBackground(Color.red);
		buttonReset = new JButton("RESET");
		buttonExit = new JButton("EXIT");
		ArrayList<String> ipsal = TkdServer.getAllIPs();
		String[] entries = ipsal.toArray(new String[ipsal.size()]);
		///String[] entries = {"Test1", "Test2", "Test3", "Test4"};
		ips = new JList<String>(entries);		
		
		fighter1 = new JLabel("Fighter 1:", JLabel.RIGHT);
		fighter1.setOpaque(true);
		fighter1.setBackground(Color.RED);
		fighter1.setFont(new Font("Tahoma", Font.BOLD, 56));
		
		f1points = new JLabel("0", JLabel.CENTER);
		f1points.setOpaque(true);
		f1points.setBackground(Color.RED);
		f1points.setFont(new Font("Tahoma", Font.BOLD, 56));
		
		fighter2 = new JLabel("Fighter 2:", JLabel.RIGHT);
		fighter2.setOpaque(true);
		fighter2.setBackground(Color.blue);
		fighter2.setFont(new Font("Tahoma", Font.BOLD, 56));	
		
		f2points = new JLabel("0", JLabel.CENTER);
		f2points.setOpaque(true);
		f2points.setBackground(Color.BLUE);
		f2points.setFont(new Font("Tahoma", Font.BOLD, 56));
		
		connection = new JLabel("Connection:", JLabel.RIGHT);
		connection.setOpaque(true);
		connection.setBackground(Color.white);
		connection.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		/// --- REF 1 
		r1Label = new JLabel("R1", JLabel.CENTER);
		r1Label.setOpaque(true);
		r1Label.setBackground(Color.WHITE);
		r1Label.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		r1ResultRed = new JLabel("10", JLabel.CENTER);
		r1ResultRed.setOpaque(true);
		r1ResultRed.setBackground(Color.RED);
		r1ResultRed.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		r1ResultBlue = new JLabel("10", JLabel.CENTER);
		r1ResultBlue.setOpaque(true);
		r1ResultBlue.setBackground(Color.BLUE);
		r1ResultBlue.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		/// --- REF 2
		r2Label = new JLabel("R2", JLabel.CENTER);
		r2Label.setOpaque(true);
		r2Label.setBackground(Color.WHITE);
		r2Label.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		r2ResultRed = new JLabel("10", JLabel.CENTER);
		r2ResultRed.setOpaque(true);
		r2ResultRed.setBackground(Color.RED);
		r2ResultRed.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		r2ResultBlue = new JLabel("10", JLabel.CENTER);
		r2ResultBlue.setOpaque(true);
		r2ResultBlue.setBackground(Color.BLUE);
		r2ResultBlue.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		
		/// --- REF 3
		r3Label = new JLabel("R3", JLabel.CENTER);
		r3Label.setOpaque(true);
		r3Label.setBackground(Color.WHITE);
		r3Label.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		r3ResultRed = new JLabel("10", JLabel.CENTER);
		r3ResultRed.setOpaque(true);
		r3ResultRed.setBackground(Color.RED);
		r3ResultRed.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		r3ResultBlue = new JLabel("10", JLabel.CENTER);
		r3ResultBlue.setOpaque(true);
		r3ResultBlue.setBackground(Color.BLUE);
		r3ResultBlue.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		
		/// --- REF 4
		r4Label = new JLabel("R4", JLabel.CENTER);
		r4Label.setOpaque(true);
		r4Label.setBackground(Color.WHITE);
		r4Label.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		r4ResultRed = new JLabel("10", JLabel.CENTER);
		r4ResultRed.setOpaque(true);
		r4ResultRed.setBackground(Color.RED);
		r4ResultRed.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		r4ResultBlue = new JLabel("10", JLabel.CENTER);
		r4ResultBlue.setOpaque(true);
		r4ResultBlue.setBackground(Color.BLUE);
		r4ResultBlue.setFont(new Font("Tahoma", Font.PLAIN, 36));
	}
	
	
	//////// ADD COMPONENTS ////////////////////
	private void addComponents()
	{
		add(getTop(),BorderLayout.NORTH);
		add(getMiddle(),BorderLayout.CENTER);
		add(getBottom(), BorderLayout.SOUTH);
///		add(buttonStart);
///		add(buttonStop);	
///		add(points, BorderLayout.SOUTH);
///		add(ips);
		
	}
	

	//////// CREATE TOP PANEL ////////////////////
	private JPanel getTop() {
		JPanel top = new JPanel();
		top.setBorder(BorderFactory.createEtchedBorder());
		top.add(buttonStart);
		top.add(buttonStop);
		return top;		
	}
	
	///////// CREATE MIDDLE PANEL ////////////////
	private JPanel getMiddle() {
		JPanel middle  = new JPanel();
		middle.setLayout(new GridLayout(3,3));
		middle.setBorder(BorderFactory.createEtchedBorder());
		middle.add(fighter1);
		middle.add(f1points);
		middle.add(fighter2);
		middle.add(f2points);
		middle.add(getRef1());
		middle.add(getRef2());
		middle.add(getRef3());
		middle.add(getRef4());
		middle.add(connection);
		middle.add(ips);
		return middle;		
	}
	
	///////// GET REF METHODS ////////////////////
	private JPanel getRef1 () {
		JPanel r1Panel = new JPanel();
		r1Panel.setLayout(new GridLayout(1,3));
		r1Panel.add(r1Label);
		r1Panel.add(r1ResultRed);
		r1Panel.add(r1ResultBlue);
		r1Panel.setBorder(BorderFactory.createEtchedBorder());
		return r1Panel;
	}
	
	private JPanel getRef2 () {
		JPanel r2Panel = new JPanel();
		r2Panel.setLayout(new GridLayout(1,3));
		r2Panel.add(r2ResultRed);
		r2Panel.add(r2ResultBlue);
		r2Panel.add(r2Label);
		r2Panel.setBorder(BorderFactory.createEtchedBorder());
		return r2Panel;
	}
	
	private JPanel getRef3 () {
		JPanel r3Panel = new JPanel();
		r3Panel.setLayout(new GridLayout(1,3));
		r3Panel.add(r3Label);
		r3Panel.add(r3ResultRed);
		r3Panel.add(r3ResultBlue);
		r3Panel.setBorder(BorderFactory.createEtchedBorder());
		return r3Panel;
	}
	
	private JPanel getRef4 () {
		JPanel r4Panel = new JPanel();
		r4Panel.setLayout(new GridLayout(1,3));
		r4Panel.add(r4ResultRed);
		r4Panel.add(r4ResultBlue);
		r4Panel.add(r4Label);
		r4Panel.setBorder(BorderFactory.createEtchedBorder());
		return r4Panel;
	}
	
	///////// CREATE BOTTOM PANEL ////////////////
	private JPanel getBottom () {
		JPanel bottom = new JPanel ();
		bottom.setBorder(BorderFactory.createEtchedBorder());
		bottom.add(buttonReset);
		bottom.add(buttonExit);
		return bottom;	
	}

	/////// ADD LISTENERS ////////////////////////
	private void addListeners()
	{
		TkdServer.getAllIPs();
		TkdServer.subscribe(this);
		buttonStart.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ae){start();}	} );
		buttonStop.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ae){stop(); }	} );
		buttonReset.addActionListener(new ActionListener (){public void actionPerformed(ActionEvent ae){resetPoints(); }	} );
		buttonExit.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent ae){exit(); }});
	}
	
	///////// EXIT /////////////////////////////////
	private void exit() {
		System.exit(0);
	}

	///////// RESET /////////////////////////////////
	public void resetPoints() {
		TkdServer.resetPoints();
		//updatePoints(points1, points2);
		//f1points.setText("0.0");
		//f2points.setText("0.0");			
	};
	
	//////// START /////////////////////////////////
	public void start()
	{		
		resetPoints();
		buttonStart.setBackground(Color.green);
		buttonStop.setBackground(null);
		TkdServer.StartServer();		
	}
	
	/////// STOP //////////////////////////////////
	public void stop()
	{
		buttonStart.setBackground(null);
		buttonStop.setBackground(Color.red);
		TkdServer.StopServer();
	}	
	
	
	///////// MAIN ////////////////////////////////
	public static void main(String[] params)
	{
		new MainWindow();
	}

	////////// UPDATE POINTS //////////////////////
	public void updatePoints(AtomicInteger points1, AtomicInteger points2, String refereeId) {
		float nPoints1 = Float.intBitsToFloat(points1.get());
        float nPoints2 = Float.intBitsToFloat(points2.get());
 ///    String newData = "Fighter1" + " :" +Float.toString(nPoints1)+"\n" + "Fighter2" + " :" + Float.toString(nPoints2);
        String f1update = Float.toString(nPoints1);
        f1points.setText(f1update);
        String f2update = Float.toString(nPoints2);
        f2points.setText(f2update);
        
///		points.setText(newData);
		
	}

	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}



