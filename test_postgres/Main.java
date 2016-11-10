package test_postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
				int id_root = rs.getInt("id_root");

				System.out.print(" id_node = " + id_node);
				System.out.print(" name = " + name);
				System.out.print(" tag = " + tag);
				System.out.print(" size = " + size);
				System.out.print(" id_root = " + id_root);
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
				Boolean niveau_un = rs.getBoolean("niveau_un");
				int root = rs.getInt("id_root");
				int id_parent = rs.getInt("id_node");
				int id_enfant = rs.getInt("id_node_1");

				System.out.print(" niveau_un = " + niveau_un);
				System.out.print(" root = " + root);
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

		//liste 
		ArrayList<Integer> listNodeDeRoot = new ArrayList<Integer>();
		//param1 = enfant , parent2 = parent
		Map<Integer, Integer> mapEnfantParent = new HashMap<Integer, Integer>();
		HashMap<Integer, Object> mapEnfantParent2 = new HashMap<Integer, Object>();
		ArrayList<Integer> listParent = new ArrayList<Integer>();
		ArrayList<Integer> listEnfant = new ArrayList<Integer>();

		//POUR LES COMPOSITION
		try {
			PreparedStatement prepareQuery;
			prepareQuery = c.prepareStatement("SELECT * FROM composition WHERE id_root = ?");
			prepareQuery.setLong(1, numRoot);
			ResultSet rs = prepareQuery.executeQuery();
			while (rs.next()) {
				Boolean niveau_un = rs.getBoolean("niveau_un");
				int root = rs.getInt("id_root");
				int id_parent = rs.getInt("id_node");
				int id_enfant = rs.getInt("id_node_1");

				if (niveau_un == true) {
					listNodeDeRoot.add(id_enfant);
				} else {
					listParent.add(id_parent);
					listEnfant.add(id_enfant);
					mapEnfantParent.put(id_enfant, id_parent);
					mapEnfantParent2.put(id_enfant, id_parent);
				}

				/*System.out.print(" niveau_un = " + niveau_un);
				System.out.print(" root = " + root);
				System.out.print(" id_parent = " + id_parent);
				System.out.print(" id_enfant = " + id_enfant);
				System.out.println();*/

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//POUR LES NOEUDS
		try {
			PreparedStatement prepareQuery2;
			prepareQuery2 = c.prepareStatement("SELECT * FROM NODE WHERE id_root = ?");
			prepareQuery2.setLong(1, numRoot);
			ResultSet rs = prepareQuery2.executeQuery();
			while (rs.next()) {
				int id_node = rs.getInt("id_node");
				String name = rs.getString("name");
				String tag = rs.getString("tag");
				int size = rs.getInt("size");
				int id_root = rs.getInt("id_root");

				System.out.print(" id_node = " + id_node);
				System.out.print(" name = " + name);
				System.out.print(" tag = " + tag);
				System.out.print(" size = " + size);
				System.out.print(" id_root = " + id_root);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//--------Test (pas bon)
		/*for (int i = 0; i < listNodeDeRoot.size(); i++) {
			int idMasterNode = listNodeDeRoot.get(i);
			System.out.println("-" + idMasterNode);
			for (int j = 0; j < listParent.size(); j++) {
				if (idMasterNode == listParent.get(j)) {
					System.out.println("--- " + listParent.get(j) + " - " + listEnfant.get(j));
				}
			}
		}*/

		//---------TEST 1
		/*Set<Integer> keys = mapEnfantParent.keySet(); //key=enfant ,parent
		for (int i = 0; i < listNodeDeRoot.size(); i++) {
			System.out.println("-" + listNodeDeRoot.get(i));
			for (Integer key : keys) {
				if (listNodeDeRoot.get(i) == mapEnfantParent.get(key)) {
					System.out.println("---" + mapEnfantParent.get(key) + "<->" + key);
				}
			}
		}*/

		//---------TEST 2
		for (int i = 0; i < listNodeDeRoot.size(); i++) {
			System.out.println("-" + listNodeDeRoot.get(i));
			recursiveFindLeaf(listNodeDeRoot.get(i), mapEnfantParent);
		}
		System.out.println("\n");
		iterateHashMap(mapEnfantParent2);

		System.out.println("Operation done successfully");
	}

	//---------TEST 2
	public static void recursiveFindLeaf(int numNodeFromRoot, Map<Integer, Integer> mapEnfantParent) {
		Set<Integer> keys = mapEnfantParent.keySet(); //key=enfant ,parent
		for (Integer key : keys) {
			if (numNodeFromRoot == mapEnfantParent.get(key)) {
				System.out.println("---" + mapEnfantParent.get(key) + "<->" + key);
				//mapEnfantParent.remove(key);
				//recursiveFindLeaf(numNodeFromRoot,mapEnfantParent);
			} else {
				//mapEnfantParent.remove(key);
				//recursiveFindLeaf(numNodeFromRoot,mapEnfantParent);
				//return;
			}
		}
	}

	// http://www.prodigyproductionsllc.com/articles/programming/recursively-iterate-hashmap-with-java/
	// http://stackoverflow.com/questions/9337536/iterate-recursively-through-deep-hashmap
	//-------TEST 3 r�cursive 
	private static void iterateHashMap(HashMap<Integer, Object> map) {
		Iterator it = map.keySet().iterator();
		System.out.println("-");
		while (it.hasNext()) {
			Integer key = (Integer) it.next();
			System.out.println("---" + map.get(key) + "<->" + key);
			if (map.get(key) instanceof HashMap){
				iterateHashMap((HashMap) map.get(key));
			}
		}
	}

}
