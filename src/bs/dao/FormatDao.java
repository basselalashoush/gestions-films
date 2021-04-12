package bs.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bs.model.Format;


public class FormatDao implements IDao<Format>{

	@Override
	public List<Format> list() {
		List<Format> formats = new ArrayList<Format>();
		Format format;
		try {
			String sql= "SELECT * FROM formats ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				format = new Format(rs.getInt("id_format"),rs.getString("type"));
				formats.add(format);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
				
			}
		return formats;
	}

	@Override
	public Format find(int id) {
		Format format = null;
		try {
			String sql= "SELECT * FROM formats WHERE id_format="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				format = new Format(rs.getInt("id_format"),rs.getString("type"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return format;
	}

	@Override
	public boolean add(Format item) {
		int resultat = 0;
		try {
			String sql = "INSERT INTO format (type)VALUS'"+item.getType()+"'";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Format item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM format WHERE id_format ='"+item.getId_format()+"' ";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Format item) {
		int resultat = 0;
		try {
			String sql = "UPDATE format SET type = '"+item.getType()+"'";
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

	public Object find(String type) {
		Format format = null;
		try {
			String sql= "SELECT * FROM formats WHERE type='"+type+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				format = new Format(rs.getInt("id_format"),rs.getString("type"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return format;
	}

}
