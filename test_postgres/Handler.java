package test_postgres;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Pour le client Ajax
 * http://www.java2s.com/Code/Jar/h/Downloadhttp20070405jar.htm
 *
 */
public class Handler {

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
	 * http://127.0.0.1:9999/getSunburst?parent=0
	 * Route  /getSunburst
	 */
	public static class GetSunburstHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange argHttpExhange) throws IOException {

			// parse request
			Map<String, String> parameters = new HashMap<String, String>();
			//Message a afficher
			String responseStr = "";
			int parentId = 0;

			URI requestedUri = argHttpExhange.getRequestURI();
			String query = requestedUri.getRawQuery();

			if (query != null) {
				//On cherche les arguments passé dans l'url
				parameters = queryToMap(query);
				//System.out.println("query=" + query);
				System.out.println("param parent=" + parameters.get("parent"));

				parentId = Integer.parseInt(parameters.get("parent"));
				//System.out.println("param parent=" + parentId);
			} else {
				System.out.println("Aucun argument (ex:/getSunburst?parent=0)");
			}

			//Lancement du script sql
			System.out.println("parentId " + parentId);
			responseStr = SqlRequest.SelectAll(Main.c, parentId);

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
	 * 
	 *  TOOL
	 * 
	 */

	/**
	 * Deserialisation URL pour avoir les parametres
	 * 
	 * Utilisation : Map<String, String> params = queryToMap(httpExchange.getRequestURI().getQuery()); 
	 * System.out.println("param A=" + params.get("A"));
	 * @param query
	 * @return
	 */
	public static Map<String, String> queryToMap(String query) {
		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return result;
	}

	/**
	 * 
	 * @param query
	 * @param parameters
	 * @throws UnsupportedEncodingException
	 */
	public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

		if (query != null) {
			String pairs[] = query.split("[&]");
			for (String pair : pairs) {
				String param[] = pair.split("[=]");
				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						List<String> values = (List<String>) obj;
						values.add(value);

					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}

}
