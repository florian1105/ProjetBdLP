package dao;

import java.util.List;

import entity.I_Catalogue;

public interface I_CatalogueDAO {

	void openConnection();

	void closeConnection();

	void create(I_Catalogue catalogue);

	I_Catalogue find(String nom);

	List<I_Catalogue> findAll();

	void remove(String nomCatalogue);

	List<I_Catalogue> findAllInfos();

}