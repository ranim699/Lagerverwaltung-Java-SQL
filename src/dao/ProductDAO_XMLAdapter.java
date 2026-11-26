package dao;

import java.util.List;

import domain.I_Product;

public class ProductDAO_XMLAdapter implements I_DAO<I_Product> {

	private ProductDAO_XML productXML;

	public ProductDAO_XMLAdapter() {
		super();
		productXML = new ProductDAO_XML();
	}

	@Override
	public boolean create(I_Product obj) {
		return productXML.create((I_Product) obj);
	}

	@Override
	public boolean update(I_Product p) throws StockQuantityException {
		if ((p.getQuantity() + p.getQuantity()) < 0) {
			throw (new StockQuantityException("Not enough stock."));
		}
		return productXML.update(p);
	}

	@Override
	public boolean delete(I_Product p) {
		return productXML.delete(p);
	}

	@Override
	public List<I_Product> findAll(String catalogName) {
		return productXML.readAll(catalogName);
	}

	@Override
	public I_Product findByAttribute(String column, Object value) {
		return null;
	}

	@Override
	public int countRows(String name) {
		return productXML.countRows(name);
	}

}
