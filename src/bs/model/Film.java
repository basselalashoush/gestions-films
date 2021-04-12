package bs.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Film {
	
	private int id_film;
	private String titre;
	private LocalDate date_sorti;
	private String resume;
	private String photo;
	private String audio;
	private String sous_titre;
	private LocalTime duree;
	private Personne personne;
	private List<Acteur> acteurs;
	
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public int getId_film() {
		return id_film;
	}
	public void setId_film(int id_film) {
		this.id_film = id_film;
	}
	public LocalDate getDate_sorti() {
		return date_sorti;
	}
	public void setDate_sorti(LocalDate date_sorti) {
		this.date_sorti = date_sorti;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getSous_titre() {
		return sous_titre;
	}
	public void setSous_titre(String sous_titre) {
		this.sous_titre = sous_titre;
	}
	public LocalTime getDuree() {
		return duree;
	}
	public void setDuree(LocalTime duree) {
		this.duree = duree;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	public List<Acteur> getActeurs() {
		return acteurs;
	}
	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}
	public Film(int id_film, String titre, LocalDate date_sorti, String resume, String photo, String langues_audio,
			String sous_titre, LocalTime duree, Personne personne) {
		this.id_film = id_film;
		this.titre = titre;
		this.date_sorti = date_sorti;
		this.resume = resume;
		this.photo = photo;
		this.audio = langues_audio;
		this.sous_titre = sous_titre;
		this.duree = duree;
		this.personne = personne;
	}
	
	public Film(String titre, LocalDate date_sorti, String resume, String photo, String audio, String sous_titre,
			LocalTime duree, Personne personne) {
		this.titre = titre;
		this.date_sorti = date_sorti;
		this.resume = resume;
		this.photo = photo;
		this.audio = audio;
		this.sous_titre = sous_titre;
		this.duree = duree;
		this.personne = personne;
		
	}
	@Override
	public String toString() {
		return "Film [id_film=" + id_film + ", titre=" + titre + ", date_sorti=" + date_sorti + ", resume=" + resume
				+ ", photo=" + photo + ", audio=" + audio + ", sous_titre=" + sous_titre + ", duree="
				+ duree + ", personne=" + personne +  "]";
	}
	
	
	

}
