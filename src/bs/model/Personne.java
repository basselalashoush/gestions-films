package bs.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Personne {
	
	private int id_personne;
	private String nom;
	private String prenom;
	private String description;
	private LocalDate date_naissance;
	
	public int getId_personne() {
		return id_personne;
	}
	public void setId_personne(int id_personne) {
		this.id_personne = id_personne;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
	}
	public Personne(String nom, String prenom, String description, LocalDate date_naissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.description = description;
		this.date_naissance = date_naissance;
	}
	

	public Personne(int id_personne, String nom, String prenom, String description, LocalDate date_naissance) {
		this.id_personne = id_personne;
		this.nom = nom;
		this.prenom = prenom;
		this.description = description;
		this.date_naissance = date_naissance;
	}
	@Override
	public String toString() {
		return "Personne [id_personne=" + id_personne + ", nom=" + nom + ", prenom=" + prenom + ", description="
				+ description + ", date_naissance=" + date_naissance + "]";
	}
	
	
	
	
	

}
