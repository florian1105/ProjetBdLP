package controller;


import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.CatalogueDAO;
import dao.DAOAbstraiteFactory;
import dao.I_CatalogueDAO;
import dao.I_ProduitDAO;
import view.FenetreAccueil;
import view.FenetrePrincipale;
import entity.Catalogue;
import entity.I_Catalogue;

public class SelectionController {
	
	private static List<I_Catalogue> catalogues;
	private static DAOAbstraiteFactory daoFact = DAOAbstraiteFactory.getInstance();
	private static I_CatalogueDAO catDAO = daoFact.createCatalogueDao();
	
	public SelectionController() {
		catalogues = catDAO.findAllInfos();
	}
	
	public static String[] getAllCatallogue() {
		String[] s = new String[catalogues.size()];
		int index = 0;
		for (I_Catalogue catalogue : catalogues) {
			s[index] = catalogue.getNom();
			index++;
		}
		return s;
	}
	
	public static String[] getNomEtProduit() {
		String[] s = new String[catalogues.size()];
		int index = 0;
		for (I_Catalogue catalogue : catalogues) {
			s[index] = catalogue.getNom()+" : "+catalogue.nbProdInitial()+" produits";
			index++;
		}
		return s;
	}
	
	public static String[] addCatalogue(String nom) {
		I_Catalogue catalogue = new Catalogue(nom,"0");
		catDAO.create(catalogue);
		catalogues = catDAO.findAllInfos();
		return getNomEtProduit();
	}
	
	public static String[] remove(String nom) {
		catDAO.remove(nom);
		catalogues = catDAO.findAllInfos();
		return getNomEtProduit();
	}
	
	
	public static void changeCatalogue(String nom) {
		ProduitController.setProduitDAO(daoFact.createDaoProduit(catDAO.getConnection()));
		CatalogueController catalogueCtrl = new CatalogueController(catDAO.findId(nom), nom,catDAO);
		
		FenetrePrincipale principale = new FenetrePrincipale();
	}
	
	public static void main(String[] args) {
		SelectionController ctrl = new SelectionController();
		FenetreAccueil acceuil = new FenetreAccueil(ctrl.getAllCatallogue(),ctrl.getNomEtProduit(),ctrl);
		
	}

	

}
