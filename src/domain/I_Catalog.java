package domain;

import java.util.List;

public interface I_Catalog {

	public abstract boolean addProduct(I_Product product);
	public abstract boolean addProduct(String name, double price, int qty);
	public abstract int addProducts(List<I_Product> l);
	public abstract boolean removeProduct(String name);
	public abstract I_Product getProductByName(String name);
	public abstract boolean buyStock(String productName, int purchasedQty);
	public abstract boolean sellStock(String productName, int soldQty);
	public abstract String[] getProductNames();
	public abstract String getCatalogName();
	public abstract void setCatalogName(String name);
	public abstract double getTotalAmountInclVAT();
	public abstract String toString();

	public abstract void clear();

}
