package controller;

import domain.I_Catalog;

public class StockController extends MainController {

	public StockController(I_Catalog selectedCatalog) {
		super(selectedCatalog);
	}

	public static String showStock() {
		return catalog.toString();
	}
}
