package dao;

public class DAOFactoryXML extends DAOAbstraiteFactory {

	@Override
	public I_ProduitDAO createDaoProduit() {
		return new ProduitDAO_XML_adapteur();
	}

	@Override
	public I_CatalogueDAO createCatalogueDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
