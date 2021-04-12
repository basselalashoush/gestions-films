package bs.model;

public class Genre {
	
	private int id_genre;
	private String libelle;
	public int getId() {
		return id_genre;
	}
	public void setId(int id) {
		this.id_genre = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Genre(int id, String libelle) {
		this.id_genre = id;
		this.libelle = libelle;
	}
	
	public Genre(String libelle) {
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "Genre [id_genre=" + id_genre + ", libelle=" + libelle + "]";
	}
	
	

}
