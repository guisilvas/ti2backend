package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO extends DAO {
    
    public UsuarioDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
    
    
    public boolean insert(Usuario usuario) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO \"user\" (code, login, password, gender) "
                       + "VALUES (" + usuario.getCode() + ", '" + usuario.getLogin() + "', '"  
                       + usuario.getPassword() + "', '" + usuario.getGender() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    
    public Usuario get(int code) {
        Usuario usuario = null;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM \"user\" WHERE code = " + code;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);    
            if (rs.next()) {            
                usuario = new Usuario(rs.getInt("code"), rs.getString("login"), rs.getString("password"), rs.getString("gender").charAt(0));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }
    
    
    public List<Usuario> get() {
        return get("");
    }

    
    public List<Usuario> getOrderByCode() {
        return get("code");        
    }
    
    
    public List<Usuario> getOrderByLogin() {
        return get("login");        
    }
    
    
    public List<Usuario> getOrderByGender() {
        return get("gender");        
    }
    
    
    private List<Usuario> get(String orderBy) {    
    
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM \"user\"" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while (rs.next()) {                
                Usuario u = new Usuario(rs.getInt("code"), rs.getString("login"), rs.getString("password"), rs.getString("gender").charAt(0));
                usuarios.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }


    public List<Usuario> getGenderMasculino() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM \"user\" WHERE gender LIKE 'M'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while (rs.next()) {                
                Usuario u = new Usuario(rs.getInt("code"), rs.getString("login"), rs.getString("password"), rs.getString("gender").charAt(0));
                usuarios.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }
    
    
    public boolean update(Usuario usuario) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE \"user\" SET login = '" + usuario.getLogin() + "', password = '"  
                       + usuario.getPassword() + "', gender = '" + usuario.getGender() + "'"
                       + " WHERE code = " + usuario.getCode();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public boolean delete(int code) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM \"user\" WHERE code = " + code;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    
    public boolean autenticar(String login, String password) {
        boolean resp = false;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM \"user\" WHERE login LIKE '" + login + "' AND password LIKE '" + password  + "'";
            
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            resp = rs.next();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }    
}
