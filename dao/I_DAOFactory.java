package dao;

public interface I_DAOFactory {
	
	
	
	public abstract I_ProduitDAO createDaoProduit();

	public abstract I_CatalogueDAO createCatalogueDao();
}
