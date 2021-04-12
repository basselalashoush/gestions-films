package bs.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bs.model.Acteur;
import bs.model.Film;
import bs.model.Personne;

public class ActeurDao implements IDao<Acteur>{

	@Override
	public List<Acteur> list() {
		List<Acteur>acteurs = new ArrayList<Acteur>();
		Acteur acteur;
		try {
			String sql = "SELECT * FROM acteur ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Personne personne = new PersonneDao().find(rs.getInt("id_personne"));
				acteur = new Acteur(film, personne);
				acteurs.add(acteur);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return acteurs;
	}

	@Override
	public Acteur find(int id) {
		
		try {
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public Acteur find(int id_film,int id_personne) {
		Acteur acteur = null;
		try {
			String sql = "SELECT * FROM acteurs Where id_film ="+id_film+" AND id_personne ="+id_personne;
			Statement stmt = connection.createStatement();
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Personne personne = new PersonneDao().find(rs.getInt("id_personne"));
				acteur = new Acteur(film, personne);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return acteur;
	}

	@Override
	public boolean add(Acteur item) {
		int resultat = 0;
		try {
			String sql = "INSERT INTO acteurs (id_film,id_personne) VALUES ('"+item.getFilm().getId_film()+"','"+item.getPersonne().getId_personne()+"')";
			Statement stmt = connection.createStatement();
			System.out.println(sql);
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Acteur item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM acteurs WHERE id_film = '"+item.getFilm().getId_film()+"' AND id_personne = '"+item.getPersonne().getId_personne()+"'";
			Statement stmt = connection.createStatement();
			System.out.println(sql);
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Acteur item) {
		int resultat = 0;
		try {
			String sql = "";
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
