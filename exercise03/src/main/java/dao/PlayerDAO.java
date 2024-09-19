package dao;

import model.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO extends DAO {	
	public PlayerDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Player player) {
		boolean status = false;
		try {
			String sql = "INSERT INTO player (name, birthdate, federation, rating, titles) "
		               + "VALUES ('" + player.getName() + "', "
		               + " ? " + ", '" + player.getFederation() + "', " + player.getRating() + ", " + player.getTitles() +");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(1, Date.valueOf(player.getBirthdate()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public Player get(int id) {
		Player player = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM player WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 player = new Player(rs.getInt("id"), rs.getString("name"), rs.getDate("birthdate").toLocalDate(), 
	        			 			   rs.getString("federation"),
	        			 			   rs.getInt("rating"),
	        			 		   	   rs.getInt("titles"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return player;
	}
	
	public List<Player> get() {
		return get("");
	}

	public List<Player> getOrderByID() {
		return get("id");		
	}
	
	public List<Player> getOrderByName() {
		return get("name");		
	}
	
	public List<Player> getOrderByBirthdate() {
		return get("birthdate");		
	}
	
	public List<Player> getOrderByFederation() {
		return get("rating");		
	}
	
	public List<Player> getOrderByRating() {
		return get("rating");		
	}
	
	public List<Player> getOrderByTitles() {
		return get("titles");		
	}
	
	private List<Player> get(String orderBy) {
		List<Player> players = new ArrayList<Player>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM player" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Player p = new Player(rs.getInt("id"), rs.getString("name"), rs.getDate("birthdate").toLocalDate(), 
				        					rs.getString("federation"),
				        					rs.getInt("rating"),
				        					rs.getInt("titles"));
	            players.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return players;
	}
	
	public boolean update(Player player) {
		boolean status = false;
		try {  
			String sql = "UPDATE player SET name = '" + player.getName() + "', "
					   + "birthdate =  ?, " 
					   + "federation = '" + player.getFederation() + "',"
					   + "rating = " + player.getRating() + ","
					   + "titles = " + player.getTitles() + " WHERE id = " + player.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(1, Date.valueOf(player.getBirthdate()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM player WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}