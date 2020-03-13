package controller;


import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.CatalogueDAO;
import dao.I_CatalogueDAO;
import view.FenetreAccueil;
import view.FenetrePrincipale;
import entity.Catalogue;
import entity.I_Catalogue;

public class SelectionController {
	
	private List<I_Catalogue> catalogues;
	private static I_CatalogueDAO catDAO = new CatalogueDAO(); 
	
	public SelectionController() {
		this.catalogues = catDAO.findAllInfos();
	}
	
	public String[] getAllCatallogue() {
		String[] s = new String[catalogues.size()];
		int index = 0;
		for (I_Catalogue catalogue : catalogues) {
			s[index] = catalogue.getNom();
			index++;
		}
		return s;
	}
	
	public String[] getNomEtProduit() {
		String[] s = new String[catalogues.size()];
		int index = 0;
		for (I_Catalogue catalogue : catalogues) {
			s[index] = catalogue.getNom()+" : "+catalogue.nbProdInitial()+" produits";
			index++;
		}
		return s;
	}
	
	public static void addCatalogue(String nom) {
		I_Catalogue catalogue = new Catalogue(nom,"0");
		catDAO.create(catalogue);
	}
	
	public static void remove(String nom) {
		catDAO.remove(nom);
	}
	
	public static void changeCatalogue(String nom) {
		CatalogueController catalogueCtrl = new CatalogueController(nom);
		FenetrePrincipale principale = new FenetrePrincipale();
	}
	
	public static void main(String[] args) {
		SelectionController ctrl = new SelectionController();
		FenetreAccueil acceuil = new FenetreAccueil(ctrl.getAllCatallogue(),ctrl.getNomEtProduit(),ctrl);
		
	}

	

}
