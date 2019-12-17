package controller;

import entity.Catalogue;
import entity.I_Catalogue;
import entity.I_Produit;

public class CatalogueController {
	
	public static I_Catalogue cat = new Catalogue();
	
	public CatalogueController() {
		// TODO Auto-generated constructor stub
	}

	public static void add(I_Produit produit) {
		cat.addProduit(produit);
		
	}
	
	public static I_Catalogue getCatalogue() {
		return cat; 
	}

	public static void remove(String nom) {
		cat.removeProduit(nom);
		
	}

}
