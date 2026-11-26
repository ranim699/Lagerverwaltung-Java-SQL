package domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import domain.Product;

public class Catalog implements I_Catalog {

	private List<I_Product> products;
	private String catalogName;

	public Catalog(String name) {
		this.catalogName = name;
		products = new ArrayList<I_Product>();
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public List<I_Product> getProducts() {
		return products;
	}

	public String toString() {
		String result = "";
		DecimalFormat df = new DecimalFormat("#0.00");

		for (I_Product p : products) {
			result += p.toString();
		}

		result += "\n" + "Total stock value incl. VAT: " + df.format(this.getTotalAmountInclVAT()) + " €";

		return result;
	}

	@Override
	public boolean addProduct(I_Product product) {
		boolean isInCatalog = false;
		boolean result = false;

		for (Iterator<I_Product> iterator = products.iterator(); iterator.hasNext();) {
			Product p = (Product) iterator.next();
			if (isInCatalog = (p.getName().equals(product.getName().replace("	", " ").trim()))) {
				return false;
			}
		}

		if (!isInCatalog && product != null) {
			if ((product.getUnitPriceExclVAT() != 0) && (product.getQuantity() >= 0)
					&& (product.getUnitPriceExclVAT() > 0)) {
				this.products.add((Product) product);
				result = true;
			}
		}

		return result;
	}

	@Override
	public boolean addProduct(String name, double price, int qty) {
		Product product = new Product(name.replace("	", " ").trim(), price, qty, this);
		return addProduct(product);
	}

	@Override
	public int addProducts(List<I_Product> l) {
		int addedCount = 0;
		if (l != null) {
			for (I_Product p : l) {
				if (this.addProduct(p)) {
					addedCount++;
				}
			}
		}
		return addedCount;
	}

	/**
	 * @param name
	 * @return the product in this catalog whose name equals the given name
	 */
	public I_Product getProductByName(String name) {
		for (I_Product p : this.products) {
			if (p.getName() == name) {
				return p;
			}
		}
		return null;
	}

	@Override
	public boolean removeProduct(String name) {
		I_Product productToRemove = this.getProductByName(name);
		if (productToRemove != null) {
			return this.products.remove(productToRemove);
		} else {
			return false;
		}
	}

	@Override
	public boolean buyStock(String productName, int purchasedQty) {
		boolean result = false;
		if (purchasedQty > 0) {
			for (Iterator<I_Product> i = products.iterator(); i.hasNext();) {
				I_Product product = i.next();
				if (product.getName() == productName) {
					product.add(purchasedQty);
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public boolean sellStock(String productName, int soldQty) {
		boolean result = false;
		for (Iterator<I_Product> i = products.iterator(); i.hasNext();) {
			I_Product product = i.next();
			if ((product.getName() == productName) && (soldQty > 0)) {
				if (product.getQuantity() >= soldQty) {
					product.remove(soldQty);
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public String[] getProductNames() {
		String[] productNames = new String[0];
		if (products != null) {
			productNames = new String[products.size()];
			int index = 0;
			for (Iterator<I_Product> i = products.iterator(); i.hasNext();) {
				I_Product product = i.next();
				productNames[index] = product.getName();
				index++;
			}
			Arrays.sort(productNames);
		}
		return productNames;
	}

	@Override
	public double getTotalAmountInclVAT() {
		double total = 0;
		for (Iterator<I_Product> i = products.iterator(); i.hasNext();) {
			I_Product product = i.next();
			total = total + product.getStockValueInclVAT();
		}

		BigDecimal bd = new BigDecimal(total);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		total = bd.doubleValue();
		return total;
	}

	@Override
	public void clear() {
		products = null;
	}

}
