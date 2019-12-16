package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import exceptions.ExceptionProduitNotFound;

public class Catalogue implements I_Catalogue {
	
	private List<I_Produit> lesProduits;

	public Catalogue() {
		this.lesProduits = new ArrayList<I_Produit>();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if (this.contient(produit.getNom())) {
			return false;
		}
		
		return this.lesProduits.add(produit);
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		return this.addProduit(new Produits(nom, prix, qte));
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int nbAdded = 0;
		for (I_Produit produit : l) {
			if (this.addProduit(produit)) {
				nbAdded++;
			}
		}
		
		return nbAdded;
	}

	@Override
	public boolean removeProduit(String nom) {
		if (!this.contient(nom)) {
			return false;
		}
		
		ListIterator<I_Produit> it = this.lesProduits.listIterator();
		while (it.hasNext()) {
			if (it.next().getNom().equals(nom)) {
				it.remove();
			}
		}
		
		return true;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		I_Produit produit;
		
		try {
			produit = this.getProduitByName(nomProduit);
		} catch(ExceptionProduitNotFound e) {
			return false;
		}
		
		produit.ajouter(qteAchetee);
		
		return true;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		I_Produit produit;
		
		try {
			produit = this.getProduitByName(nomProduit);
		} catch(ExceptionProduitNotFound e) {
			return false;
		}
		
		if (produit.getQuantite() < qteVendue) {
			return false;
		}
		
		produit.enlever(qteVendue);
		
		return true;
	}

	@Override
	public String[] getNomProduits() {
		String[] nomProduits = new String[this.lesProduits.size()];
		for (int i = 0; i < this.lesProduits.size(); i++) {
			nomProduits[i] = this.lesProduits.get(i).getNom();
		}
		
		return nomProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		int montantTotal = 0;
		for (I_Produit produit : lesProduits) {
			montantTotal += produit.getPrixStockTTC();
		}
		
		return montantTotal;
	}

	@Override
	public void clear() {
		this.lesProduits.clear();
	}
	
	private I_Produit getProduitByName(String nom) throws ExceptionProduitNotFound {
		I_Produit produit = null;
		boolean isFound = false;
		ListIterator<I_Produit> it = this.lesProduits.listIterator();
		while (!isFound && it.hasNext()) {
			produit = it.next();
			if (produit.getNom().equals(nom)) {
				isFound = true;
			}
		}
		
		if (!isFound) {
			throw new ExceptionProduitNotFound();
		}
		
		return produit;
	}
	
	private boolean contient(String nom) {
		boolean existe = false;
		ListIterator<I_Produit> it = this.lesProduits.listIterator();
		while (!existe && it.hasNext()) {
			if (it.next().getNom().equals(nom)) {
				existe = true;
			}
		}
		
		return existe;
	}
}