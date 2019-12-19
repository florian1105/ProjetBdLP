package controller;

import java.util.List;

import entity.Catalogue;
import entity.I_Catalogue;
import entity.I_Produit;

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

}
