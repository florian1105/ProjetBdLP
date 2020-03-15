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
	public int findId(String nom) {
		try {
			PreparedStatement pst = cn.prepareStatement("SELECT idCatalogue FROM Catalogues WHERE nomCatalogue=?");
			pst.setString(1, nom);
			this.rs = pst.executeQuery();
			if(this.rs.next()) {
				return rs.getInt(1);
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return -1;
	}

	@Override
	public List<I_Catalogue> findAllInfos() {
		List<I_Catalogue> catalogues = new ArrayList<I_Catalogue>();
		try {
			this.rs = this.st.executeQuery("SELECT idCatalogue, nomCatalogue FROM Catalogues ORDER BY idCatalogue");
			while (this.rs.next()) {
				int idCat = this.rs.getInt(1);
				int numberOfProduct = this.getNumberOfProductOfCatalogue(idCat);

				I_Catalogue catalogue = new Catalogue(rs.getString(2), Integer.toString(numberOfProduct));
				catalogues.add(catalogue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return catalogues;
	}

	private int getNumberOfProductOfCatalogue(int idCatalogue) throws SQLException {
		PreparedStatement pst = cn.prepareStatement("SELECT COUNT(*) FROM Produits WHERE idCatalogue = ?");
		pst.setInt(1, idCatalogue);
		ResultSet rsNumberOfProduct = pst.executeQuery();

		if (!rsNumberOfProduct.next()) {
			return -1;
		}

		return rs.getInt(1);
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

	@Override
	public Connection getConnection() {
		return this.cn;
	}

	

	

}
