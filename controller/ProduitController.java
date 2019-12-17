package controller;

import entity.I_Produit;
import entity.Produit;
import exceptions.ExceptionProduitIllegal;
import view.TempPopup;

public class ProduitController {

	public ProduitController() {
		
	}
	
	public static void remove(String nom) {
		try {
			CatalogueController.remove(nom);
			TempPopup.ShowTempPopup("L'article"+nom+" à bien été supprimé");
		}catch(Exception e) {
			
		}
	}

	public static void addNew(String nom, double prixU, int quantite) {
		try {
			I_Produit produit = new Produit(nom,prixU,quantite);
			CatalogueController.add(produit);
			TempPopup.ShowTempPopup("L'article"+nom+" à bien été crée");
		} catch (ExceptionProduitIllegal e) {
			TempPopup.ShowTempPopup("Article invalide");
			e.printStackTrace();
		}
		
	}
	
}
