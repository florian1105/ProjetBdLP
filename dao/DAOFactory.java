package dao;

import java.sql.Connection;

public class DAOFactory extends DAOAbstraiteFactory {
	
	
	@Override
	public I_ProduitDAO createDaoProduit(Connection cn) {
		return new ProduitDAO(cn);
	}
	
	@Override
	public I_CatalogueDAO createCatalogueDao() {
		return new CatalogueDAO();
	}


}
