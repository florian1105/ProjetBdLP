package entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Produit implements I_Produit{

	private int quantiteStock; 
	private String nom; 
	private double prixUnitiareHT ;
	private static double tauxTVA =0.2;
	
	
	public Produit(String nom, double prixU, int quantite) {
		this.nom=nom;
		this.prixUnitiareHT=prixU;
		this.quantiteStock=quantite;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		this.quantiteStock+=qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		if(this.quantiteStock-qteVendue<0) {
			return false; 
		}else {
			this.quantiteStock-=qteVendue;
			return true; 
		}
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
		double prix = this.prixUnitiareHT+(this.prixUnitiareHT/100)*(Produit.getTauxTVA()*100);
		return prix;
	}

	@Override
	public double getPrixStockTTC() {
			double prix = this.quantiteStock*this.getPrixUnitaireTTC();
			return prix;
	}

}
