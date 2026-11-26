package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import domain.Catalog;
import domain.I_Catalog;

public class CatalogDAO implements I_DAO<I_Catalog> {

	private Connection connect;

	public CatalogDAO() {
		super();
		this.connect = null;
	}

	public CatalogDAO(Connection connect) {
		super();
		this.connect = connect;
	}

	@Override
	public boolean create(I_Catalog cat) {
		String catalogName = cat.getCatalogName();

		try {
			CallableStatement cst = connect.prepareCall("{call newCatalog(?)}");
			cst.setString(1, catalogName);
			cst.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(I_Catalog cat) {
		return false;
	}

	@Override
	public boolean delete(I_Catalog cat) {
		String catalogName = cat.getCatalogName();
		try {
			PreparedStatement pst = connect.prepareStatement("DELETE FROM Catalog WHERE catalogName = ?");
			pst.setString(1, catalogName);
			pst.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<I_Catalog> findAll(String objName) {
		List<I_Catalog> catalogList = new ArrayList<I_Catalog>();
		I_Catalog catalog;
		String catalogName;

		try {
			PreparedStatement pst = connect.prepareStatement("SELECT catalogName FROM Catalog");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				catalogName = rs.getString(1);
				catalog = new Catalog(catalogName);
				catalogList.add(catalog);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return catalogList;
	}

	@Override
	public I_Catalog findByAttribute(String column, Object value) {
		String catalogName;
		try {
			PreparedStatement pst = connect.prepareStatement("SELECT catalogName FROM Products WHERE " + column + " = ?");
			pst.setString(1, column);
			pst.setObject(2, value);
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
					"SELECT count(*) FROM Catalog c JOIN Products p ON c.catalogId = p.catalogId WHERE catalogName = ?");
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
