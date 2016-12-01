package test_postgres;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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

			String responseStr = "JSON";
			
			//Lancement du script sql
			responseStr = SqlRequest.SelectAll(Main.c, 1);
			
			//Map<String, String> params = queryToMap(query); 
			//System.out.println("user=" + params.get("user"));
			//System.out.println(argHttpExhange.getRequestURI().getQuery());
			
			argHttpExhange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); //CORS
			argHttpExhange.sendResponseHeaders(200, responseStr.length());
			OutputStream os = argHttpExhange.getResponseBody();
			os.write(responseStr.toString().getBytes());
			os.close();
		}
	}
	
	
	/**
	 * Deserialisation URL pour avoir les parametres
	 * 
	 * Utilisation : Map<String, String> params = queryToMap(httpExchange.getRequestURI().getQuery()); 
	 * @param query
	 * @return
	 */
	public static Map<String, String> queryToMap(String query){
	    Map<String, String> result = new HashMap<String, String>();
	    for (String param : query.split("&")) {
	        String pair[] = param.split("=");
	        if (pair.length>1) {
	            result.put(pair[0], pair[1]);
	        }else{
	            result.put(pair[0], "");
	        }
	    }
	    return result;
	}


}
