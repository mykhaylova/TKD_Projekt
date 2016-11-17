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
    static String mRefereeID = null;
    static AtomicInteger mFighter1Points = new AtomicInteger(Float.floatToIntBits(0.0f));
    static AtomicInteger mFighter2Points = new AtomicInteger(Float.floatToIntBits(0.0f));
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
                    float points1 = Float.intBitsToFloat(mFighter1Points.get());
                    float points2 = Float.intBitsToFloat(mFighter2Points.get());
                    final String answerContent = mFighter1ID + ":" +Float.toString(points1)+"\n" + mFighter2ID + ":" + Float.toString(points2);

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
                    
                	   mRefereeID = refereeID;
                	   
	                    if(mFighter1ID.equals(fighterID))
	                    {
	                        float fighterPoints = Float.intBitsToFloat(mFighter1Points.get());
	                        fighterPoints += points;
	                        mFighter1Points.set(Float.floatToIntBits(fighterPoints));
	                    }
	                    else if(mFighter2ID.equals(fighterID))
	                    {
	                        float fighterPoints = Float.intBitsToFloat(mFighter2Points.get());
	                        fighterPoints += points;
	                        mFighter2Points.set(Float.floatToIntBits(fighterPoints));
	                    }
	                    else {
	                        System.err.println("Point update for unknown FighterID received.");
	                    }
                		   

                    //answer
                    httpExchange.sendResponseHeaders(200, -1); //we dont send a response body, so -1;
                    httpExchange.close();
                }
                else {
                    System.err.println("Unknown request method: "+requestMethod);
                }
            } finally {
                httpExchange.close();
                for(PointListener pl : mListeners)
                	pl.updatePoints(mFighter1Points, mFighter2Points, mRefereeID);
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

        //to make the httpServer handle the request multithreaded (1 thread by request) we use a thread pool.
        // This avoids creating new threads on the fly, but uses a pool of a fixed number of threads to process the requests.
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
    		mServer.stop(2);
    		System.out.println("stopped http server");
    	}
    	catch (NullPointerException e)
    	{
    		e.printStackTrace();
    	}
    }
    
   public static void resetPoints()
   {
	   mFighter1Points.set(0);
	   mFighter2Points.set(0);	 
	  // for(PointListener pl : mListeners)
      // pl.updatePoints(mFighter1Points, mFighter2Points, mRefereeID);	  
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