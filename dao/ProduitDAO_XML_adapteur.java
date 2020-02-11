package dao;

import java.util.List;

import entity.I_Produit;
import exceptions.ExceptionNomProduitIllegal;
import exceptions.ExceptionPrixProduitIllegal;
import exceptions.ExceptionQuantiteProduitIllegal;

public class ProduitDAO_XML_adapteur implements I_ProduitDAO {
	
	public ProduitDAO_XML daoXml ; 
	
	public ProduitDAO_XML_adapteur() {
		daoXml = new ProduitDAO_XML();
	}

	@Override
	public void openConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(I_Produit produit) {
		this.daoXml.creer(produit);
		
	}

	@Override
	public I_Produit find(String nom) {
		try {
			return this.daoXml.lire(nom);
		} catch (NumberFormatException | ExceptionNomProduitIllegal | ExceptionPrixProduitIllegal
				| ExceptionQuantiteProduitIllegal e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public List<I_Produit> findAll() {
		return this.daoXml.lireTous();
	}

	@Override
	public void remove(String nomProduit) {
		this.daoXml.supprimer(find(nomProduit));
		
	}

	@Override
	public boolean maj(I_Produit produit) {
		return this.daoXml.maj(produit);
	}
}
