package bs.model;

public class Stocker {
	
	private Film film;
	private Format format;
	private int qte;
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
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public Stocker(Film film, Format format, int qte) {
		this.film = film;
		this.format = format;
		this.qte = qte;
	}
	@Override
	public String toString() {
		return "Stocker [film=" + film + ", format=" + format + ", qte=" + qte + "]";
	}
	
	

}
