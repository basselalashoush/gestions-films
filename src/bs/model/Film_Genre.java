package bs.model;

public class Film_Genre {

	private Film film;
	private Genre genre;
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public Film_Genre(Film film, Genre genre) {
		super();
		this.film = film;
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Film_Genre [film=" + film + ", genre=" + genre + "]";
	}
	
	
}
