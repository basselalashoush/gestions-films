package bs.dao;

import java.sql.ResultSet;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import bs.model.Commande;
import bs.model.Film;
import bs.model.Format;
import bs.model.Ligne_Cmd;

public class Ligne_CmdDao implements IDao<Ligne_Cmd> {

	@Override
	public List<Ligne_Cmd> list() {
		List<Ligne_Cmd>cmds = new ArrayList<Ligne_Cmd>();
		Ligne_Cmd lign ;
		try {
			String sql = "SELECT * FROM ligne_cmd";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Commande cmd = new CommandeDao().find(rs.getInt("id_cmd"));
				Format format = new FormatDao().find(rs.getInt("id_format"));
				lign = new Ligne_Cmd(film, cmd,format, rs.getInt("qte"));
				cmds.add(lign);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cmds;
	}
	
	public List<Ligne_Cmd> list(int id, int bool) {
		List<Ligne_Cmd>cmds = new ArrayList<Ligne_Cmd>();
		Ligne_Cmd lign ;
		try {
			String sql = "SELECT ligne_cmds.id_cmd,ligne_cmds.id_film,ligne_cmds.id_format,ligne_cmds.qut FROM  ligne_cmds INNER JOIN commandes ON (ligne_cmds.id_cmd=commandes.id_cmd) WHERE commandes.id_user ='"+id+"' AND commandes.payer='"+bool+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Commande cmd = new CommandeDao().find(rs.getInt("id_cmd"));
				Format format = new FormatDao().find(rs.getInt("id_format"));
				lign = new Ligne_Cmd(film, cmd,format, rs.getInt("qut"));
				cmds.add(lign);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return cmds;
	}

	@Override
	public Ligne_Cmd find(int id) {
		Ligne_Cmd lign = null ;
		try {
			String sql = "SELECT * FROM ligne_cmd WHERE id_cmd="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				Film film = new FilmDao().find(rs.getInt("id_film"));
				Commande cmd = new CommandeDao().find(rs.getInt("id_cmd"));
				Format format = new FormatDao().find(rs.getInt("id_format"));
				lign = new Ligne_Cmd(film, cmd,format, rs.getInt("qte"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lign;
	}

	@Override
	public boolean add(Ligne_Cmd item) {
		int resultat = 0;
		try {
			String sql = "INSERT INTO ligne_cmds (id_cmd,id_film,id_format,qut) VALUES ('"+item.getCde().getId_cmd()+"','"+item.getFilm().getId_film()+"','"+item.getFormat().getId_format()+"','"+item.getQte()+"')";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Ligne_Cmd item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM ligne_cmds WHERE id_cmd='"+item.getCde().getId_cmd()+"'AND id_film='"+item.getFilm().getId_film()+"'AND id_format='"+item.getFormat().getId_format()+"'";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Ligne_Cmd item) {
		int resultat = 0;
		try {
			String sql = "UPDATE ligne_cmds SET id_format = '"+item.getFormat().getId_format()+"', qut = '"+item.getQte()+"' WHERE id_cmd ='"+item.getCde().getId_cmd()+"' AND  id_film ='"+item.getFilm().getId_film()+"'";
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
	
	
	
//	  private SessionFactory sessionFactory;
	  
//	  public void saveOrder(CartInfo cartInfo) {
//	        Session session = this.sessionFactory.getCurrentSession();
//	 
//	        int orderNum = this.getMaxOrderNum() + 1;
//	        Order order = new Order();
//	 
//	        order.setId(UUID.randomUUID().toString());
//	        order.setOrderNum(orderNum);
//	        order.setOrderDate(new Date());
//	        order.setAmount(cartInfo.getAmountTotal());
//	 
//	        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
//	        order.setCustomerName(customerInfo.getName());
//	        order.setCustomerEmail(customerInfo.getEmail());
//	        order.setCustomerPhone(customerInfo.getPhone());
//	        order.setCustomerAddress(customerInfo.getAddress());
//	 
//	        session.persist(order);
//	 
//	        List<CartLineInfo> lines = cartInfo.getCartLines();
//	 
//	        for (CartLineInfo line : lines) {
//	            OrderDetail detail = new OrderDetail();
//	            detail.setId(UUID.randomUUID().toString());
//	            detail.setOrder(order);
//	            detail.setAmount(line.getAmount());
//	            detail.setPrice(line.getProductInfo().getPrice());
//	            detail.setQuanity(line.getQuantity());
//	 
//	            String code = line.getProductInfo().getCode();
//	            Product product = this.productDAO.findProduct(code);
//	            detail.setProduct(product);
//	 
//	            session.persist(detail);
//	        }
//	 
//	        // Order Number!
//	        cartInfo.setOrderNum(orderNum);
//	        // Flush
//	        session.flush();
//	    }

}
