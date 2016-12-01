package test_postgres;

import java.sql.Connection;


public class Main {
	
	//Connexion 
	static Connection c;

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main test Postgress");
		//Connexion à PostgreSQL
		c = SqlRequest.Connexion("postgres", "root");
		try {
			//TEST lance direct la requete sql
			//SqlRequest.SelectNode(c);
			//SqlRequest.SelectComposition(c);
			//SqlRequest.SelectAll(c, 1); 
			
			
			Thread serverHttp = new Thread(new ServerStartHTTP());
			serverHttp.start();
			
			
			//c.close();
		} catch (Exception e) {
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
