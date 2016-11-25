package test_postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main test Postgress");
		Connection c = Connexion();
		try {
			//SelectNode(c);
			//SelectComposition(c);
			SelectAll(c, 1);
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection Connexion() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "root");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;
	}

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
		System.out.println("SelectNode done successfully");
	}

	public static void SelectAll(Connection c, int numRoot) {

		//param1 = enfant , parent2 = parent
		Map<Integer, Integer> mapEnfantParent = new HashMap<Integer, Integer>();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		//recursiveFindLeaf(mapEnfantParent);
		System.out.println("\n---------------");
		//System.out.print("{'name':'"+1+"','children':[");
		FinalRecursivee(1,mapEnfantParent);
		String replacedString = finalJson.replace("}{", "},{");// a déplacer dans une nouvelle méthode <<<<<<
		System.out.println("\n"+replacedString);// a déplacer dans une nouvelle méthode <<<<<<
		//utilser http://www.jsoneditoronline.org/ 
	}


	

	
	/**
	 * Méthode récursive qui construit un json en fonction parent rentré
	 */
	static String finalJson ="{\"name\":\""+1+"\",\"children\":["; // a déplacer dans une nouvelle méthode <<<<<<
	public static void FinalRecursivee(int parent ,Map<Integer, Integer> mapEnfantParent){
		
		//System.out.print("{'name':'"+parent+"','children':[");
		//System.out.print("{'name':'"+parent);
		Set<Integer> keys = mapEnfantParent.keySet(); //key=enfant ,parent
		
		for (Integer key : keys) {
			//System.out.print("{'name':"+key+",'size':3000},");
			//si parent == parent choisie (qui peut etre un enfant)
			if( mapEnfantParent.get(key) == parent){
				//System.out.println(",{"+ key);
				//System.out.print("{'name':'"+key+",'size':3000},");
				//System.out.print("{'name':'"+key+"','children':[");
				finalJson += "{\"name\":\""+key+"\",\"children\":[";
				//int secondKey = key;
				//mapEnfantParent.
				FinalRecursivee(key,mapEnfantParent);
			}else{
				
			}
		}

		finalJson = finalJson +"]}";
		//System.out.print("]},");
	}


	/* ********************************
	 * 			POUR LES TESTS
	 * ********************************
	 */
	
	//TEST PAS BON
	public static void recursiveFindLeaf(Map<Integer, Integer> mapEnfantParent) {
		System.out.println("-------------recursiveFindLeaf-------------------");
		Set<Integer> keys = mapEnfantParent.keySet(); //key=enfant ,parent
		for (Integer key : keys) {
			System.out.println("---" + mapEnfantParent.get(key) + "<->" + key);

		}
		// { 1 :[{1,2,3}],2...
		int gardeMyVar = 1;
		System.out.print("{");
		for (Integer key : keys) {

			if (mapEnfantParent.get(key) == gardeMyVar) {
				System.out.print("{" + key + "}");
			} else {
				System.out.print(mapEnfantParent.get(key));
				gardeMyVar = mapEnfantParent.get(key);
			}
			//System.out.print(",{");

		}
		System.out.print("}");
	}
	
	//TEST PAS BON
	public static List<Object> recursiveFindLeafFinal(Map<String, Object> mapEnfantParent) {
	 	List<Object> retVal = new ArrayList<Object>();
	    for (Map.Entry<String, Object> entry : mapEnfantParent.entrySet()) {
	        Object value = entry.getValue();	
	        if (value instanceof Map) {
	            retVal.addAll(recursiveFindLeafFinal((Map) value));
	        } else {
	            retVal.add(value);
	        }
	    }
	    return retVal;
}
}
