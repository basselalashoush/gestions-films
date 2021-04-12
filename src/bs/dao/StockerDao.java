package bs.dao;

import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bs.model.Format;
import bs.model.Film;
import bs.model.Stocker;

public class StockerDao implements IDao<Stocker>{

	@Override
	public List<Stocker> list() {
		List<Stocker>stocks = new ArrayList<Stocker>();
		Stocker stock;
		try {
			String sql = "SELECT *FROM stocker";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Format format = new FormatDao().find(rs.getInt("id_format"));
				stock = new Stocker(film, format, rs.getInt("qte"));
				stocks.add(stock);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stocks;
	}

	@Override
	public Stocker find(int id) {
		Stocker stock = null;
		try {
			String sql = "SELECT *FROM stocks WHERE id_stock="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Format format = new FormatDao().find(rs.getInt("id_format"));
				stock = new Stocker(film, format, rs.getInt("qte"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stock;
	}
	public Stocker find(int id_film,int id_format) {
		Stocker stock = null;
		try {
			String sql = "SELECT *FROM stocks WHERE id_film='"+id_film+"' AND id_format='"+id_format+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Format format = new FormatDao().find(rs.getInt("id_format"));
				stock = new Stocker(film, format, rs.getInt("qte"));
			}
		} catch (Exception e) {
			System.out.println("Error "+e);
		}
		return stock;
	}

	@Override
	public boolean add(Stocker item) {
		int resultat = 0;
		try {
			String sql = "INSERT INTO stocks (id_film,id_format,qte)VALUES ('"+item.getFilm().getId_film()+"','"+item.getFormat().getId_format()+"','"+item.getQte()+"')";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Stocker item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM stocker WHERE id_film ="+item.getFilm().getId_film();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Stocker item) {
		int resultat = 0;
		Stocker stock= this.find(item.getFilm().getId_film(), item.getFormat().getId_format());
		int qte= stock.getQte()- item.getQte();
		try {
			String sql = "UPDATE stocks SET qte='"+qte+"'WHERE id_film = '"+item.getFilm().getId_film()+"'AND id_format = '"+item.getFormat().getId_format()+"'";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}
	
	public boolean updateStock(Stocker item) {
		int resultat = 0;
		try {
			String sql = "UPDATE stocks SET qte='"+item.getQte()+"'WHERE id_film = '"+item.getFilm().getId_film()+"'AND id_format = '"+item.getFormat().getId_format()+"'";
			System.out.println(sql);
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
