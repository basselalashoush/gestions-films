package bs.model;

import java.time.LocalDate;

public class Commande {
	
	private int id_cmd;
	private LocalDate date;
	private boolean payer;
	private int qte;
	private double total;
	private User user;
	public int getId_cmd() {
		return id_cmd;
	}
	public void setId_cmd(int id_cmd) {
		this.id_cmd = id_cmd;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public boolean isPayer() {
		return payer;
	}
	public void setPayer(boolean payer) {
		this.payer = payer;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Commande(int id_cmd, LocalDate date, boolean payer, int qte, double total, User user) {
		this.id_cmd = id_cmd;
		this.date = date;
		this.payer = payer;
		this.qte = qte;
		this.total = total;
		this.user = user;
	}
	
	public Commande( LocalDate date, boolean payer, int qte, double total, User user) {
		this.date = date;
		this.payer = payer;
		this.qte = qte;
		this.total = total;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Commande [id_cmd=" + id_cmd + ", date=" + date + ", payer=" + payer + ", qte=" + qte + ", total="
				+ total + ", user=" + user + "]";
	}
	
	

}
