package bs.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bs.model.Genre;


public class GenreDao implements IDao<Genre>{

	@Override
	public List<Genre> list() {
		List<Genre> genres = new ArrayList<Genre>();
		Genre genre;
		try {
			String sql= "SELECT * FROM genres ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				 genre = new Genre(rs.getInt("id_genre"),rs.getString("libelle"));
				 genres.add(genre);
				 
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return genres;
	}

	@Override
	public Genre find(int id) {
		Genre genre =null;
		try {
			String sql= "SELECT * FROM genres WHERE id="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				 genre = new Genre(rs.getInt("id"),rs.getString("libelle"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return genre;
	}

	@Override
	public boolean add(Genre item) {
		if(find(ucfirst(item.getLibelle().toLowerCase()))!=null) {
			System.out.println("la Genre '"+item.getLibelle()+"' existe déjà");
			return false;
		}
		int resultat = 0;
		try {
			String sql = "INSERT INTO Genres(libelle)VALUES('"+ucfirst(item.getLibelle().toLowerCase())+"')";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Genre item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM genres WHERE id="+item.getId();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Genre item) {
		int resultat = 0;
		try {
			String sql = "UPDATE genres SET libelle ='"+ucfirst(item.getLibelle().toLowerCase())+"'"+"WHERE id="+item.getId();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}
	

	
	public Genre find(String libelle) {
		Genre genre =null;
		try {
			String sql= "SELECT * FROM genres WHERE libelle='"+libelle.toLowerCase()+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				 genre = new Genre(rs.getInt("id"),rs.getString("libelle"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return genre;
	}
	
	@Override
	public String ucfirst(String chaine) {
		return chaine.substring(0, 1).toUpperCase()+ chaine.substring(1).toLowerCase();
	}

	

}
