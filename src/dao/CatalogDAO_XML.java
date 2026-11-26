package dao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import domain.Catalog;
import domain.I_Catalog;
import domain.I_Product;

public class CatalogDAO_XML implements I_DAO<I_Catalog> {
	private String uri = "Catalogs.xml";
	private Document doc;
	private I_DAO<I_Product> productDAO;

	public CatalogDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
			productDAO = new ProductDAO_XMLAdapter();
		} catch (Exception e) {
			System.out.println("error building the JDOM tree");
		}
	}

	@Override
	public boolean create(I_Catalog c) {
		try {
			Element root = doc.getRootElement();
			Element cat = new Element("catalog");
			cat.setAttribute("name", c.getCatalogName());
			root.addContent(cat);
			return save();
		} catch (Exception e) {
			System.out.println("error creating catalog");
			return false;
		}
	}

	@Override
	public boolean update(I_Catalog c) {
		return false;
	}

	@Override
	public boolean delete(I_Catalog c) {
		try {
			Element root = doc.getRootElement();
			Element cat = findCatalogElement(c.getCatalogName());
			if (cat != null) {
				root.removeContent(cat);

				List<I_Product> products = productDAO.findAll(c.getCatalogName());
				for (I_Product product : products) {
					productDAO.delete(product);
				}
				return save();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("error deleting catalog");
			return false;
		}
	}

	@Override
	public List<I_Catalog> findAll(String objName) {

		List<I_Catalog> l = new ArrayList<I_Catalog>();
		try {
			Element root = doc.getRootElement();
			List<Element> catalogElements = root.getChildren("catalog");

			for (Element element : catalogElements) {
				String catalogName = element.getAttributeValue("name");
				l.add(new Catalog(catalogName));
			}
		} catch (Exception e) {
			System.out.println("error reading all catalogs");
		}
		return l;
	}

	private boolean save() {
		System.out.println("Saving");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("error saving the XML file");
			return false;
		}
	}

	private Element findCatalogElement(String name) {
		Element root = doc.getRootElement();
		List<Element> catalogElements = root.getChildren("catalog");
		int i = 0;
		while (i < catalogElements.size() && !catalogElements.get(i).getAttributeValue("name").equals(name))
			i++;
		if (i < catalogElements.size())
			return catalogElements.get(i);
		else
			return null;
	}

	@Override
	public I_Catalog findByAttribute(String column, Object value) {
		Element root = doc.getRootElement();
		List<Element> catalogElements = root.getChildren("catalog");
		int i = 0;
		while (i < catalogElements.size() && !catalogElements.get(i).getAttributeValue(column).equals(value))
			i++;
		if (i < catalogElements.size())
			return new Catalog((String) value);
		else
			return null;
	}

	@Override
	public int countRows(String catalogName) {
		List<I_Product> products = productDAO.findAll(catalogName);
		return products.size();
	}

}
