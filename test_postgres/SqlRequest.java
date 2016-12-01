package test_postgres;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  Méthode pour faire des requetes SQL au serveur PostgreSQL
 *
 */
public class SqlRequest {
	
	
	
	static String finalJson = ""; // Json final

	/**
	 * Lance une connexion postgresql
	 * @param user
	 * @param password
	 * @return Connection c
	 */
	public static Connection Connexion(String user, String password) {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;
	}

	/**
	 *  Selectionne tout les noeuds node pour les afficher
	 * @param c
	 */
	public static void SelectNode(Connection c) {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM node;");
			while (rs.next()) {
				int id_node = rs.getInt("id_node");
				String name = rs.getString("name");
				String tag = rs.getString("tag");
				int size = rs.getInt("size");

				System.out.print(" id_node = " + id_node);
				System.out.print(" name = " + name);
				System.out.print(" tag = " + tag);
				System.out.print(" size = " + size);
				System.out.println();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("SelectNode done successfully");
	}

	/**
	 *  Selectionne toutes les compositions pour les afficher
	 * @param c
	 */
	public static void SelectComposition(Connection c) {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM composition;");
			while (rs.next()) {
				int id_parent = rs.getInt("id_node");
				int id_enfant = rs.getInt("id_node_1");
				System.out.print(" id_parent = " + id_parent);
				System.out.print(" id_enfant = " + id_enfant);
				System.out.println();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("SelectComposition done successfully");
	}

	public static void SelectAll(Connection c, int numRoot) {

		//param1 = enfant , parent2 = parent
		Map<Integer, Integer> mapEnfantParent = new HashMap<Integer, Integer>();
		//param1 = id_node , parent2 = name
		Map<Integer, String> mapIdNodeName = new HashMap<Integer, String>();

		HashMap<String, Object> mapEnfantParent2 = new HashMap<String, Object>();
		ArrayList<Integer> listParent = new ArrayList<Integer>();
		ArrayList<Integer> listEnfant = new ArrayList<Integer>();

		//POUR LES COMPOSITION on remplit le hasmap
		try {
			Statement stmt = null;
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM composition;");
			while (rs.next()) {
				int id_parent = rs.getInt("id_node");
				int id_enfant = rs.getInt("id_node_1");

				listParent.add(id_parent);
				listEnfant.add(id_enfant);
				mapEnfantParent.put(id_enfant, id_parent);
				mapEnfantParent2.put(Integer.toString(id_enfant), id_parent);

				/*System.out.print(" niveau_un = " + niveau_un);
				System.out.print(" root = " + root);
				System.out.print(" id_parent = " + id_parent);
				System.out.print(" id_enfant = " + id_enfant);
				System.out.println();*/

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//POUR LES NOEUDS on remplit le hasmap
		try {
			Statement stmt = null;
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM node;");
			while (rs.next()) {
				int id_node = rs.getInt("id_node");
				String name = rs.getString("name");
				String tag = rs.getString("tag");
				int size = rs.getInt("size");

				System.out.print(" id_node = " + id_node);
				System.out.print(" name = " + name);
				System.out.print(" tag = " + tag);
				System.out.print(" size = " + size);
				System.out.println();

				mapIdNodeName.put(id_node, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("\n---------------");
		createJSON(0, mapEnfantParent, mapIdNodeName);

		//utilser http://www.jsoneditoronline.org/ 

	}

	/**
	 * Création du JSON
	 * 
	 */
	public static void createJSON(int parent, Map<Integer, Integer> mapEnfantParent,
			Map<Integer, String> mapIdNodeName) {

		Set<Integer> keys = mapIdNodeName.keySet(); //key = id_node, name

		finalJson = "{\"name\":\"" + mapIdNodeName.get(parent) + "\",\"children\":[";
		FinalRecursivee(parent, mapEnfantParent, mapIdNodeName);
		String replacedString = finalJson.replace("}{", "},{");
		System.out.println("\n" + replacedString);

		//Création du JSON en sortie
		try {
			final File parentDir = new File("web_service");
			parentDir.mkdir();
			final String hash = "data";
			final String fileName = hash + ".json";
			final File file = new File(parentDir, fileName);
			file.createNewFile(); // web_service/data.json*/

			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.println(replacedString);
			writer.close();
		} catch (IOException e) {
			System.out.println("Erreur crétion du JSON");
		}
	}
	
	
	
	/**
	 * Méthode récursive qui construit un json en fonction parent rentré
	 */
	public static void FinalRecursivee(int parent, Map<Integer, Integer> mapEnfantParent, Map<Integer, String> mapIdNodeName) {
		Set<Integer> keys = mapEnfantParent.keySet(); //key=enfant ,parent
		Set<Integer> keysNode = mapIdNodeName.keySet(); //key = id_node, name

		for (Integer key : keys) {
			//si parent == parent choisie (qui peut etre un enfant)
			if (mapEnfantParent.get(key) == parent) {
				finalJson += "{\"name\":\"" + mapIdNodeName.get(key) + "\",\"children\":[";
				FinalRecursivee(key, mapEnfantParent,mapIdNodeName);
			} else {

			}
		}
		finalJson = finalJson + "]}";
	}

}
