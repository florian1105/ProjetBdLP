package entity;

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
		return this.prixUnitiareHT+(this.prixUnitiareHT/100*Produit.getTauxTVA());
	}

	@Override
	public double getPrixStockTTC() {
		return this.quantiteStock*this.getPrixStockTTC();
	}

}
