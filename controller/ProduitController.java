package controller;

import entity.I_Produit;
import entity.Produit;
import exceptions.ExceptionProduitIllegal;
import view.TempPopup;

public class ProduitController {

	public ProduitController() {

	}

	public static boolean remove(String nom) {
		TempPopup.ShowTempPopup("L'article "+nom+" à bien été supprimé");
		return CatalogueController.remove(nom);
	}

	public static boolean addNew(String nom, String prixU, String quantite) {
		try {
			I_Produit produit = new Produit(nom,Double.parseDouble(prixU),Integer.parseInt(quantite));
			TempPopup.ShowTempPopup(quantite+"articles "+nom+" à bien été crée");
			return CatalogueController.add(produit);
		} catch (ExceptionProduitIllegal | NumberFormatException e) {
			TempPopup.ShowTempPopup("Article invalide");
			return false;
		}

	}


}
