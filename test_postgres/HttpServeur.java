package test_postgres;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

/**
 * Pour le client Ajax
 * http://www.java2s.com/Code/Jar/h/Downloadhttp20070405jar.htm
 *
 */
public class HttpServeur implements Runnable {
	public HttpServer server;
	public int portThread = 9999;

	//Avec Thread
	@Override
	public void run() {
		try {
			server = HttpServer.create(new InetSocketAddress(portThread), 0);
			System.out.println("SERVEUR HTTP OK localhost ou 127.0.0.1:" + portThread);
			System.out.println("/");
			System.out.println("/getSunburst");
			System.out.println("Se rendre directement sur l'URL pour activer la requete SQL");
			System.out.println("------------------------------------------");
			server.createContext("/", new Handler.IndexHandler());
			server.createContext("/getSunburst", new Handler.GetSunburstHandler());
			server.setExecutor(null);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Stop() {
		server.stop(0);
		System.out.println("serveur http arreté");
	}
	
	
	

	//Méthode sans thread (PAS UTILISER)
	public void Start(int port) {
		try {
			server = HttpServer.create(new InetSocketAddress(portThread), 0);
			//System.out.println("SERVEUR HTTP OK" + port);
			server.createContext("/", new Handler.IndexHandler());
			server.createContext("/getSunburst", new Handler.GetSunburstHandler());
			server.setExecutor(null);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}