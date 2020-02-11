package dao;

public class DAOFactory extends DAOAbstraiteFactory {
	
	
	@Override
	public I_ProduitDAO createDaoProduit() {
		return new ProduitDAO();
	}
	
	@Override
	public I_CatalogueDAO createCatalogueDao() {
		return new CatalogueDAO();
	}

}
