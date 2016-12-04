/*
 * Nom de classe : Main
 *
 * Description   : connexion a la base de donnees et lancement du serveur http
 *
 * Version       : 3.0
 *
 * Date          : 04/12/2016
 * 
 * Copyright     : Olivier Colombies et Olivier Schultz
 */
package test_postgres;

import java.sql.Connection;

/**
 * description de la classe.
 * explication supplémentaire si nécessaire
 * 
 * @version 3.0
 *
 * @see UneAutreClasse
 * @author Colombies et Schultz
 */
public class Main {
	
	static Connection connection; /* connexion */

	/** 
	 * description de la méthode.
	 * explication supplémentaire si nécessaire 
	 *  
	 * @return      void
	 * @param       args
	 * 
	 * @see UneAutreClasse#UneAutreMethode 
	 * @author Colombies et Schultz
	 */
	public static void main(String[] args) {
		connection = SqlRequest.Connexion("postgres", "root"); /* connexion a PostgreSQL */
		try {
			/* lancement du thread pour le serveur HTTP REST */
			Thread serverHttp = new Thread(new ServerStartHTTP());
			serverHttp.start();
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
