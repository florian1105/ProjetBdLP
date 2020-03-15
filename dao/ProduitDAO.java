package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.CatalogueController;
import controller.ProduitController;
import controller.SelectionController;
import entity.I_Produit;
import entity.Produit;
import exceptions.ExceptionNomProduitIllegal;
import exceptions.ExceptionPrixProduitIllegal;
import exceptions.ExceptionProduitNotFound;
import exceptions.ExceptionQuantiteProduitIllegal;

public class ProduitDAO implements I_ProduitDAO {
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	public ProduitDAO(Connection cn) {
		this.cn=cn;
		try {
			this.st = this.cn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void openConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.cn = DriverManager.getConnection(
					"jdbc:oracle:thin:@162.38.222.149:1521:iut",
					"grauwinm",
					"1110017777M"
					);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void closeConnection() {
		try {
			this.cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void create(I_Produit produit) {
		try {
			CallableStatement cst = cn.prepareCall("{ CALL addProduit(?, ?, ?, ?) }");
			cst.setString(1, produit.getNom());
			cst.setDouble(2, produit.getPrixUnitaireHT());
			cst.setInt(3, produit.getQuantite());
			int idCat = CatalogueController.getCatDAO().findId(CatalogueController.getCatalogue().getNom());
			cst.setInt(4, idCat);
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public I_Produit find(String nom) {
		I_Produit produit = null;
		
		try {
			PreparedStatement pst = cn.prepareStatement("{ SELECT * FROM Produits WHERE nomProduit = ? }");
			pst.setString(1, nom);
			this.rs = pst.executeQuery();
			if (!this.rs.next()) {
				throw new ExceptionProduitNotFound();
			}
			produit = new Produit(this.rs.getString(2), this.rs.getDouble(3), this.rs.getInt(4));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ExceptionProduitNotFound e) {
			e.printStackTrace();
		} catch (ExceptionNomProduitIllegal | ExceptionPrixProduitIllegal | ExceptionQuantiteProduitIllegal e) {
			e.printStackTrace();
		}
		
		return produit;
	}
	
	@Override
	public List<I_Produit> findAll() {
		List<I_Produit> produits = new ArrayList<I_Produit>();
		
		try {
			this.rs = this.st.executeQuery("SELECT * FROM Produits ORDER BY nomProduit");
			while (this.rs.next()) {
				I_Produit produit = new Produit(this.rs.getString(2), this.rs.getDouble(3), this.rs.getInt(4));
				produits.add(produit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ExceptionNomProduitIllegal | ExceptionPrixProduitIllegal | ExceptionQuantiteProduitIllegal e) {
			e.printStackTrace();
		}
		
		return produits;
	}
	
	@Override
	public List<I_Produit> findAllByIdCat(int idCatalogue) {
		List<I_Produit> produits = new ArrayList<I_Produit>();
		
		try {
<<<<<<< HEAD
			PreparedStatement pst = cn.prepareStatement("SELECT * FROM Produits WHERE idCatalogue=? ORDER BY nomProduit");
=======
			PreparedStatement pst = cn.prepareStatement("SELECT * FROM Produits WHERE idCatalogue = ? ORDER BY nomProduit ");
>>>>>>> f057207388438ba5ae458523b0d8bab76596d4ae
			pst.setInt(1, idCatalogue);
			this.rs = pst.executeQuery();
			while (this.rs.next()) {
				I_Produit produit = new Produit(this.rs.getString(2), this.rs.getDouble(3), this.rs.getInt(4));
				produits.add(produit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ExceptionNomProduitIllegal | ExceptionPrixProduitIllegal | ExceptionQuantiteProduitIllegal e) {
			e.printStackTrace();
		}
		
		return produits;
	}
	
	@Override
	public void remove(String nomProduit) {
		try {
			CallableStatement cst = cn.prepareCall("{ CALL removeProduit(?) }");
			cst.setString(1, nomProduit);
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
