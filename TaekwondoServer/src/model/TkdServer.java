package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class TkdServer
{
    static String mFighter1ID = "fighter1";
    static String mFighter2ID = "fighter2";
    static String m1RefereeID = "referee1";
    static String m2RefereeID = "referee2";
    static String m3RefereeID = "referee3";
    static String m4RefereeID = "referee4";
    static AtomicInteger m1Fighter1Points = new AtomicInteger(0);
    static AtomicInteger m1Fighter2Points = new AtomicInteger(0);
    static AtomicInteger m2Fighter1Points = new AtomicInteger(0);
    static AtomicInteger m2Fighter2Points = new AtomicInteger(0);
    static AtomicInteger m3Fighter1Points = new AtomicInteger(0);
    static AtomicInteger m3Fighter2Points = new AtomicInteger(0);
    static AtomicInteger m4Fighter1Points = new AtomicInteger(0);
    static AtomicInteger m4Fighter2Points = new AtomicInteger(0);

    static ExecutorService threadPool = Executors.newCachedThreadPool();
    static HttpServer mServer = null;
    static ArrayList<PointListener> mListeners = new ArrayList<>();    

    static final HttpHandler mCountHandler = new HttpHandler() {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            try {
                String requestMethod = httpExchange.getRequestMethod().toUpperCase();
                //GET:
                if(requestMethod.equals("GET"))
                {
                    int points1 = m1Fighter1Points.get();
                    int points2 = m1Fighter2Points.get();
                    final String answerContent = mFighter1ID + ":" + Integer.toString(points1)+"\n" + mFighter2ID + ":" + Integer.toString(points2);

                    Headers responseHeaders = httpExchange.getResponseHeaders();
                    responseHeaders.set("Content-Type", String.format("text/plain", StandardCharsets.UTF_8));
                    byte[] responseBody = answerContent.getBytes(StandardCharsets.UTF_8);
                    httpExchange.sendResponseHeaders(200, responseBody.length);
                    httpExchange.getResponseBody().write(responseBody);
                }

                else if(requestMethod.equals("POST"))
                {
                	System.out.println("POSTHANDLER");
                    Headers requestHeaders = httpExchange.getRequestHeaders();
                    String contentType = requestHeaders.getFirst("Content-Type");
                    if(contentType.equals("text/plain") == false)
                        System.out.println("Request has wrong content type. Should be text/plain, but is "+contentType);

                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(),"UTF-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    String requestBody = sb.toString();
                    String[] requestBodyParts = requestBody.split(":");
                    String refereeID = requestBodyParts[0];
                    String fighterID = requestBodyParts[1];
                    float points = Float.valueOf(requestBodyParts[2]);
                    System.out.println("REQ:"+requestBody);
                    
                	   
                    if(m1RefereeID.equals(refereeID))
                    {                	   
	                    if(mFighter1ID.equals(fighterID))
	                    {
	                        int fighterPoints = m1Fighter1Points.get();
	                        fighterPoints += points;
	                        m1Fighter1Points.set(fighterPoints);
	                    }
	                    else if(mFighter2ID.equals(fighterID))
	                    {
	                        int fighterPoints = m1Fighter2Points.get();
	                        fighterPoints += points;
	                        m1Fighter2Points.set(fighterPoints);
	                    }
	                    else {
	                        System.err.println("Point update for unknown FighterID received.");
	                    }
                    }
                    if(m2RefereeID.equals(refereeID))
                    {                	   
	                    if(mFighter1ID.equals(fighterID))
	                    {
	                        int fighterPoints = m2Fighter1Points.get();
	                        fighterPoints += points;
	                        m2Fighter1Points.set(fighterPoints);
	                    }
	                    else if(mFighter2ID.equals(fighterID))
	                    {
	                        int fighterPoints = m2Fighter2Points.get();
	                        fighterPoints += points;
	                        m2Fighter2Points.set(fighterPoints);
	                    }
	                    else {
	                        System.err.println("Point update for unknown FighterID received.");
	                    }
                    }
                    if(m3RefereeID.equals(refereeID))
                    {                	   
	                    if(mFighter1ID.equals(fighterID))
	                    {
	                        int fighterPoints = m3Fighter1Points.get();
	                        fighterPoints += points;
	                        m3Fighter1Points.set(fighterPoints);
	                    }
	                    else if(mFighter2ID.equals(fighterID))
	                    {
	                        int fighterPoints = m3Fighter2Points.get();
	                        fighterPoints += points;
	                        m3Fighter2Points.set(fighterPoints);
	                    }
	                    else {
	                        System.err.println("Point update for unknown FighterID received.");
	                    }
                    }
                    if(m4RefereeID.equals(refereeID))
                    {                	   
	                    if(mFighter1ID.equals(fighterID))
	                    {
	                        int fighterPoints = m4Fighter1Points.get();
	                        fighterPoints += points;
	                        m4Fighter1Points.set(fighterPoints);
	                    }
	                    else if(mFighter2ID.equals(fighterID))
	                    {
	                        int fighterPoints = m4Fighter2Points.get();
	                        fighterPoints += points;
	                        m4Fighter2Points.set(fighterPoints);
	                    }
	                    else {
	                        System.err.println("Point update for unknown FighterID received.");
	                    }
                    }
                    
                    httpExchange.sendResponseHeaders(200, -1); 
                    httpExchange.close();
                }
                else {
                    System.err.println("Unknown request method: "+requestMethod);
                }
            } finally {
                httpExchange.close();
                for(PointListener pl : mListeners)
                {
                	pl.updatePoints(m1Fighter1Points, m1Fighter2Points, "referee1");
                	pl.updatePoints(m2Fighter1Points, m2Fighter2Points, "referee2");
                	pl.updatePoints(m3Fighter1Points, m3Fighter2Points, "referee3");
                	pl.updatePoints(m4Fighter1Points, m4Fighter2Points, "referee4");
                }
            }
        }
    };    
    
    public static ArrayList<String> getAllIPs()
    {
    	ArrayList<String> ips = new ArrayList<String>();
		try {
			
			for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
				NetworkInterface ifc = en.nextElement();
				if(ifc.isUp() && !ifc.isLoopback()){
					for(Enumeration<InetAddress> ia = ifc.getInetAddresses(); ia.hasMoreElements();) {
						InetAddress i = ia.nextElement();
						ips.add(i.toString());
						System.out.println(i.toString());
					}
				}	
			}
			return ips;
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ips;
		}
	}

    public static void StartServer()
    {
        String hostname = "";
        InetSocketAddress addr = null;
        try{
            hostname = InetAddress.getLocalHost().getHostName();
            InetAddress h = InetAddress.getByName(hostname);

            addr = new InetSocketAddress(8080);
            System.out.println("Hostname: " + hostname + " " + h.getHostAddress());
            //InetAddress ip = InetAddress.getByName("192.168.1.11");
           //addr = new InetSocketAddress(ip, 8080);
        }catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
       
        ExecutorService threadPool = Executors.newFixedThreadPool(24);
        try {
            mServer = HttpServer.create(addr, 24);
            mServer.setExecutor(threadPool);
            mServer.createContext("/count", mCountHandler);
            System.out.println("starting http server");
            mServer.start();           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void StopServer()
    {
    	try 
    	{
    		mServer.stop(1);
    		System.out.println("stopped http server");
    	}
    	catch (NullPointerException e)
    	{
    		e.printStackTrace();
    	}
    }
    
   public static void resetPoints()
   {
	   m1Fighter1Points.set(0);
	   m1Fighter2Points.set(0);
	   m2Fighter1Points.set(0);
	   m2Fighter2Points.set(0);
	   m3Fighter1Points.set(0);
	   m3Fighter2Points.set(0);
	   m4Fighter1Points.set(0);
	   m4Fighter2Points.set(0);
	   
	   for(PointListener pl : mListeners)
	   {
	       pl.updatePoints(m1Fighter1Points, m1Fighter2Points, "referee1");
		   pl.updatePoints(m2Fighter1Points, m2Fighter2Points, "referee2");
		   pl.updatePoints(m3Fighter1Points, m3Fighter2Points, "referee3");
		   pl.updatePoints(m4Fighter1Points, m4Fighter2Points, "referee4");
	   }
	  	 	  
   }
    
    public static void subscribe(PointListener pl)
    {
    	mListeners.add(pl);  		
	}
    
    public interface PointListener
    {
    	void updatePoints(AtomicInteger fighter1, AtomicInteger fighter2, String refereeID);
    };
	
} 