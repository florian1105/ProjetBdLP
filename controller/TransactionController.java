package controller;

import view.TempPopup;

public class TransactionController {

	public TransactionController() {
		// TODO Auto-generated constructor stub
	}

	public static boolean vente(String nom, int quantite) {
		if(ProduitController.removeQuantity(nom, quantite)) {
			TempPopup.ShowTempPopup(quantite+" articles "+nom+" ont bien été vendus");
			return true;
		}else {
			TempPopup.ShowTempPopup("quantité en stock insuffisante");
			return false;
		}


	}

	public static boolean achat(String nom, int quantite) {
		if(ProduitController.addQuantity(nom,quantite)) {
			TempPopup.ShowTempPopup(quantite+" articles "+nom+" ont bien été achetés");
			return true;
		}else {
			TempPopup.ShowTempPopup("quantite invalide");
			return false;
		}
	}

}
