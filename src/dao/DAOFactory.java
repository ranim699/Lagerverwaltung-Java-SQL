package dao;

import java.sql.Connection;

import db.DBConnection;
import domain.I_Catalog;
import domain.I_Product;

public class DAOFactory extends AbstractDAOFactory {
	private static final Connection connect = DBConnection.getInstance();

	public DAOFactory() {
	}

	/**
	 * @return a product DAO that works with the relational database
	 */
	public I_DAO<I_Product> createProductDAO() {
		return new ProductDAO(connect);
	}

	/**
	 * @return a catalog DAO that works with the relational database
	 */
	public I_DAO<I_Catalog> createCatalogDAO() {
		return new CatalogDAO(connect);
	}

	public boolean disconnectDB() {
		return DBConnection.disconnect();
	}

}
