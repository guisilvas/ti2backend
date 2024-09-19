// DAO - Data Acess Object
// Classe responsável pela conexão com o db
package dao;

import java.sql.*;
import java.util.Scanner;
import java.security.*;
import java.math.*;

public class DAO {
	protected Connection conexao;
	Scanner sc = new Scanner(System.in);
	
	public DAO() {
		conexao = null;
	}
	
	// Função para continuar com o programa
	private void pressEnterToContinue()
	{ 
	       System.out.println("\n\n\n\n\tPressione a tecla ENTER para continuar...\n\n\n\n");
	       try
	       {
	           sc.nextLine();
	       }  
	       catch(Exception e)
	       {}  
	}
	
	// Função que realiza a conexão com o db
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "test";
		int port = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + port + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("\n\n\n\n\tConection status: \t✅ Connection established with PostgreSQL!\n\n\n\n");
			pressEnterToContinue();
		} catch (ClassNotFoundException e) { 
			System.err.println("\n\n\n\nConnection NOT established with PostgreSQL -- Driver not found -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("\n\n\n\nConnection NOT established with PostgreSQL -- " + e.getMessage());
		}

		return status;
	}
	
	// Função que fecha a conexão com o db
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	// Função que faz uma criptografia da senha
	public static String toMD5(String password) throws Exception {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, m.digest()).toString(16);
	}
}
