package controller;

import entity.I_Produit;
import entity.Produit;
import exceptions.ExceptionNomProduitIllegal;
import exceptions.ExceptionPrixProduitIllegal;
import exceptions.ExceptionQuantiteProduitIllegal;
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
		} catch (ExceptionNomProduitIllegal e) {
			TempPopup.ShowTempPopup("Nom invalide");
			return false;
		} catch (ExceptionPrixProduitIllegal e) {
			TempPopup.ShowTempPopup("Prix invalide");
			return false;
		} catch (ExceptionQuantiteProduitIllegal e) {
			TempPopup.ShowTempPopup("Quantite invalide");
			return false;
		}catch (NumberFormatException e) {
			TempPopup.ShowTempPopup("Veuillez renseigner tous les champs");
			return false;
		}

	}


}
