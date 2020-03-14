package dao;

import java.sql.Connection;

public abstract class DAOAbstraiteFactory  {
	
	private static DAOAbstraiteFactory instance ; 

	
	public static DAOAbstraiteFactory getInstance() {
		if(instance == null) {
			instance = new DAOFactory();
			return instance ; 
		}
		return instance;
		
	}
	

	public abstract I_CatalogueDAO createCatalogueDao();

	public I_ProduitDAO createDaoProduit(Connection cn) {
		// TODO Auto-generated method stub
		return null;
	}




	public I_ProduitDAO createDaoProduit() {
		// TODO Auto-generated method stub
		return null;
	}

}
