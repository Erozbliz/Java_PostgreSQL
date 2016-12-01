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
			//SqlRequest.SelectNode(c);
			//SqlRequest.SelectComposition(c);
			SqlRequest.SelectAll(c, 1);
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
