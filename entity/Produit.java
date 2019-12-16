package entity;

import exceptions.*;

public class Produit implements I_Produit{

	private int quantiteStock; 
	private String nom; 
	private double prixUnitiareHT ;
	private static double tauxTVA =0.2;
	
	
	public Produit(String nom, double prixU, int quantite) throws ExceptionProduitIllegal {
		if(prixU<=0 || quantite < 0 || nom== null) {
			throw new ExceptionProduitIllegal();
		}
		this.nom=nom.trim();
		this.prixUnitiareHT=prixU;
		this.quantiteStock=quantite;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		if(qteAchetee<=0) {
			return false; 
		}
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

	@Override
	public String toString() {
		return this.nom+" - prix HT : "+this.prixUnitiareHT+" € - prix TTC : "+this.getPrixUnitaireTTC()+" € - quantité en stock : "+this.quantiteStock;
	}
	
	

}
