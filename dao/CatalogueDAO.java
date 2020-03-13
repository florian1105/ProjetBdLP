package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Catalogue;
import entity.I_Catalogue;
import entity.I_Produit;
import entity.Produit;
import exceptions.ExceptionNomProduitIllegal;
import exceptions.ExceptionPrixProduitIllegal;
import exceptions.ExceptionProduitNotFound;
import exceptions.ExceptionQuantiteProduitIllegal;

public class CatalogueDAO implements I_CatalogueDAO {

	private Connection cn;
	private Statement st;
	private ResultSet rs;

	public CatalogueDAO() {
		this.openConnection();
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
			this.st = this.cn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
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
	public void create(I_Catalogue catalogue) {
		try {
			CallableStatement cst = cn.prepareCall("{ CALL addCatalogue(?) }");
			cst.setString(1, catalogue.getNom());
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	}
	
	@Override
	public List<I_Catalogue> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public I_Catalogue find(String nom) {
		return null;

	}
	
	@Override
	public String findId(String nom) {
		try {
			CallableStatement cst = cn.prepareCall("{ SELECT idCatalogue FROM Catalogues WHERE nomCatalogue=? }");
			cst.setString(1, nom);
			cst.execute();
			if(this.rs.next()) {
				return rs.getString(2);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public List<I_Catalogue> findAllInfos() {
		List<I_Catalogue> catalogues = new ArrayList<I_Catalogue>();
		try {
			this.rs = this.st.executeQuery("SELECT nomCatalogue, COUNT(*) FROM Catalogues C JOIN Produits P ON C.idCatalogue=P.idCatalogue"
					+ " GROUP BY nomCatalogue");
			while (this.rs.next()) {
				I_Catalogue catalogue = new Catalogue(this.rs.getString(2),this.rs.getString(3));
				catalogues.add(catalogue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return catalogues;
	}

	@Override
	public void remove(String nomCatalogue) {
		try {
			CallableStatement cst = cn.prepareCall("{ CALL removeCatalogue(?) }");
			cst.setString(1, nomCatalogue);
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	

	

}