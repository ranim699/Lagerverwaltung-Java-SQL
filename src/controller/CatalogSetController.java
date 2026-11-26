package controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import dao.AbstractDAOFactory;
import dao.I_DAO;
import domain.Catalog;
import domain.I_Catalog;

public class CatalogSetController {
	private static List<I_Catalog> catalogs;
	private static CatalogSetController instance = null;
	private static I_DAO<I_Catalog> catalogDAO;

	private CatalogSetController() {
		catalogDAO = AbstractDAOFactory.getInstance("XML").createCatalogDAO();
		catalogs = catalogDAO.findAll(null);
	}

	public static synchronized CatalogSetController getInstance() {
		if (instance == null) {
			instance = new CatalogSetController();
		}
		return instance;
	}

	public String[] getCatalogNames() {
		String[] catalogNames = new String[0];
		if (catalogs != null) {
			catalogNames = new String[catalogs.size()];
			int index = 0;
			for (Iterator<I_Catalog> i = catalogs.iterator(); i.hasNext();) {
				I_Catalog cat = i.next();
				catalogNames[index] = cat.getCatalogName();
				index++;
			}
			Arrays.sort(catalogNames);
		}
		return catalogNames;
	}

	public String[] getCatalogDetails() {
		String[] catalogDetails = new String[0];
		if (catalogs != null) {
			catalogDetails = new String[catalogs.size()];
			int index = 0;
			for (Iterator<I_Catalog> i = catalogs.iterator(); i.hasNext();) {
				I_Catalog cat = i.next();
				String catalogName = cat.getCatalogName();
				int productCount = catalogDAO.countRows(catalogName);
				catalogDetails[index] = catalogName + ": " + productCount + " products.";
				index++;
			}
			Arrays.sort(catalogDetails);
		}
		return catalogDetails;
	}

	public int getCatalogCount() {
		return catalogs.size();
	}

	public List<I_Catalog> getCatalogs() {
		return catalogs;
	}

	private I_Catalog findCatalog(String catalogName) {
		for (Iterator<I_Catalog> i = catalogs.iterator(); i.hasNext();) {
			I_Catalog cat = (I_Catalog) i.next();
			if (cat.getCatalogName() == catalogName) {
				return cat;
			}
		}
		return null;
	}

	public boolean addCatalog(String catalogName) {
		if (!containsCatalog(catalogName)) {
			I_Catalog c = new Catalog(catalogName);
			return catalogDAO.create(c) && catalogs.add(c);
		}
		return false;
	}

	private boolean containsCatalog(String catalogName) {
		for (Iterator<I_Catalog> i = catalogs.iterator(); i.hasNext();) {
			I_Catalog cat = (I_Catalog) i.next();
			if (cat.getCatalogName() == catalogName) {
				return true;
			}
		}
		return false;
	}

	public boolean deleteCatalog(String catalogName) {
		I_Catalog cat = findCatalog(catalogName);
		if (cat != null) {
			return catalogDAO.delete(cat) && catalogs.remove(cat);
		}
		return false;
	}

	public void openCatalogWindow(String selectedText) {
		I_Catalog c = findCatalog(selectedText);
		if (c != null) {
			new MainController(c);
		}
	}

	public static void disconnectDB() {
		AbstractDAOFactory.getInstance(null).disconnectDB();
	}
}
