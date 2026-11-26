package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import domain.Catalog;
import domain.I_Catalog;
import domain.I_Product;
import domain.Product;

import org.junit.Before;
import org.junit.Test;


public class CatalogTest {

	I_Catalog cat;

	@Before
	public void setUp() {
		cat = new Catalog("test");
		// If your Catalog is a singleton, change the line above and empty the catalog with clear():
		// cat.clear();
	}

	@Test
	public void testCatalogConstructor() {
		assertNotNull("create catalog", cat);
	}

	@Test
	public void testAddProductIProduct_null() {
		I_Product p1 = null;
		assertFalse("add null product", cat.addProduct(p1));
	}

	@Test
	public void testAddProductIProduct_oneProduct() {
		I_Product p1 = createProduct("Mars", 10, 1);
		assertTrue("add one product", cat.addProduct(p1));
	}

	@Test
	public void testAddProductIProduct_twoProducts() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Treets", 10, 1);
		assertTrue("add two products", cat.addProduct(p2));
	}

	@Test
	public void testAddProductIProduct_sameProductTwiceConsecutive() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Treets", 10, 1);
		cat.addProduct(p2);
		assertFalse("add the same product twice in a row", cat.addProduct(p2));
	}

	@Test
	public void testAddProductIProduct_sameProductTwiceNonConsecutive() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Treets", 10, 1);
		cat.addProduct(p2);
		assertFalse("add the same product twice, not in a row", cat.addProduct(p1));
	}

	@Test
	public void testAddProductIProduct_sameNameTwiceConsecutive() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Treets", 10, 1);
		cat.addProduct(p2);
		I_Product p3 = createProduct("Treets", 15, 2);
		assertFalse("add two products with the same name in a row", cat.addProduct(p3));
	}

	@Test
	public void testAddProductIProduct_sameNameTwiceNonConsecutive() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Treets", 10, 1);
		cat.addProduct(p2);
		I_Product p3 = createProduct("Mars", 15, 2);
		assertFalse("add two products with the same name, not in a row", cat.addProduct(p3));
	}

	@Test
	public void testAddProductIProduct_sameNameLeadingSpaces() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct(" Mars", 15, 2);
		assertFalse("add two products with the same name, one with leading spaces", cat.addProduct(p2));
	}

	@Test
	public void testAddProductIProduct_sameNameLeadingTabs() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("	Mars", 15, 2);
		assertFalse("add two products with the same name, one with leading tabs", cat.addProduct(p2));
	}

	@Test
	public void testAddProductIProduct_sameNameTrailingSpaces() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Mars ", 15, 2);
		assertFalse("add two products with the same name, one with trailing spaces", cat.addProduct(p2));
	}

	@Test
	public void testAddProductIProduct_sameNameTrailingTabs() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Mars	", 15, 2);
		assertFalse("add two products with the same name, one with trailing tabs", cat.addProduct(p2));
	}

	@Test
	public void testAddProductIProduct_negativeStock() {
		I_Product p1 = createProduct("Raider", 10, -1);
		assertFalse("add product with negative stock", cat.addProduct(p1));
	}

	@Test
	public void testAddProductIProduct_zeroStock() {
		I_Product p1 = createProduct("Snickers", 1, 0);
		assertTrue("add product with zero stock", cat.addProduct(p1));
	}

	@Test
	public void testAddProductIProduct_zeroPrice() {
		I_Product p1 = createProduct("Lion", 0, 3);
		assertFalse("add product with zero price", cat.addProduct(p1));
	}

	@Test
	public void testAddProductIProduct_negativePrice() {
		I_Product p1 = createProduct("Bounty", -5, 4);
		assertFalse("add product with negative price", cat.addProduct(p1));
	}


	@Test
	public void testAddProductStringDoubleInt_oneProduct() {
		assertTrue("add one product", cat.addProduct("Mars", 10, 1));
	}

	@Test
	public void testAddProductStringDoubleInt_twoProducts() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		assertTrue("add two products", cat.addProduct("Treets", 10, 1));
	}

	@Test
	public void testAddProductStringDoubleInt_sameNameTwiceConsecutive() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Treets", 10, 1);
		cat.addProduct(p2);
		assertFalse("add the same product twice in a row", cat.addProduct("Treets", 10, 1));
	}

	@Test
	public void testAddProductStringDoubleInt_sameNameTwiceNonConsecutive() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		I_Product p2 = createProduct("Treets", 10, 1);
		cat.addProduct(p2);
		assertFalse("add the same product twice, not in a row", cat.addProduct("Mars", 10, 1));
	}

	@Test
	public void testAddProductStringDoubleInt_sameNameLeadingSpaces() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		assertFalse("add the same product twice, one with leading spaces", cat.addProduct(" Mars", 10, 1));
	}

	@Test
	public void testAddProductStringDoubleInt_sameNameLeadingTabs() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		assertFalse("add the same product twice, one with leading tabs", cat.addProduct("	Mars", 10, 1));
	}

	@Test
	public void testAddProductStringDoubleInt_sameNameTrailingSpaces() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		assertFalse("add the same product twice, one with trailing spaces", cat.addProduct("Mars ", 10, 1));
	}

	@Test
	public void testAddProductStringDoubleInt_sameNameTrailingTabs() {
		I_Product p1 = createProduct("Mars", 10, 1);
		cat.addProduct(p1);
		assertFalse("add the same product twice, one with trailing tabs", cat.addProduct("Mars	", 10, 1));
	}

	@Test
	public void testAddProductStringDoubleInt_negativeStock() {
		assertFalse("add product with negative stock", cat.addProduct("Raider", 10, -1));
	}

	@Test
	public void testAddProductStringDoubleInt_zeroStock() {
		assertTrue("add product with zero stock", cat.addProduct("Snickers", 1, 0));
	}

	@Test
	public void testAddProductStringDoubleInt_zeroPrice() {
		assertFalse("add product with zero price", cat.addProduct("Lion", 0, 3));
	}

	@Test
	public void testAddProductStringDoubleInt_negativePrice() {
		assertFalse("add product with negative price", cat.addProduct("Bounty", -5, 4));
	}

	@Test
	public void testAddProducts_null() {
		List<I_Product> list = null;
		assertEquals("add null list", 0, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_empty() {
		List<I_Product> list = new ArrayList<I_Product>();
		assertEquals("add empty list", 0, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_noDuplicatesEmptyCatalog() {
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		list.add(p1);
		list.add(p2);
		assertEquals("add list with two products to an empty catalog", 2, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_noDuplicatesFilledCatalog() {
		I_Product p0 = createProduct("Twix", 10, 6);
		cat.addProduct(p0);
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		list.add(p1);
		list.add(p2);
		assertEquals("add list with two products to a filled catalog", 2, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_oneDuplicate() {
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		cat.addProduct(p1);
		cat.addProduct(p2);
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p3 = createProduct("Mars", 15, 2);
		I_Product p4 = createProduct("Twix", 10, 6);
		I_Product p5 = createProduct("M&M's", 8, 1);
		I_Product p6 = createProduct("Bounty", 4, 2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p2);
		list.add(p4);
		assertEquals("add list where only one product is already in the catalog", 3, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_someDuplicates() {
		I_Product p1 = createProduct("Twix", 10, 4);
		I_Product p2 = createProduct("Bounty", 11, 2);
		cat.addProduct(p1);
		cat.addProduct(p2);
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p3 = createProduct("Mars", 15, 2);
		I_Product p4 = createProduct("Twix", 10, 6);
		I_Product p5 = createProduct("M&M's", 8, 1);
		I_Product p6 = createProduct("Bounty", 4, 2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p2);
		list.add(p4);
		assertEquals("add list where several products are already in the catalog", 2, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_onlyDuplicates() {
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		cat.addProduct(p1);
		cat.addProduct(p2);
		List<I_Product> list = new ArrayList<I_Product>();
		list.add(p1);
		list.add(p2);
		assertEquals("add list where all products are already in the catalog", 0, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_duplicateNames() {
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		cat.addProduct(p1);
		cat.addProduct(p2);
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p3 = createProduct("Mars", 15, 2);
		I_Product p4 = createProduct("Treets", 10, 6);
		list.add(p3);
		list.add(p4);
		assertEquals("add list where all names are already in the catalog", 0, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_duplicateNameLeadingSpaces() {
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		cat.addProduct(p1);
		cat.addProduct(p2);
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p3 = createProduct(" Mars", 15, 2);
		I_Product p4 = createProduct("Twix", 10, 6);
		I_Product p5 = createProduct("M&M's", 8, 1);
		I_Product p6 = createProduct("Bounty", 4, 2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p2);
		list.add(p4);
		assertEquals("add list with a product name with leading spaces", 3, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_duplicateNameLeadingTabs() {
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		cat.addProduct(p1);
		cat.addProduct(p2);
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p3 = createProduct("	Mars", 15, 2);
		I_Product p4 = createProduct("Twix", 10, 6);
		I_Product p5 = createProduct("M&M's", 8, 1);
		I_Product p6 = createProduct("Bounty", 4, 2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p2);
		list.add(p4);
		assertEquals("add list with a product name with leading tabs", 3, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_duplicateNameTrailingSpaces() {
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		cat.addProduct(p1);
		cat.addProduct(p2);
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p3 = createProduct("Mars ", 15, 2);
		I_Product p4 = createProduct("Twix", 10, 6);
		I_Product p5 = createProduct("M&M's", 8, 1);
		I_Product p6 = createProduct("Bounty", 4, 2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p2);
		list.add(p4);
		assertEquals("add list with a product name with trailing spaces", 3, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_duplicateNameTrailingTabs() {
		I_Product p1 = createProduct("Mars", 10, 4);
		I_Product p2 = createProduct("Treets", 11, 2);
		cat.addProduct(p1);
		cat.addProduct(p2);
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p3 = createProduct("Mars ", 15, 2);
		I_Product p4 = createProduct("Twix", 10, 6);
		I_Product p5 = createProduct("M&M's", 8, 1);
		I_Product p6 = createProduct("Bounty", 4, 2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p2);
		list.add(p4);
		assertEquals("add list with a product name with trailing tabs", 3, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_negativeStock() {
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p1 = createProduct("Mars", 15, 2);
		I_Product p2 = createProduct("Kit Kat", 8, -3);
		I_Product p3 = createProduct("Lion", 4, 6);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		assertEquals("add list with a product with negative stock", 2, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_zeroStock() {
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p1 = createProduct("Mars", 15, 2);
		I_Product p2 = createProduct("Snickers", 1, 0);
		I_Product p3 = createProduct("Lion", 4, 6);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		assertEquals("add list with a product with zero stock", 3, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_zeroPrice() {
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p1 = createProduct("Nuts", 0, 1);
		list.add(p1);
		assertEquals("add list with a product with zero price", 0, cat.addProducts(list));
	}

	@Test
	public void testAddProducts_negativePrice() {
		List<I_Product> list = new ArrayList<I_Product>();
		I_Product p1 = createProduct("Topset", -8, 3);
		I_Product p2 = createProduct("Nuts", 4, 6);
		list.add(p1);
		list.add(p2);
		assertEquals("add list with a product with negative price", 1, cat.addProducts(list));
	}

	@Test
	public void testRemoveProduct_exists() {
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		cat.addProduct("Raider", 12, 2);
		assertTrue("remove existing product", cat.removeProduct("Mars"));
	}

	@Test
	public void testRemoveProduct_doesNotExist() {
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		cat.addProduct("Raider", 12, 2);
		assertFalse("remove a product that does not exist", cat.removeProduct("Lion"));
	}

	@Test
	public void testRemoveProduct_null() {
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		cat.addProduct("Raider", 12, 2);
		assertFalse("remove with a null name", cat.removeProduct(null));
	}

	@Test
	public void testBuyProduct_doesNotExist() {
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		cat.addProduct("Raider", 12, 2);
		assertFalse("buy a product that does not exist", cat.buyStock("Nuts", 3));
	}

	@Test
	public void testBuyProduct_exists() {
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		cat.addProduct("Raider", 12, 2);
		assertTrue("buy a product that exists", cat.buyStock("Raider", 3));
	}

	@Test
	public void testBuyProduct_negativeQuantity() {
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		cat.addProduct("Raider", 12, 2);
		assertFalse("buy a negative quantity", cat.buyStock("Mars", -4));
	}

	@Test
	public void testBuyProduct_zeroQuantity() {
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		cat.addProduct("Raider", 12, 2);
		assertFalse("buy a zero quantity", cat.buyStock("Treets", 0));
	}

	@Test
	public void testSellProduct_doesNotExist() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 0);
		cat.addProduct("Raider", 12, 3);
		assertFalse("sell a product that does not exist", cat.sellStock("Nuts", 3));
	}

	@Test
	public void testSellProduct_exists() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 0);
		cat.addProduct("Raider", 12, 3);
		assertTrue("sell a product that exists", cat.sellStock("Raider", 1));
	}

	@Test
	public void testSellProduct_negativeQuantity() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 0);
		cat.addProduct("Raider", 12, 3);
		assertFalse("sell a negative quantity", cat.sellStock("Mars", -4));
	}

	@Test
	public void testSellProduct_zeroQuantity() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 0);
		cat.addProduct("Raider", 12, 3);
		assertFalse("sell a zero quantity", cat.sellStock("Treets", 0));
	}

	@Test
	public void testSellProduct_zeroStock() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 0);
		cat.addProduct("Raider", 12, 3);
		assertFalse("sell a product without stock", cat.sellStock("Treets", 4));
	}

	@Test
	public void testSellProduct_notEnoughStock() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 0);
		cat.addProduct("Raider", 12, 3);
		assertFalse("sell a product with not enough stock", cat.sellStock("Raider", 10));
	}

	@Test
	public void testGetProductNames_empty() {
		String[] tab0 = new String[0];
		assertArrayEquals("product names of an empty catalog", tab0, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_oneProduct() {
		String[] tab = {"Mars"};
		cat.addProduct("Mars", 10, 1);
		assertArrayEquals("product names with one product", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_oneProductLeadingSpaces() {
		String[] tab = {"Mars"};
		cat.addProduct(" Mars", 10, 1);
		assertArrayEquals("product name with leading space; leading spaces must not be stored", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_oneProductTrailingSpaces() {
		String[] tab = {"Mars"};
		cat.addProduct("Mars ", 10, 1);
		assertArrayEquals("product name with trailing space; trailing spaces must not be stored", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_oneProductSpacesInMiddle() {
		String[] tab = {"Kit Kat"};
		cat.addProduct("Kit Kat", 10, 1);
		assertArrayEquals("product name with spaces in the middle", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_oneProductLeadingTabs() {
		String[] tab = {"Mars"};
		cat.addProduct("	Mars", 10, 1);
		assertArrayEquals("product name with leading tab; leading tabs must not be stored", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_oneProductTrailingTabs() {
		String[] tab = {"Mars"};
		cat.addProduct("Mars	", 10, 1);
		assertArrayEquals("product name with trailing tab; trailing tabs must not be stored", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_oneProductTabsInMiddle() {
		String[] tab = {"Kit Kat"};
		cat.addProduct("Kit	Kat", 10, 1);
		assertArrayEquals("product name with tabs in the middle; tabs in the middle must be replaced by spaces", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_twoProducts() {
		String[] tab = {"Mars", "Treets"};
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		assertArrayEquals("names of two products", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_severalProductsAddedAlphabetically() {
		String[] tab = {"Mars", "Raider", "Treets"};
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Raider", 12, 2);
		cat.addProduct("Treets", 10, 1);
		assertArrayEquals("names of several products added in alphabetical order", tab, cat.getProductNames());
	}

	@Test
	public void testGetProductNames_severalProductsAddedRandomly() {
		String[] tab = {"Bounty", "Mars", "Raider", "Treets"};
		cat.addProduct("Mars", 10, 1);
		cat.addProduct("Treets", 10, 1);
		cat.addProduct("Raider", 12, 2);
		cat.addProduct("Bounty", 12, 2);
		assertArrayEquals("names of several products added in random order (must be returned in alphabetical order)", tab, cat.getProductNames());
	}

	@Test
	public void testTotalAmountInclVAT_emptyCatalog() {
		assertEquals("amount incl. VAT of an empty catalog", 0, cat.getTotalAmountInclVAT(), 0);
	}

	@Test
	public void testTotalAmountInclVAT_noStock() {
		cat.addProduct("Nuts", 1, 0);
		assertEquals("amount incl. VAT without stock", 0, cat.getTotalAmountInclVAT(), 0);
	}

	@Test
	public void testTotalAmountInclVAT_noDecimals() {
		cat.addProduct("Mars", 100, 4);
		cat.addProduct("Raider", 20, 5);
		assertEquals("amount incl. VAT without decimals", 600, cat.getTotalAmountInclVAT(), 0);
	}

	@Test
	public void testTotalAmountInclVAT_oneDecimalNoRounding() {
		cat.addProduct("Mars", 10, 6);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 12);
		assertEquals("amount incl. VAT with 1 decimal", 134.4, cat.getTotalAmountInclVAT(), 0);
	}

	@Test
	public void testTotalAmountInclVAT_twoDecimalsNoRounding() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.addProduct("Twix", 12.6, 1);
		assertEquals("amount incl. VAT with 2 decimals", 135.12, cat.getTotalAmountInclVAT(), 0);
	}

	@Test
	public void testTotalAmountInclVAT_threeDecimalsRoundDown() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.addProduct("Twix", 12.66, 1);
		assertEquals("amount incl. VAT; 135.192 must be rounded to 135.19", 135.19, cat.getTotalAmountInclVAT(), 0);
	}

	@Test
	public void testTotalAmountInclVAT_threeDecimalsRoundUp() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.addProduct("Twix", 12.69, 1);
		assertEquals("amount incl. VAT; 135.228 must be rounded to 135.23", 135.23, cat.getTotalAmountInclVAT(), 0);
	}

	@Test
	public void testTotalAmountInclVAT_roundTotalNotUnitPrices() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.addProduct("Twix", 12.67, 1);
		cat.addProduct("Nuts", 12.67, 1);
		assertEquals("the total amount incl. VAT must be rounded, not the prices of the single products", 150.41, cat.getTotalAmountInclVAT(), 0);
	}

	@Test
	public void testToString_emptyCatalog() {
		String expected = "\n" +
						  "Total stock value incl. VAT: 0,00 €";
		assertEquals("toString of an empty catalog", expected, cat.toString());
	}

	@Test
	public void testToString_withProducts_totalNoDecimals() {
		String expected = "Mars - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 5" + "\n" +
						  "Treets - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 4" + "\n" +
						  "Raider - price excl. VAT: 1,00 € - price incl. VAT: 1,20 € - quantity in stock: 10" + "\n" +
						  "\n" +
						  "Total stock value incl. VAT: 120,00 €";
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		assertEquals("toString catalog without decimals", expected, cat.toString());
	}

	@Test
	public void testToString_withProducts_spacesInNames() {
		String expected = "Mars - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 5" + "\n" +
				  "Treets - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 4" + "\n" +
				  "Kit Kat - price excl. VAT: 1,00 € - price incl. VAT: 1,20 € - quantity in stock: 10" + "\n" +
				  "\n" +
				  "Total stock value incl. VAT: 120,00 €";
		cat.addProduct("Mars ", 10, 5);
		cat.addProduct(" Treets", 10, 4);
		cat.addProduct("Kit Kat", 1, 10);
		assertEquals("toString with spaces in product names", expected, cat.toString());
	}

	@Test
	public void testToString_withProducts_tabsInNames() {
		String expected = "Mars - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 5" + "\n" +
				  "Treets - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 4" + "\n" +
				  "Kit Kat - price excl. VAT: 1,00 € - price incl. VAT: 1,20 € - quantity in stock: 10" + "\n" +
				  "\n" +
				  "Total stock value incl. VAT: 120,00 €";
		cat.addProduct("Mars	", 10, 5);
		cat.addProduct("	Treets", 10, 4);
		cat.addProduct("Kit	Kat", 1, 10);
		assertEquals("toString with tabs in product names", expected, cat.toString());
	}

	@Test
	public void testToString_withProducts_totalOneDecimal() {
		String expected = "Mars - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 5" + "\n" +
						  "Treets - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 4" + "\n" +
						  "Raider - price excl. VAT: 1,00 € - price incl. VAT: 1,20 € - quantity in stock: 10" + "\n" +
						  "Twix - price excl. VAT: 10,45 € - price incl. VAT: 12,54 € - quantity in stock: 5" + "\n" +
						  "\n" +
						  "Total stock value incl. VAT: 182,70 €";
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.addProduct("Twix", 10.45, 5);
		assertEquals("toString catalog with a total with one decimal", expected, cat.toString());
	}

	@Test
	public void testToString_withProducts_totalTwoDecimals() {
		String expected = "Mars - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 5" + "\n" +
						  "Treets - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 4" + "\n" +
						  "Raider - price excl. VAT: 1,00 € - price incl. VAT: 1,20 € - quantity in stock: 10" + "\n" +
						  "Twix - price excl. VAT: 10,40 € - price incl. VAT: 12,48 € - quantity in stock: 1" + "\n" +
						  "\n" +
						  "Total stock value incl. VAT: 132,48 €";
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.addProduct("Twix", 10.4, 1);
		assertEquals("toString catalog with a total with two decimals", expected, cat.toString());
	}

	@Test
	public void testToString_withProducts_totalThreeDecimalsRoundDown() {
		String expected = "Mars - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 5" + "\n" +
						  "Treets - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 4" + "\n" +
						  "Raider - price excl. VAT: 1,00 € - price incl. VAT: 1,20 € - quantity in stock: 10" + "\n" +
						  "Twix - price excl. VAT: 10,47 € - price incl. VAT: 12,56 € - quantity in stock: 1" + "\n" +
						  "\n" +
						  "Total stock value incl. VAT: 132,56 €";
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.addProduct("Twix", 10.47, 1);
		assertEquals("unit prices incl. VAT are shown with two decimals, but the total is calculated with the unrounded unit prices", expected, cat.toString());
	}

	@Test
	public void testToString_withProducts_totalThreeDecimalsRoundUp() {
		String expected = "Mars - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 5" + "\n" +
						  "Treets - price excl. VAT: 10,00 € - price incl. VAT: 12,00 € - quantity in stock: 4" + "\n" +
						  "Raider - price excl. VAT: 1,00 € - price incl. VAT: 1,20 € - quantity in stock: 10" + "\n" +
						  "Twix - price excl. VAT: 10,47 € - price incl. VAT: 12,56 € - quantity in stock: 2" + "\n" +
						  "\n" +
						  "Total stock value incl. VAT: 145,13 €";
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.addProduct("Twix", 10.47, 2);
		assertEquals("unit prices incl. VAT are shown with two decimals, but the total is calculated with the unrounded unit prices", expected, cat.toString());
	}

	@Test
	public void testClear() {
		cat.addProduct("Mars", 10, 5);
		cat.addProduct("Treets", 10, 4);
		cat.addProduct("Raider", 1, 10);
		cat.clear();
		assertTrue("the catalog has been emptied", cat.getProductNames().length == 0);
	}

	private I_Product createProduct(String name, double priceExclVAT, int quantity) {
		try {
			return new Product(name, priceExclVAT, quantity, cat);
		}
		catch (Exception e) { return null; }
	}

}
