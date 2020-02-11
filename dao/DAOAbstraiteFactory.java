package dao;

public abstract class DAOAbstraiteFactory implements I_DAOFactory {
	
	private static I_DAOFactory instance ; 

	
	public static I_DAOFactory getInstance() {
		if(instance == null) {
			instance = new DAOFactory();
			return instance ; 
		}
		return instance;
		
	}
	
	@Override
	public abstract I_ProduitDAO createDaoProduit();

	@Override
	public abstract I_CatalogueDAO createCatalogueDao();

}
