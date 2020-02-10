package controller;

import java.util.List;

import entity.Catalogue;
import entity.I_Catalogue;
import entity.I_Produit;
import view.FenetrePrincipale;

public class CatalogueController {

	public static I_Catalogue cat;

	public CatalogueController(String nom) {
		cat= new Catalogue(nom);
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
	
	public static void main (String[] args) {
		FenetrePrincipale f = new FenetrePrincipale();
	}

}
