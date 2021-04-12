package bs.model;

public class Ligne_Cmd {

	private Film film;
	private Commande cde;
	private int qte;
	private Format format;
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Commande getCde() {
		return cde;
	}
	public void setCde(Commande cde) {
		this.cde = cde;
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
	public Ligne_Cmd(Film film, Commande cde , Format format, int qte) {
		this.film = film;
		this.cde = cde;
		this.qte = qte;
		this.format = format;
	}
	@Override
	public String toString() {
		return "Ligne_Cmd [film=" + film + ", cde=" + cde + ", qte=" + qte + ", format=" + format + "]";
	}
	
	
	
	
	
}
