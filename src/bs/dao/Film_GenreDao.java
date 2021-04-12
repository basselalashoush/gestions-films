package bs.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bs.model.Film;
import bs.model.Film_Genre;
import bs.model.Genre;

public class Film_GenreDao implements IDao<Film_Genre>{

	@Override
	public List<Film_Genre> list() {
		List<Film_Genre>fgenres = new ArrayList<Film_Genre>();
		Film_Genre fg;
		try {
			String sql = "SELECT *FROM film_genre";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Genre genre = new GenreDao().find(rs.getInt("id_genre"));
				fg = new Film_Genre(film, genre);
				fgenres.add(fg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fgenres;
	}

	@Override
	public Film_Genre find(int id) {
		Film_Genre fg = null;
		try {
			String sql = "SELECT *FROM film_genre WHERE id_film = "+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Genre genre = new GenreDao().find(rs.getInt("id_genre"));
				fg = new Film_Genre(film, genre);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fg;
	}

	@Override
	public boolean add(Film_Genre item) {
		int resultat = 0;
		try {
			String sql = "INSERT INT film_genre (id_film,id_genre)VALUES ('"+item.getFilm().getId_film()+"','"+item.getGenre().getId()+"')";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Film_Genre item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM film_genre WHERE id_film="+item.getFilm().getId_film();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Film_Genre item) {
		int resultat = 0;
		try {
			String sql = "UPDATE film_genre SET id_film = '"+item.getFilm().getId_film()+"',id_genre = '"+item.getGenre().getId()+"'";
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
