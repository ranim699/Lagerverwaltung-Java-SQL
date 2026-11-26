package dao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import domain.Catalog;
import domain.I_Catalog;
import domain.I_Product;
import domain.Product;

public class ProductDAO_XML {
	private String uri = "Products.xml";
	private Document doc;

	public ProductDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("error building the JDOM tree");
		}
	}

	public boolean create(I_Product p) {
		try {
			Element root = doc.getRootElement();
			Element prod = new Element("product");
			prod.setAttribute("name", p.getName());
			Element price = new Element("priceExclVAT");
			prod.addContent(price.setText(String.valueOf(p.getUnitPriceExclVAT())));
			Element qty = new Element("quantity");
			prod.addContent(qty.setText(String.valueOf(p.getQuantity())));
			Element catalog = new Element("catalog");
			prod.addContent(catalog.setText(p.getCatalog().getCatalogName()));
			root.addContent(prod);
			return save();
		} catch (Exception e) {
			System.out.println("error creating product");
			return false;
		}
	}

	public boolean update(I_Product p) {
		try {
			Element prod = findProductElement(p.getName(), p.getCatalog().getCatalogName());
			if (prod != null) {
				prod.getChild("quantity").setText(String.valueOf(p.getQuantity()));
				return save();
			}
			return false;
		} catch (Exception e) {
			System.out.println("error updating product");
			return false;
		}
	}

	public boolean delete(I_Product p) {
		try {
			Element root = doc.getRootElement();
			Element prod = findProductElement(p.getName(), p.getCatalog().getCatalogName());
			if (prod != null) {
				root.removeContent(prod);
				return save();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("error deleting product");
			return false;
		}
	}

	public I_Product read(String name, String catalog) {
		Element e = findProductElement(name, catalog);
		if (e != null) {
			String productName = e.getAttributeValue("name");
			double price = Double.parseDouble(e.getChildText("priceExclVAT"));
			int productQty = Integer.parseInt(e.getChildText("quantity"));
			I_Catalog cat = new Catalog(e.getChildText("catalog"));
			return new Product(productName, price, productQty, cat);
		} else {
			return null;
		}

	}

	public List<I_Product> readAll(String catalogName) {

		List<I_Product> l = new ArrayList<I_Product>();
		I_Catalog catalog = new Catalog(catalogName);
		try {
			Element root = doc.getRootElement();
			List<Element> productElements = root.getChildren("product");

			for (Element prod : productElements) {
				if (prod.getChild("catalog").getText().equals(catalogName)) {
					String productName = prod.getAttributeValue("name");
					Double price = Double.parseDouble(prod.getChild("priceExclVAT").getText());
					int qty = Integer.parseInt(prod.getChild("quantity").getText());
					l.add(new Product(productName, price, qty, catalog));
				}
			}
		} catch (Exception e) {
			System.out.println("error reading all products");
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

	private Element findProductElement(String name, String catalog) {
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> productElements = root.getChildren("product");
		int i = 0;

		while (i < productElements.size() && !productElements.get(i).getAttributeValue("name").equals(name) &&
				productElements.get(i).getChild("catalog").getText().equals(catalog))
			i++;
		if (i < productElements.size())
			return productElements.get(i);
		else
			return null;
	}

	public int countRows(String name) {
		Element root = doc.getRootElement();
		return root.getChildren().size();
	}
}
