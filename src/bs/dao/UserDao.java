package bs.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import bs.model.User;

public class UserDao implements IDao<User> {

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public User find(int id) {
		User user = null;
		try {
			String sql = "SELECT * FROM users WHERE id_user ="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				user = new User(rs.getInt("id_user"),rs.getString("nom"),rs.getString("role"),rs.getString("login"),rs.getString("password"));
			}
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return user;
	}
	
	public User find(String login, String password) {
		User user = null;
		try {
			String sql = "SELECT * FROM users WHERE login ='"+login+"' AND password = '"+password+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				user = new User(rs.getInt("id_user"),rs.getString("nom"),rs.getString("role"),rs.getString("login"),rs.getString("password"));
			}
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return user;
	}

	@Override
	public boolean add(User item) {
		int resultat = 0;
		try {
			String sql = "INSERT INTO users (nom,role,login,password) VALUES ('"+item.getNom()+"','"+item.getRole()+"','"+item.getLogin()+"','"+item.getPassword()+"')";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(User item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM users WHERE id_user = "+item.getId_user();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(User item) {
		int resultat = 0;
		try {
			String sql = "UPDATE users SET nom = '"+item.getNom()+"',role = '"+item.getRole()+"',login = '"+item.getLogin()+"',password = '"+item.getPassword()+"'";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public String ucfirst(String chaine) {
		// TODO Auto-generated method stub
		return null;
	}

}
