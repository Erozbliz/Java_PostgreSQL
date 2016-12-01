package test_postgres;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main test Postgress");
		//Connexion à PostgreSQL
		Connection c = SqlRequest.Connexion("postgres", "root");
		try {
			//TEST
			//SqlRequest.SelectNode(c);
			//SqlRequest.SelectComposition(c);
			SqlRequest.SelectAll(c, 1);
			c.close();
			
			Thread serverHttp = new Thread(new ServerStartHTTP());
			serverHttp.start();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Protocol HTTP
	 * pour http post et get Permet de lancer le serveur
	 *
	 */
	public static class ServerStartHTTP implements Runnable {
		public void run() {
			try {
					HttpServeur httpServeur= new HttpServeur();
					Thread monitorThread = new Thread(httpServeur);
					monitorThread.start();
	
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}
