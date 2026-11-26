package dao;

import domain.I_Catalog;
import domain.I_Product;

public abstract class AbstractDAOFactory implements I_DAOFactory {

	public static AbstractDAOFactory instance = null;

	protected AbstractDAOFactory() {}

	public synchronized static AbstractDAOFactory getInstance(String technology) {
		if (instance == null) {
			if (technology == "XML") {
				instance = new DAOFactory_XML();
			} else {
				instance = new DAOFactory();
			}

		}
		return instance;
	}

	@Override
	public abstract I_DAO<I_Product> createProductDAO();

	@Override
	public abstract I_DAO<I_Catalog> createCatalogDAO();

	@Override
	public boolean disconnectDB() {
		return false;
	}

}
