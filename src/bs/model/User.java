package bs.model;

public class User {
	
	private int id_user;
	private String nom;
	private String role;
	private String login;
	private String password;
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(int id_user, String nom, String role, String login, String password) {
		this.id_user = id_user;
		this.nom = nom;
		this.role = role;
		this.login = login;
		this.password = password;
	}
	
	public User(String nom, String role, String login, String password) {
		this.nom = nom;
		this.role = role;
		this.login = login;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", nom=" + nom + ", role=" + role + ", login=" + login + ", password="
				+ password + "]";
	}
	
	

}
