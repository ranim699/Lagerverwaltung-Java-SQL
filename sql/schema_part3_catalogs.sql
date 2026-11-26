-- Part 3: several catalogs, each product belongs to one catalog (Oracle SQL)
-- These names match the queries in src/dao/ProductDAO.java and src/dao/CatalogDAO.java.

CREATE TABLE Catalog
(catalogId NUMBER, catalogName VARCHAR(100),
CONSTRAINT pk_Catalog PRIMARY KEY (catalogId),
CONSTRAINT un_catalogName_Catalog UNIQUE (catalogName),
CONSTRAINT nn_catalogName_Catalog CHECK (catalogName IS NOT NULL) );

CREATE TABLE Products
(productId NUMBER, productName VARCHAR(100), stockQuantity NUMBER(*,0), unitPriceExclVAT NUMBER(*,2), catalogId NUMBER,
CONSTRAINT pk_Product PRIMARY KEY (productId),
CONSTRAINT un_productName_Product UNIQUE (productName, catalogId),
CONSTRAINT nn_productName_Product CHECK (productName IS NOT NULL),
CONSTRAINT nn_stockQuantity_Product CHECK (stockQuantity IS NOT NULL),
CONSTRAINT nn_price_Product CHECK (unitPriceExclVAT IS NOT NULL),
CONSTRAINT nn_catalog_Product CHECK (catalogId IS NOT NULL),
CONSTRAINT fk_catalog_Product FOREIGN KEY (catalogId) REFERENCES Catalog(catalogId) ON DELETE CASCADE );


CREATE SEQUENCE seqCatalog;
CREATE SEQUENCE seqProducts;


CREATE OR REPLACE PROCEDURE newCatalog (p_catalogName IN Catalog.catalogName%TYPE) IS
BEGIN
	INSERT INTO Catalog (catalogId, catalogName)
	VALUES (seqCatalog.NEXTVAL, p_catalogName);
END;
/

CREATE OR REPLACE PROCEDURE newProduct (p_productName IN Products.productName%TYPE,
                                        p_quantity IN Products.stockQuantity%TYPE,
                                        p_price IN Products.unitPriceExclVAT%TYPE,
                                        p_catalogName IN Catalog.catalogName%TYPE) IS

	v_catalogId Catalog.catalogId%TYPE;
BEGIN
	SELECT catalogId INTO v_catalogId
	FROM Catalog
	WHERE catalogName = p_catalogName;

	INSERT INTO Products (productId, productName, stockQuantity, unitPriceExclVAT, catalogId)
	VALUES (seqProducts.NEXTVAL, p_productName, p_quantity, p_price, v_catalogId);
END;
/
SHOW ERRORS
