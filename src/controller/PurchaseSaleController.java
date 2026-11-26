package controller;

import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.I_Catalog;
import domain.I_Product;

public class PurchaseSaleController extends MainController {

	public PurchaseSaleController(I_Catalog selectedCatalog) {
		super(selectedCatalog);
	}

	private static boolean validateUserInput(String txtQty) throws Exception {
		boolean result;
		if (txtQty.isEmpty()) {
			throw new Exception("Please enter a value!");
		} else if (!Pattern.matches("\\d+", txtQty)) {
			throw new Exception("Please enter a valid value (numbers only)!");
		} else {
			if (Integer.parseInt(txtQty) <= 0) {
				throw new Exception("Please enter a quantity greater than zero!");
			} else if (Integer.parseInt(txtQty) > 0) {
				result = true;
			} else {
				throw new Exception("An unexpected error occurred, please try again...");
			}
		}
		return result;
	}

	public static void purchaseProduct(JFrame frame, String selectedProduct, String txtQty) {
		try {
			if (validateUserInput(txtQty) == true) {
				int quantity = Integer.parseInt(txtQty);
				catalog.buyStock(selectedProduct, quantity);
				I_Product p = catalog.getProductByName(selectedProduct);

				if (productDao.update(p)) {
					throw new Exception(
						"Your purchase of: " + selectedProduct + " (x" + quantity + ") has been saved.");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void sellProduct(JFrame frame, String selectedProduct, String txtQty) {
		try {
			if (validateUserInput(txtQty) == true) {
				int quantity = Integer.parseInt(txtQty);
				if (!catalog.sellStock(selectedProduct, quantity)) {
					throw new Exception("Not enough stock.");
				}
				I_Product p = catalog.getProductByName(selectedProduct);

				if (productDao.update(p)) {
					throw new Exception(
							"Your sale of: " + selectedProduct + " (x" + quantity + ") has been saved.");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
