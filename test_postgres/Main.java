package test_postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main test Postgress");
		Connection c = Connexion();
		try {
			SelectNode(c);
			SelectComposition(c);
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
				
				System.out.println(" id_node = " + id_node);
				System.out.println(" name = " + name);
				System.out.println(" tag = " + tag);
				System.out.println(" size = " + size);
				System.out.println(" id_root = " + id_root);
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

	public static void Select() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "root");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM opus_children;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

}
