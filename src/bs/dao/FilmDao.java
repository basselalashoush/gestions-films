package bs.dao;

import java.sql.ResultSet;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import bs.model.Film;
import bs.model.Personne;

public class FilmDao implements IDao<Film>{

	@Override
	public List<Film> list() {
		List<Film> films = new ArrayList<Film>();
		Film film;
		try {
			String sql= "SELECT * FROM films ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				 Personne personne = new PersonneDao().find(rs.getInt("id_personne"));
				 LocalDate date = LocalDate.parse(rs.getString("date_sorti"));
				 LocalTime duree = LocalTime.parse(rs.getString("duree"));
				 film = new Film(rs.getInt("id_film"),rs.getString("titre"),date,rs.getString("resume"),rs.getString("photo"),rs.getString("audio"),rs.getString("sous_titre"),duree,personne);
				 films.add(film);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return films;
	}

	@Override
	public Film find(int id) {
		Film film =null;
		try {
			String sql= "SELECT * FROM films WHERE id_film="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				Personne personne = new PersonneDao().find(rs.getInt("id_personne"));
				LocalDate date = LocalDate.parse(rs.getString("date_sorti"));
				LocalTime duree = LocalTime.parse(rs.getString("duree"));
				film = new Film(rs.getInt("id_film"),rs.getString("titre"),date,rs.getString("resume"),rs.getString("photo"),rs.getString("audio"),rs.getString("sous_titre"),duree,personne);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return film;
	}

	@Override
	public boolean add(Film item) {
		if(find(item.getTitre())!=null) {
			System.out.println("Film '"+item.getTitre()+"' existe déjà");
			return false;
		}
		int resultat = 0;
		try {
			String sql = "INSERT INTO films(titre,date_sorti,resume,photo,audio,sous_titre,duree,id_personne)VALUES('"+item.getTitre().toUpperCase()+"','"+item.getDate_sorti()+"','"+item.getResume()+"','"+item.getPhoto()+"','"+item.getAudio()+"','"+item.getSous_titre()+"','"+item.getDuree()+"',"+item.getPersonne().getId_personne()+")";
			System.out.println(sql);
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Film item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM films WHERE id_film="+item.getId_film();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Film item) {
		int resultat = 0;
		try {
			String sql = "UPDATE films SET titre ='"+item.getTitre()+"',date_sorti='"+item.getDate_sorti()+"',resume='"+item.getResume()+"',photo='"+item.getPhoto()+"',audio='"+item.getAudio()+"',sous_titre='"+item.getSous_titre()+"',duree='"+item.getDuree()+"',id_personne='"+item.getPersonne().getId_personne()+"'"+"WHERE id_film="+item.getId_film();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	public Film find(String titre) {
		Film film =null;
		try {
			String sql= "SELECT * FROM films WHERE titre='"+titre+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				Personne personne = new PersonneDao().find(rs.getInt("id_personne"));
				LocalDate date = LocalDate.parse(rs.getString("date_sorti"));
				LocalTime duree = LocalTime.parse(rs.getString("duree"));
				film = new Film(rs.getInt("id_film"),rs.getString("titre"),date,rs.getString("resume"),rs.getString("photo"),rs.getString("audio"),rs.getString("sous_titre"),duree,personne);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return film;
	}
	
	public Film find(int id, String type) {
		Film film =null;
		try {
			String sql= "SELECT films.`id_film`, films.`titre`, films.date_sorti,films.resume ,films.photo ,films.audio ,films.sous_titre,films.duree ,films.id_personne ,prix.prix,formats.id_format,formats.type  FROM films INNER JOIN prix ON (films.id_film=prix.id_film) INNER JOIN formats ON (prix.id_format=formats.id_format) WHERE formats.type ='"+type+"' AND films.id_film='"+id+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				Personne personne = new PersonneDao().find(rs.getInt("id_personne"));
				LocalDate date = LocalDate.parse(rs.getString("date_sorti"));
				LocalTime duree = LocalTime.parse(rs.getString("duree"));
				film = new Film(rs.getInt("id_film"),rs.getString("titre"),date,rs.getString("resume"),rs.getString("photo"),rs.getString("audio"),rs.getString("sous_titre"),duree,personne);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return film;
	}

	
	

	@Override
	public String ucfirst(String chaine) {
		return chaine.substring(0, 1).toUpperCase()+ chaine.substring(1).toLowerCase();
	}


}
