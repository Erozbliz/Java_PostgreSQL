package test_postgres;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Pour le client Ajax
 * http://www.java2s.com/Code/Jar/h/Downloadhttp20070405jar.htm
 *
 */
public class Handler{
	
	
	
	/**
	 * Route /
	 */
	public static class IndexHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange argHttpExhange) throws IOException {
			String indexMessage = "Bienvenue sur le serveur localhost/127.0.01 " + " Port: " + 9999;
			argHttpExhange.sendResponseHeaders(200, indexMessage.length());
			OutputStream os = argHttpExhange.getResponseBody();
			os.write(indexMessage.getBytes());
			os.close();
		}
	}
	
	
	/**
	 * Permet d'avoir le dernier evenement
	 * Route  /getSunburst
	 */
	public static class GetSunburstHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange argHttpExhange) throws IOException {
			URI requestedUri = argHttpExhange.getRequestURI();
			String query = requestedUri.getRawQuery();

			String responseStr = "hello";

			argHttpExhange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); //CORS
			argHttpExhange.sendResponseHeaders(200, responseStr.length());
			OutputStream os = argHttpExhange.getResponseBody();
			os.write(responseStr.toString().getBytes());
			os.close();
		}
	}



}
