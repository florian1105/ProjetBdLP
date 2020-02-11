package controller;

import java.util.List;

import entity.Catalogue;
import entity.I_Catalogue;
import entity.I_Produit;
import exceptions.ExceptionProduitNotFound;
import view.FenetrePrincipale;

public class CatalogueController {

	public static I_Catalogue cat = new Catalogue();

	public CatalogueController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void hydrate(List<I_Produit> produits) {
		cat.addProduits(produits);
	}

	public static boolean add(I_Produit produit) {
		return cat.addProduit(produit);

	}

	public static boolean add(String nomProduit,int quantite) {
		try {
			if (cat.acheterStock(nomProduit, quantite)) {
				return ProduitController.maj(cat.getProduitByName(nomProduit));
			}else {
				return false ; 
			}
		} catch (ExceptionProduitNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static I_Catalogue getCatalogue() {
		return cat; 
	}

	public static boolean remove(String nom) {
		return cat.removeProduit(nom);
	}

	public static boolean remove(String nom, int quantite) {
		try {
			if (cat.vendreStock(nom,quantite)) {
				return ProduitController.maj(cat.getProduitByName(nom));
			}else {
				return false ; 
			}
		} catch (ExceptionProduitNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		

	}
	
	public static void main (String[] args) {
		FenetrePrincipale f = new FenetrePrincipale();
	}

}
