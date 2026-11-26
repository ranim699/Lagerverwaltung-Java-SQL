-- Part 2: one product table (Oracle SQL)

CREATE TABLE Products
(productId NUMBER, productName VARCHAR(100), stockQuantity NUMBER(*,0), unitPriceExclVAT NUMBER(*,2),
CONSTRAINT pk_Product PRIMARY KEY (productId),
CONSTRAINT un_productName_Product UNIQUE (productName),
CONSTRAINT nn_productName_Product CHECK (productName IS NOT NULL),
CONSTRAINT nn_stockQuantity_Product CHECK (stockQuantity IS NOT NULL),
CONSTRAINT nn_price_Product CHECK (unitPriceExclVAT IS NOT NULL) );


CREATE SEQUENCE seqProducts;

CREATE OR REPLACE PROCEDURE newProduct (p_productName IN Products.productName%TYPE,
                                        p_quantity IN Products.stockQuantity%TYPE,
                                        p_price IN Products.unitPriceExclVAT%TYPE) IS
BEGIN
	INSERT INTO Products (productId, productName, stockQuantity, unitPriceExclVAT)
	VALUES (seqProducts.NEXTVAL, p_productName, p_quantity, p_price);
END;
/
