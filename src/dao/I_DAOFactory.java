package dao;

import domain.I_Catalog;
import domain.I_Product;

public interface I_DAOFactory {

	public abstract I_DAO<I_Product> createProductDAO();

	public abstract I_DAO<I_Catalog> createCatalogDAO();

	public abstract boolean disconnectDB();
}
