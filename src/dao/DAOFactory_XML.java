package dao;

import domain.I_Catalog;
import domain.I_Product;

public class DAOFactory_XML extends AbstractDAOFactory {

	public DAOFactory_XML() {
	}

	@Override
	public I_DAO<I_Product> createProductDAO() {
		return new ProductDAO_XMLAdapter();
	}

	@Override
	public I_DAO<I_Catalog> createCatalogDAO() {
		return new CatalogDAO_XML();
	}

}
