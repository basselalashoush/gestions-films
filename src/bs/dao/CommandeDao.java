package bs.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import bs.model.Commande;
import bs.model.Personne;
import bs.model.User;

public class CommandeDao implements IDao<Commande>{

	@Override
	public List<Commande> list() {
		List<Commande> cdes = new ArrayList<Commande>();
		Commande cde;
		try {
			String sql = "SELECT * FROM commande";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				User user= new UserDao().find(rs.getInt("id_user"));
				LocalDate date = LocalDate.parse(rs.getString("date"));
				cde = new Commande(date, rs.getBoolean("payer"), rs.getInt("qte"), rs.getDouble("total"), user);
				cdes.add(cde);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return cdes;
	}

	@Override
	public Commande find(int id) {
		Commande cde = null;
		try {
			String sql = "SELECT * FROM commandes WHERE id_cmd = "+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_cmd"));
				User user = new UserDao().find(rs.getInt("id_user"));
				cde = new Commande(rs.getInt("id_cmd"),date, rs.getBoolean("payer"),rs.getInt("qte_total"), rs.getDouble("prix_total"), user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cde;
	}

	@Override
	public boolean add(Commande item ) {
		int resultat = 0;
		try {
			double total = 0;
			// il faut calculer la total 
			String sql = "INSERT INTO commandes (date_cmd,payer,qte_total,prix_total,id_user)VALUES ('"+LocalDate.now()+"',false,'"+item.getQte()+"','"+total+"','"+item.getUser().getId_user()+"')";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}
	
	public boolean add(Commande item, double prix ) {
		int resultat = 0;
		try {
			String sql = "INSERT INTO `commandes`(`date_cmd`, `payer`, `qte_total`, `prix_total`, `id_user`) VALUES ('"+LocalDate.now()+"',false,'"+item.getQte()+"',"+prix+",'"+item.getUser().getId_user()+"')";
			System.out.println("hello");
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Commande item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM commandes WHERE id_cmd="+item.getId_cmd();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Commande item ) {
		int resultat = 0;
		try {
			String sql ="UPDATE commandes SET date_cmd='"+LocalDate.now()+"',payer = true,qte_total ='"+item.getQte()+"',prix_total ='"+item.getTotal()+"',id_user = '"+item.getUser().getId_user()+"'WHERE id_cmd='"+item.getId_cmd()+"'";
			System.out.println(sql);
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}
	public boolean updatePanier(Commande item ) {
		int resultat = 0;
		try {
			String sql ="UPDATE commandes SET date_cmd='"+LocalDate.now()+"',payer = false ,qte_total ='"+item.getQte()+"',prix_total ='"+item.getTotal()+"',id_user = '"+item.getUser().getId_user()+"'WHERE id_cmd='"+item.getId_cmd()+"'";
			System.out.println(sql);
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public String ucfirst(String chaine) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Commande getCommande() {
		Commande cde = null;
		try {
			String sql = "SELECT * FROM commandes WHERE id_cmd= LAST_INSERT_ID()"; 
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getString("date_cmd"));
				User user = new UserDao().find(rs.getInt("id_user"));
				cde = new Commande(rs.getInt("id_cmd"),date, rs.getBoolean("payer"),rs.getInt("qte_total"), rs.getDouble("prix_total"), user);
			}
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return cde;
	
	}
	public Commande lastCmd() {
		Commande cde =null;
		try {
			String sql = "select last_insert_id() from commandes";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int lastid = rs.getInt("id_cmd");
			cde = this.find(lastid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cde;
	}
}
