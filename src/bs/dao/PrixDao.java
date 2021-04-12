package bs.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bs.model.Film;
import bs.model.Format;
import bs.model.Prix;

public class PrixDao implements IDao<Prix>{

	@Override
	public List<Prix> list() {
		List<Prix>px =new ArrayList<Prix>();
		Prix p;
		try {
			String sql = "SELECT * FROM prix";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Format format = new FormatDao().find(rs.getInt("id_format"));
				Film film = new FilmDao().find(rs.getInt("id_film"));
				p = new Prix(film, format, rs.getDouble("prix"));
				px.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return px;
	}

	@Override
	public Prix find(int id) {
		Prix p = null;
		try {
			String sql = "SELECT * FROM prix WHERE id_prix ="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Format format = new FormatDao().find(rs.getInt("id_format"));
				Film film = new FilmDao().find(rs.getInt("id_film"));
				p = new Prix(film, format, rs.getDouble("prix"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return p;
	}


	@Override
	public boolean add(Prix item) {
		int resultat = 0;
		try {
			String sql = "INSERT INTO prix (id_format,id_film,prix) VALUS ('"+item.getFormat().getId_format()+"','"+item.getFilm().getId_film()+"',"+item.getPrix()+")";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Prix item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM prix WHERE id_film ="+item.getFilm().getId_film() +"AND id_format ="+item.getFormat().getId_format();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Prix item) {
		int resultat = 0;
		try {
			String sql = "UPDATE prix SET id_format="+item.getFormat().getId_format()+"id_film="+item.getFilm().getId_film()+"prix="+item.getPrix();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}
	
	public Prix find(int id_film,int id_format) {
		Prix p = null;
		try {
			String sql = "SELECT * FROM prix WHERE id_film ='"+id_film+"' AND id_format ='"+id_format+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Format format = new FormatDao().find(rs.getInt("id_format"));
				Film film = new FilmDao().find(rs.getInt("id_film"));
				p = new Prix(film, format, rs.getDouble("prix"));
			}
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return p;
	}
	
	public Prix find(String type) {
		Prix p = null;
		try {
			String sql = "SELECT *  FROM prix INNER JOIN formats ON (prix.id_format=formats.id_format) WHERE formats.type = '"+type+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Format format = new FormatDao().find(rs.getInt("id_format"));
				Film film = new FilmDao().find(rs.getInt("id_film"));
				p = new Prix(film, format, rs.getDouble("prix"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return p;
	}

	@Override
	public String ucfirst(String chaine) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
