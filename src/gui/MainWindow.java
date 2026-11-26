package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.MainController;
import controller.StockController;

public class MainWindow extends JFrame implements ActionListener,
	WindowListener {

	private static final long serialVersionUID = -1731314792688972956L;
	private JButton btShow;
	private JButton btNewProduct;
	private JButton btDeleteProduct;
	private JButton btPurchase;
	private JButton btSale;
	private JButton btQuit;

	public MainWindow() {

		setTitle("Products");
		setBounds(500, 500, 320, 250);

		JPanel panDisplay = new JPanel();
		JPanel panNewDeleteProduct = new JPanel();
		JPanel panPurchaseSale = new JPanel();
		JPanel panQuit = new JPanel();

		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btShow = new JButton("Stock quantities");
		btNewProduct = new JButton("New product");
		btDeleteProduct = new JButton("Delete product");
		btPurchase = new JButton("Purchase products");
		btSale = new JButton("Sell products");
		btQuit = new JButton("Quit");
		panDisplay.add(btShow);
		panNewDeleteProduct.add(btNewProduct);
		panNewDeleteProduct.add(btDeleteProduct);
		panPurchaseSale.add(btPurchase);
		panPurchaseSale.add(btSale);
		panQuit.add(btQuit);

		contentPane.add(panDisplay);
		contentPane.add(panNewDeleteProduct);
		contentPane.add(panPurchaseSale);
		contentPane.add(panQuit);

		btShow.addActionListener(this);
		btNewProduct.addActionListener(this);
		btDeleteProduct.addActionListener(this);
		btPurchase.addActionListener(this);
		btSale.addActionListener(this);
		btQuit.addActionListener(this);

		addWindowListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btPurchase) {
			new PurchaseWindow(MainController.getProductNames());
		}
		if (e.getSource() == btSale)
			new SaleWindow(MainController.getProductNames());
		if (e.getSource() == btShow)
			new DisplayWindow(StockController.showStock());
		if (e.getSource() == btNewProduct)
			new NewProductWindow();
		if (e.getSource() == btDeleteProduct)
			new DeleteProductWindow(MainController.getProductNames());
		if (e.getSource() == btQuit) {
			new HomeWindow();
			this.dispose();
		}
	}

	public void windowClosing(WindowEvent arg0) {
		new HomeWindow();
		this.dispose();
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
