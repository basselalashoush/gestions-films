package bs.model;

import java.util.List;

public class Acteur {
	
	private Film film;
	private Personne personne;
	private List<Film> films;
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	public List<Film> getFilms() {
		return films;
	}
	public void setFilms(List<Film> films) {
		this.films = films;
	}
	public Acteur(Film film, Personne personne, List<Film> films) {
		this.film = film;
		this.personne = personne;
		this.films = films;
	}
	
	public Acteur(Film film, Personne personne) {
		this.film = film;
		this.personne = personne;
	}
	@Override
	public String toString() {
		return "Acteur [film=" + film + ", personne=" + personne + ", films=" + films + "]";
	}
	
	
	
	

}
