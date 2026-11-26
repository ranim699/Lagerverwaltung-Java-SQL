package dao;

import java.util.List;

public interface I_DAO<T> {

	/**
	 * Saves the object obj in the database
	 * @param obj
	 * @return TRUE if and only if the object was created
	 */
	public abstract boolean create(T obj);

	/**
	 * Updates the data of the object obj in the database
	 * @param obj
	 * @return TRUE if and only if the object was updated
	 */
	public abstract boolean update(T obj) throws StockQuantityException;

	/**
	 * Deletes the object obj from the database
	 * @param obj
	 * @return TRUE if and only if the object was deleted
	 */
	public abstract boolean delete(T obj);

	/**
	 * @param objName
	 * @return a list of all T elements stored in the database
	 */
	public abstract List<T> findAll(String objName);

	/**
	 * @param column, value
	 * @return a T object stored in the database
	 */
	public abstract T findByAttribute(String column, Object value);

	/**
	 * @param name
	 * @return the number of products in a catalog
	 */
	public abstract int countRows(String name);

}
