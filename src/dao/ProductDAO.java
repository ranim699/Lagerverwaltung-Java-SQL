package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Catalog;
import domain.I_Catalog;
import domain.I_Product;
import domain.Product;

public class ProductDAO implements I_DAO<I_Product> {

	private Connection connect;

	public ProductDAO() {
		super();
		this.connect = null;
	}

	public ProductDAO(Connection connect) {
		super();
		this.connect = connect;
	}

	@Override
	public boolean create(I_Product obj) {
		String productName = obj.getName();
		int stockQty = obj.getQuantity();
		double priceExclVAT = obj.getUnitPriceExclVAT();
		String productCatalogName = obj.getCatalog().getCatalogName();

		try {
			CallableStatement cst = connect.prepareCall("{call newProduct(?,?,?,?)}");
			cst.setString(1, productName);
			cst.setInt(2, stockQty);
			cst.setDouble(3, priceExclVAT);
			cst.setString(4, productCatalogName);
			cst.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(I_Product productToUpdate) throws StockQuantityException {
		String productName = productToUpdate.getName();
		String productCatalogName = productToUpdate.getCatalog().getCatalogName();
		int newQuantity = productToUpdate.getQuantity();
		I_Product p = findByAttribute("productName", productName);

		if ((p.getQuantity() + newQuantity) < 0) {
			throw (new StockQuantityException("Not enough stock."));
		}

		try {
			PreparedStatement pst = connect.prepareStatement(
					"UPDATE Products p SET stockQuantity = ? WHERE productName = ? AND p.catalogId = (SELECT c.catalogId FROM Catalog c WHERE catalogName = ?)");
			pst.setInt(1, newQuantity);
			pst.setString(2, productName);
			pst.setString(3, productCatalogName);
			pst.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(I_Product product) {
		String productName = product.getName();
		try {
			PreparedStatement pst = connect.prepareStatement("DELETE FROM Products WHERE productName = ?");
			pst.setString(1, productName);
			pst.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<I_Product> findAll(String catalogName) {
		List<I_Product> productList = new ArrayList<I_Product>();
		I_Product p;
		String productName;
		int stockQty;
		double priceExclVAT;
		I_Catalog catalog;

		try {
			PreparedStatement pst = connect.prepareStatement(
					"SELECT productName, stockQuantity, unitPriceExclVAT, c.catalogId FROM Products p JOIN Catalog c ON p.catalogId = c.catalogId WHERE catalogName = ?");
			pst.setString(1, catalogName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				productName = rs.getString(1);
				stockQty = rs.getInt(2);
				priceExclVAT = rs.getDouble(3);
				catalog = this.findCatalog(rs.getInt(4));
				p = new Product(productName, priceExclVAT, stockQty, catalog);
				productList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}

	@Override
	public I_Product findByAttribute(String column, Object value) {
		String productName;
		int stockQty;
		double priceExclVAT;
		I_Catalog catalog;
		try {
			PreparedStatement pst = connect.prepareStatement(
					"SELECT productName, stockQuantity, unitPriceExclVAT, catalogId FROM Products WHERE " + column + " = ?");
			pst.setObject(1, value);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				productName = rs.getString(1);
				stockQty = rs.getInt(2);
				priceExclVAT = rs.getInt(3);
				catalog = this.findCatalog(rs.getInt(4));
				return new Product(productName, priceExclVAT, stockQty, catalog);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	private I_Catalog findCatalog(int catalogId) {
		String catalogName;

		try {
			PreparedStatement pst = connect
					.prepareStatement("SELECT catalogName FROM Catalog WHERE catalogId = ?");
			pst.setInt(1, catalogId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				catalogName = rs.getString(1);
				I_Catalog c = new Catalog(catalogName);
				return c;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param catalogName the name of the catalog
	 * @return the number of products in the catalog
	 */
	@Override
	public int countRows(String catalogName) {
		try {
			PreparedStatement pst = connect.prepareStatement(
					"SELECT count(*) FROM Products p JOIN Catalog c ON c.catalogId = p.catalogId WHERE catalogName = ?");
			pst.setString(1, catalogName);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
