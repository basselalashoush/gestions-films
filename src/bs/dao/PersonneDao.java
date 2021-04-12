package bs.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bs.model.Personne;

public class PersonneDao implements IDao<Personne> {

	@Override
	// liste acteurs
	public List<Personne> list() {
		List<Personne>personnes = new ArrayList<Personne>();
		Personne personne;
		try {
			String sql = "SELECT * FROM personnes INNER JOIN acteurs ON (personnes.id_personne=acteurs.id_personne)";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				personne = new Personne(rs.getInt("id_personne"),rs.getString("nom"),rs.getString("prenom"), rs.getString("description"), date);
				personnes.add(personne);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return personnes;
	}
	
	public List<Personne> listActeursByFilm(int id_film) {
		List<Personne>personnes = new ArrayList<Personne>();
		Personne personne;
		try {
			String sql = "SELECT * FROM personnes INNER JOIN acteurs ON (personnes.id_personne=acteurs.id_personne) WHERE acteurs.id_film ='"+id_film+"' ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				personne = new Personne(rs.getInt("id_personne"),rs.getString("nom"),rs.getString("prenom"), rs.getString("description"), date);
				personnes.add(personne);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return personnes;
	}
	
	public List<Personne> getAll() {
		List<Personne>personnes = new ArrayList<Personne>();
		Personne personne;
		try {
			String sql = "SELECT * FROM personnes ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				personne = new Personne(rs.getInt("id_personne"),rs.getString("nom"),rs.getString("prenom"), rs.getString("description"), date);
				personnes.add(personne);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return personnes;
	}
	
	public List<Personne> listRialisateur() {
		List<Personne>personnes = new ArrayList<Personne>();
		Personne personne;
		try {
			//String sql = "SELECT * FROM `personnes` WHERE personnes.id_personne NOT IN (SELECT personnes.id_personne FROM `personnes` INNER JOIN acteurs ON (personnes.id_personne=acteurs.id_personne))";
			String sql = "SELECT DISTINCT personnes.id_personne, personnes.nom , personnes.prenom , personnes.description , personnes.date_naissance FROM `personnes` INNER JOIN films ON (personnes.id_personne=films.id_personne)";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				personne = new Personne(rs.getInt("id_personne"),rs.getString("nom"),rs.getString("prenom"), rs.getString("description"), date);
				personnes.add(personne);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return personnes;
	}

	@Override
	public Personne find(int id) {
		Personne personne = null;
		try {
			String sql = "SELECT * FROM personnes WHERE id_personne="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				personne = new Personne(rs.getInt("id_personne"),rs.getString("nom"),rs.getString("prenom"), rs.getString("description"),date);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return personne;
	}

	@Override
	public boolean add(Personne item) {
		if(find(item.getNom())==null) {
			System.out.println("La personne "+item.getNom()+" existe déjà dans la base de données !! ");
		}
		int resultat = 0;
		try {
			String sql = "INSERT INTO personnes (nom,prenom,description,date_naissance) VALUES ('"+item.getNom()+"','"+item.getPrenom()+"','"+item.getDescription()+"','"+item.getDate_naissance()+"')";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Personne item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM personnes WHERE id_personne="+item.getId_personne();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Personne item) {
		int resultat = 0;
		try {
			String sql = "UPDATE personnes SET nom ='"+item.getNom()+"', prenom = '"+item.getPrenom()+"',description ='"+item.getDescription()+"',date_naissance ='"+item.getDate_naissance()+"'";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}
	
	public Personne find(String nom) {
		Personne personne = null;
		try {
			String sql = "SELECT * FROM personnes WHERE nom='"+nom+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				personne = new Personne(rs.getInt("id_personne"),rs.getString("nom"),rs.getString("prenom"), rs.getString("description"),date);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return personne;
	}
	
	public Personne find(String nom,String prenom) {
		Personne personne = null;
		try {
			String sql = "SELECT * FROM personnes WHERE nom='"+nom+"' AND prenom = '"+prenom+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				personne = new Personne(rs.getInt("id_personne"),rs.getString("nom"),rs.getString("prenom"), rs.getString("description"),date);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return personne;
	}
	@Override
	public String ucfirst(String chaine) {
		// TODO Auto-generated method stub
		return null;
	}

}
