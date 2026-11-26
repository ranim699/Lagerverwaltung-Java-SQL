package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.I_Catalog;
import domain.I_Product;
import domain.Product;

public class ProductController extends MainController {

	public ProductController(I_Catalog selectedCatalog) {
		super(selectedCatalog);
	}

	public static boolean addProduct(String productName, String productPrice, String productQty, JFrame frame) {
		if (productName == null) {
			JOptionPane.showMessageDialog(frame, "All fields must be filled in.", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			try {
				double price = Double.parseDouble(productPrice);
				int qty = Integer.parseInt(productQty);
				I_Product p = new Product(productName, price, qty, catalog);

				JOptionPane.showMessageDialog(frame, "The product " + productName + " has been saved.", "Add product", JOptionPane.INFORMATION_MESSAGE);
				return productDao.create(p) && catalog.addProduct(p);

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame, "Quantity and price must be positive numbers.", "Error", JOptionPane.WARNING_MESSAGE);
				System.err.println("Quantity and price must be positive numbers.");
				System.out.println("name=" + productName);
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(frame, "All fields must be filled in.", "Error", JOptionPane.WARNING_MESSAGE);
				System.err.println("All fields must be filled in.");
				System.out.println("name=" + productName);
			}
		}

		return false;
	}

	public static boolean deleteProduct(String productNameToDelete) {
		if (productNameToDelete == null) {
			System.err.println("No product selected");
			return false;
		} else {
			I_Product p = catalog.getProductByName(productNameToDelete);
			return productDao.delete(p) && catalog.removeProduct(productNameToDelete);
		}
	}

}
