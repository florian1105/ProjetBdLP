package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import entity.Catalogue;
import entity.I_Catalogue;
import entity.I_Produit;
import entity.Produit;

public class CatalogueDAO_XML implements I_CatalogueDAO {

	private String uri = "C:/Catalogues.xml";
	private Document doc;
	
	public CatalogueDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
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
	public void create(I_Catalogue catalogue) {
		try {
			Element root = doc.getRootElement();
			Element cat = new Element("catalogue");
			cat.setAttribute("nom", catalogue.getNom());
			Element prod = new Element("listeProduit");
			cat.setContent(prod);

			 sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
		}
	}

	@Override
	public I_Catalogue find(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<I_Catalogue> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String nomCatalogue) {
		try {
			Element root = doc.getRootElement();
			Element cat = chercheCatalogue(nomCatalogue);
			if (cat != null) {
				root.removeContent(cat);
				sauvegarde();
			}
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
		}

	}

	@Override
	public List<I_Catalogue> findAllInfos() {
		List<I_Catalogue> l = new ArrayList<I_Catalogue>();
		Element root = doc.getRootElement();
		List<Element> lCat = root.getChildren("catalogue");
		for (Element cat : lCat) {
			String nomC = cat.getAttributeValue("nom");
			String nbProd = getNbProd(nomC);
			l.add(new Catalogue(nomC,nbProd));
		}
		return null;
	}
	
	private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}
	
	private String getNbProd(String nomCat) {
		Element cat = chercheCatalogue(nomCat);
		List<Element> lProd = cat.getChildren("produits");
		int nbProd = lProd.size();
		return Integer.toString(nbProd);
	}
	
	private Element chercheCatalogue(String nom) {
		Element root = doc.getRootElement();
		List<Element> lCat = root.getChildren("catalogue");
		int i = 0;
		while (i < lCat.size() && !lCat.get(i).getAttributeValue("nom").equals(nom))
			i++;
		if (i < lCat.size())
			return lCat.get(i);
		else
			return null;
	}


	@Override
	public int findId(String nom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}
