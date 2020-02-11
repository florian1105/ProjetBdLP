package controller;

import dao.DAOFactory;
import dao.I_ProduitDAO;
import entity.I_Produit;
import entity.Produit;
import exceptions.ExceptionNomProduitIllegal;
import exceptions.ExceptionPrixProduitIllegal;
import exceptions.ExceptionQuantiteProduitIllegal;
import view.TempPopup;

public class ProduitController {
	private static I_ProduitDAO produitDAO = DAOFactory.createDao();

	public ProduitController() {

	}

	public static I_ProduitDAO getProduitDAO() {
		return produitDAO;
	}
	
	public static void hydrate() {
		CatalogueController.hydrate(produitDAO.findAll());
	}

	public static boolean remove(String nom) {
		produitDAO.remove(nom);
		TempPopup.ShowTempPopup("L'article "+nom+" à bien été supprimé");
		return CatalogueController.remove(nom);
	}

	public static boolean addNew(String nom, String prixU, String quantite) {
		try {
			I_Produit produit = new Produit(nom,Double.parseDouble(prixU.replace(",", ".")),Integer.parseInt(quantite));
			if (!CatalogueController.add(produit)) {
				throw new ExceptionNomProduitIllegal();
			}
			produitDAO.create(produit);
			TempPopup.ShowTempPopup(quantite+" articles "+nom+" à bien été crée");				
			return true;
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


	public static boolean maj(I_Produit produit) {
			return produitDAO.maj(produit);	
	}
	


}
