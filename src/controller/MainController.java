package controller;

import dao.I_DAO;
import gui.MainWindow;

import dao.AbstractDAOFactory;
import domain.I_Catalog;
import domain.I_Product;

public class MainController {
	protected static I_Catalog catalog;
	protected static I_DAO<I_Product> productDao;

	public MainController(I_Catalog selectedCatalog) {
		catalog = selectedCatalog;

		// Line to use for the relational database
		productDao = AbstractDAOFactory.getInstance("DB").createProductDAO();

		// Line to use for the XML storage
		productDao = AbstractDAOFactory.getInstance("XML").createProductDAO();

		catalog.addProducts(productDao.findAll(catalog.getCatalogName()));
		new MainWindow();
	}

	public static String[] getProductNames() {
		return MainController.catalog.getProductNames();
	}
}
