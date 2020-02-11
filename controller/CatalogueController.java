package controller;

import java.util.List;

import dao.DAOAbstraiteFactory;
import dao.DAOFactory;
import dao.I_CatalogueDAO;
import dao.I_DAOFactory;
import dao.I_ProduitDAO;
import entity.Catalogue;
import entity.I_Catalogue;
import entity.I_Produit;
import view.FenetrePrincipale;

public class CatalogueController {
	private static I_DAOFactory daoFact = DAOAbstraiteFactory.getInstance();
	private static I_CatalogueDAO catDAO = daoFact.createCatalogueDao();
	public static I_Catalogue cat;

	public CatalogueController(String nom) {
		cat = new Catalogue(nom,"0");
		ProduitController.hydrate(catDAO.findId(nom));
		FenetrePrincipale f = new FenetrePrincipale();
	}
	
	public static void hydrate(List<I_Produit> produits) {
		cat.addProduits(produits);
	}

	public static boolean add(I_Produit produit) {
		return cat.addProduit(produit);

	}

	public static boolean add(String nomProduit,int quantite) {
		return cat.acheterStock(nomProduit, quantite);

	}

	public static I_Catalogue getCatalogue() {
		return cat; 
	}

	public static boolean remove(String nom) {
		return cat.removeProduit(nom);

	}

	public static boolean remove(String nom, int quantite) {
		return cat.vendreStock(nom,quantite);

	}
	
//	public static void main (String[] args) {
//		
//	}

}
