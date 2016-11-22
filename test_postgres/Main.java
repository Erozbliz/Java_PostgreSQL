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
			//recursiveSql(c);
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

		//POUR LES COMPOSITION
		try {
			/*PreparedStatement prepareQuery;
			prepareQuery = c.prepareStatement("SELECT * FROM composition WHERE id_root = ?");
			prepareQuery.setLong(1, numRoot);
			ResultSet rs = prepareQuery.executeQuery();*/

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

		//POUR LES NOEUDS
		try {
			/*PreparedStatement prepareQuery2;
			prepareQuery2 = c.prepareStatement("SELECT * FROM NODE");
			prepareQuery2.setLong(1, numRoot);
			ResultSet rs = prepareQuery2.executeQuery();*/
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

		//--------Test (pas bon)
		for (int i = 0; i < mapEnfantParent.size(); i++) {

		}

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

		recursiveFindLeaf(mapEnfantParent);
		recursiveFindLeafFinal(mapEnfantParent2);
		System.out.println("Operation done successfully");
	}

	//---------TEST 2
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
		// ------------
		
	}
	
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

	public static void recursiveSql(Connection c) {
		Statement stmt = null;
		try {
			String sqlre = "WITH RECURSIVE q AS (SELECT id_node_1, id_node FROM composition WHERE id_node_1 = 4 UNION SELECT m.id_node_1, m.id_node FROM composition m JOIN q ON q.id_node_1 = m.id_node) SELECT id_node_1, id_node FROM q;";

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sqlre);
			boolean lock = true;
			int oldIdNode = 1;
			//rs.next();
			//Integer TestType = rs.getInt(1);
			//System.out.println(TestType);
			while (rs.next()) {
				//Version de base
				int id_parent = rs.getInt("id_node");
				int id_enfant = rs.getInt("id_node_1");
				System.out.print(id_parent);
				System.out.print(" <-> " + id_enfant);
				System.out.println();
				//---------------

				/*if (lock == true) {
					oldIdNode = id_parent;
					lock = false;
					System.out.println("lock");
				}

				System.out.println(oldIdNode + "^^" + rs.getInt("id_node"));
				if (oldIdNode == rs.getInt("id_node")) {
					oldIdNode = rs.getInt("id_node");
					System.out.println("-------->" + oldIdNode);
				}*/

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("SelectNode done successfully");

	}



	// http://www.prodigyproductionsllc.com/articles/programming/recursively-iterate-hashmap-with-java/
	// http://stackoverflow.com/questions/9337536/iterate-recursively-through-deep-hashmap
	//-------TEST 3 récursive 
	/*private static void iterateHashMap(HashMap<Integer, Object> map) {
		Iterator it = map.keySet().iterator();
		System.out.println("-");
		while (it.hasNext()) {
			Integer key = (Integer) it.next();
			System.out.println("---" + map.get(key) + "<->" + key);
			if (map.get(key) instanceof HashMap) {
				iterateHashMap((HashMap) map.get(key));
			}
		}
	}*/

}
