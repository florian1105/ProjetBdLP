package entity;

import java.text.DecimalFormat;

import exceptions.*;

public class Produit implements I_Produit{

	private int quantiteStock; 
	private String nom; 
	private double prixUnitiareHT ;
	private static double tauxTVA =0.2;
	
	public Produit(String nom, double prixU, int quantite)
			throws ExceptionNomProduitIllegal, ExceptionPrixProduitIllegal, ExceptionQuantiteProduitIllegal {
		if (nom == null || nom.contentEquals("")) {
			throw new ExceptionNomProduitIllegal();
		} else if (prixU <= 0) {
			throw new ExceptionPrixProduitIllegal();
		} else if (quantite < 0) {
			throw new ExceptionQuantiteProduitIllegal();
		}
		
		this.nom = nom.trim().replace("	", " ");
		this.prixUnitiareHT = prixU;
		this.quantiteStock = quantite;
	}

	@Override
	public boolean ajouter(int quantite) {
		if (quantite <= 0) {
			return false; 
		}

		this.quantiteStock += quantite;

		return true;
	}

	@Override
	public boolean enlever(int quantite) {
		if (this.quantiteStock < quantite) {
			return false; 
		}

		this.quantiteStock -= quantite;

		return true;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitiareHT;
	}

	public static double getTauxTVA() {
		return tauxTVA;
	}


	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitiareHT+(this.prixUnitiareHT/100)*(Produit.getTauxTVA()*100);
	}

	@Override
	public double getPrixStockTTC() {
		return this.quantiteStock* this.getPrixUnitaireTTC();
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00"); 
		String pU =  df.format(this.getPrixUnitaireHT());
		String pT = df.format(this.getPrixUnitaireTTC());
		String s = this.nom + " - prix HT : " + pU + " € - prix TTC : " + pT + " € - quantité en stock : " + this.quantiteStock + "\n";

		return s.replace(".", ",");
	}
	
	

}
