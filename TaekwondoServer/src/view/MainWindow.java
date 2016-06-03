package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.TkdServer;
import model.TkdServer.PointListener;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements PointListener{

	private JButton buttonStart, buttonStop; 
	private JLabel points;
	private LineBorder border;
	private JList<String> ips;
	
	public MainWindow()
	{
		initBasics();
		initComponents();
		addComponents();
		addListeners();
		setVisible(true);
	}
	
	private void initBasics()
	{
		setSize(450, 300);
		setLocationRelativeTo(null);
		setTitle("Taekwondo Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
	}
	
	private void initComponents()
	{
		points = new JLabel("Points");
			points.setBorder(border);	
		buttonStart = new JButton("START");
		buttonStop = new JButton("STOP");
		ArrayList<String> ipsal = TkdServer.getAllIPs();
		String[] entries = ipsal.toArray(new String[ipsal.size()]);
		ips = new JList<String>(entries);		
	
	}
	
	private void addComponents()
	{
		
		add(buttonStart);
		add(buttonStop);	
		add(points);
		add(ips);
	}
	
	private void addListeners()
	{
		TkdServer.getAllIPs();
		TkdServer.subscribe(this);
		buttonStart.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ae){start();}	} );
		buttonStop.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ae){stop();}	} );
		
	}
	
	public void start()
	{
		
		TkdServer.StartServer();
		
	}
	
	public void stop()
	{
		TkdServer.StopServer();
	}	
	
	public static void main(String[] params)
	{
		new MainWindow();
	}

	@Override
	public void updatePoints(AtomicInteger points1, AtomicInteger points2) {
		float nPoints1 = Float.intBitsToFloat(points1.get());
        float nPoints2 = Float.intBitsToFloat(points2.get());
        String newData = "Fighter1" + " :" +Float.toString(nPoints1)+"\n" + "Fighter2" + " :" + Float.toString(nPoints2);
 
		points.setText(newData);
		
	}
	
}
