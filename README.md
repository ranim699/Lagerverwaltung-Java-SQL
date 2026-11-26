# Inventory Management (Java, Layered Architecture)

> Desktop application to manage products, stock and catalogs – stored in an Oracle database or in XML files.

## Origin

Based on a repository by [ranim699](https://github.com/ranim699).

## What it does

You create products with a name, a net price and a quantity, buy or sell units and see the total stock value including VAT (20 %). Products belong to catalogs. Data is stored either in an Oracle database or in XML files.

## Tech stack

| Technology | Used for |
|---|---|
| Java + Swing | Programming language and desktop user interface (JFrame, buttons, dialogs). |
| SQL (Oracle) | Tables, sequences and stored procedures in `sql/`. |
| JDBC | Connection between Java and the database (PreparedStatement, CallableStatement). |
| JDOM | Reading and writing the XML files `Catalogs.xml` and `Products.xml`. |
| JUnit 4 | 85 automated tests in `src/tests/CatalogTest.java`. |

## Project structure

| Path | Content |
|---|---|
| `src/domain/` | Business logic: `Product`, `Catalog` and their interfaces. |
| `src/dao/` | Data access (DAO pattern): SQL and XML implementations, abstract factory, adapter. |
| `src/controller/` | Controllers that connect the user interface and the business logic. |
| `src/gui/` | Swing windows; the entry point is `gui.HomeWindow`. |
| `src/db/` | Database connection (singleton). |
| `src/tests/` | JUnit tests for the catalog. |
| `sql/` | Database schema (DDL) and stored procedures. |

## How to run

1. Install a Java JDK (8 or newer) and open the folder as a project in Eclipse or IntelliJ.
2. Add `lib/classes111.jar` and `lib/jdom.jar` to the build path (Eclipse does this via `.classpath`).
3. Run `gui.HomeWindow`. By default the app uses the XML files, so no database is needed.
4. Optional database: run `sql/schema_part3_catalogs.sql` in your own Oracle database and enter URL, user and password in `src/db/DBConnection.java`.
5. Tests: run `src/tests/CatalogTest.java` as a JUnit 4 test.

## Key concepts

- OOP: classes, interfaces, encapsulation.
- Collections: `List`/`ArrayList`, `Iterator`, `Arrays.sort`.
- Layered architecture: GUI -> controller -> domain -> DAO -> database.
- Design patterns: DAO, Abstract Factory, Adapter, Singleton.
- SQL: primary and foreign keys, UNIQUE, CHECK, JOIN, stored procedures.

## Known issues

- Strings are compared with `==` (e.g. `Catalog.getProductByName`); in Java this should be `.equals()`.
- `ProductDAO.findByAttribute` reads the price with `getInt` instead of `getDouble`, so cents are lost.
- A column name is concatenated into an SQL string in `findByAttribute`.

## Ideas for improvements

- Fix the issues above and cover them with JUnit tests.
- Switch from Oracle to MySQL or PostgreSQL.
- Rebuild the user interface with JavaFX.
- Search and sort by price or quantity, low-stock warning, CSV export.

## My changes

- _(describe here what you built or improved yourself)_
