package domain;

import java.text.DecimalFormat;

public class Product implements I_Product {
	private int stockQuantity = 0;
	private String name = "Unknown";
	private double unitPriceExclVAT = 0;
	private static final float vatRate = (float) 0.2;
	private I_Catalog catalog;

	public Product(String name, double unitPriceExclVAT, int stockQuantity, I_Catalog catalog) {
		this.name = name;
		this.unitPriceExclVAT = unitPriceExclVAT;
		this.stockQuantity = stockQuantity;
		this.catalog = catalog;
	}

	@Override
	public boolean add(int purchasedQty) {
		stockQuantity = stockQuantity + purchasedQty;
		return true;
	}

	@Override
	public boolean remove(int soldQty) {
		boolean result = false;
		if (stockQuantity >= soldQty) {
			stockQuantity = stockQuantity - soldQty;
			result = true;
		}
		return result;
	}

	@Override
	public String getName() {
		return name.replace("	", " ").trim();
	}

	@Override
	public int getQuantity() {
		return stockQuantity;
	}

	@Override
	public double getUnitPriceExclVAT() {
		return unitPriceExclVAT;
	}

	@Override
	public double getUnitPriceInclVAT() {
		return unitPriceExclVAT * (1 + vatRate);
	}

	@Override
	public double getStockValueInclVAT() {
		return getUnitPriceInclVAT() * stockQuantity;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#0.00");
		return this.getName() + " - price excl. VAT: " + df.format(this.getUnitPriceExclVAT()) + " €" + " - price incl. VAT: "
				+ df.format(this.getUnitPriceInclVAT()) + " €" + " - quantity in stock: " + this.getQuantity() + "\n";

	}

	@Override
	public I_Catalog getCatalog() {
		return this.catalog;
	}

	@Override
	public void setQuantity(int qty) {
		this.stockQuantity = qty;
	}

	@Override
	public void setUnitPriceExclVAT(double priceExclVAT) {
		if (priceExclVAT > 0)
			this.unitPriceExclVAT = priceExclVAT;
	}

}
