package domain;

public interface I_Product {

	public abstract boolean add(int purchasedQty);
	public abstract boolean remove(int soldQty);
	public abstract String getName();
	public abstract I_Catalog getCatalog();
	public abstract int getQuantity();
	public abstract void setQuantity(int qty);
	public abstract double getUnitPriceExclVAT();
	public abstract void setUnitPriceExclVAT(double priceExclVAT);
	public abstract double getUnitPriceInclVAT();
	public abstract double getStockValueInclVAT();
	public abstract String toString();

}
