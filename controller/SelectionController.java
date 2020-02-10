package controller;


import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.FenetreAccueil;
import entity.I_Catalogue;

public class SelectionController {
	
	private List<I_Catalogue> catalogues;
	
	public SelectionController() {
		this.catalogues = new ArrayList<I_Catalogue>();
		
	}
	
	public String[] getAllCatallogue() {
		List<String> s = new ArrayList<String>() ; 
		for (I_Catalogue catalogue : catalogues) {
			s.add(catalogue.getNom());
		}
		 String[] nomCat = (String[]) s.toArray();
		return nomCat ; 
	}
	
	public String[] getNomEtProduit() {
		List<String> s = new ArrayList<String>() ; 
		for (I_Catalogue catalogue : catalogues) {
			s.add(catalogue.getNom()+" : "+catalogue.getNbProduit()+" produits" );
		}
		 String[] nomCatEtProd = (String[]) s.toArray();
		return nomCatEtProd ; 
	}
	
	public static void main(String[] args) {
		SelectionController ctrl = new SelectionController();
		FenetreAccueil acceuil = new FenetreAccueil(ctrl.getAllCatallogue(),ctrl.getNomEtProduit());
		
	}

}
