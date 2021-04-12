package bs.model;

public class Prix {
 
	private Film film;
	private Format format;
	private double prix;
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Prix(Film film, Format format, double prix) {
		this.film = film;
		this.format = format;
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "Prix [film=" + film + ", format=" + format + ", prix=" + prix + "]";
	}
	
	
	
}
