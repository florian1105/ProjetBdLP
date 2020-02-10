package dao;

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
		// TODO Auto-generated method stub

	}

	@Override
	public I_Catalogue find(String nom) {
		return null ; 

	}

	@Override
	public String[] findAllInfos() {
		String[] catalogues;
		try {
			this.rs = this.st.executeQuery("SELECT nomCatalogue, COUNT(*) FROM CatalogueProduit"
					+ " GROUP BY nomCatalogue");
			while (this.rs.next()) {
				I_Catalogue catalogue = new Catalogue(this.rs.getString(2),this.rs.getString(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return catalogues;
	}

	@Override
	public void remove(String nomCatalogue) {
		// TODO Auto-generated method stub

	}

}
