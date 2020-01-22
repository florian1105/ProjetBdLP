package dal;

import java.util.List;

import entity.I_Produit;

public interface I_ProduitDAL {

	void openConnection();

	void closeConnection();

	void create(I_Produit produit);

	I_Produit find(String nom);

	List<I_Produit> findAll();

	void remove(String nomProduit);

}