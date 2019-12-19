package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.I_Produit;
import entity.Produit;
import exceptions.ExceptionNomProduitIllegal;
import exceptions.ExceptionPrixProduitIllegal;
import exceptions.ExceptionProduitNotFound;
import exceptions.ExceptionQuantiteProduitIllegal;

public class ProduitDAL {
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	public ProduitDAL() {
		this.openConnection();
	}
	
	public void openConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.cn = DriverManager.getConnection(
					"jdbc:oracle:thin:@gloin:1521:iut",
					"grauwinm",
					"1110017777M"
					);
			this.st = this.cn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
					);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public void closeConnection() {
		try {
			this.rs.close();
			this.st.close();
			this.cn.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public void create(I_Produit produit) {
		try {
			CallableStatement cst = cn.prepareCall("{ CALL addProduit(?, ?, ?); }");
			cst.setString(1, produit.getNom());
			cst.setDouble(2, produit.getPrixUnitaireHT());
			cst.setInt(3, produit.getQuantite());
			cst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public I_Produit find(String nom) {
		I_Produit produit = null;
		
		try {
			PreparedStatement pst = cn.prepareStatement("{ SELECT * FROM Produits WHERE nomProduit = ?; }");
			pst.setString(1, nom);
			this.rs = pst.executeQuery();
			if (!this.rs.next()) {
				throw new ExceptionProduitNotFound();
			}
			produit = new Produit(this.rs.getString(2), this.rs.getDouble(3), this.rs.getInt(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionProduitNotFound e) {
			// TODO
		} catch (ExceptionNomProduitIllegal | ExceptionPrixProduitIllegal | ExceptionQuantiteProduitIllegal e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produit;
	}
	
	public List<I_Produit> findAll() {
		List<I_Produit> produits = new ArrayList<I_Produit>();
		
		try {
			this.rs = this.st.executeQuery("SELECT * FROM Produits ORDER BY nomProduit;");
			while (this.rs.next()) {
				I_Produit produit = new Produit(this.rs.getString(2), this.rs.getDouble(3), this.rs.getInt(4));
				produits.add(produit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionNomProduitIllegal | ExceptionPrixProduitIllegal | ExceptionQuantiteProduitIllegal e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produits;
	}
	
	public void remove(I_Produit produit) {
		try {
			CallableStatement cst = cn.prepareCall("{ CALL removeProduit(?); }");
			cst.setString(1, produit.getNom());
			cst.execute();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
