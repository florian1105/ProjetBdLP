package dao;

public class DAOFactory {

	public DAOFactory() {
		
	}
	
	public static I_ProduitDAO createDao() {
		return new ProduitDAO();
	}
	
	public static I_ProduitDAO createDaoXml() {
		return new ProduitDAO_XML_adapteur();
	}

}
