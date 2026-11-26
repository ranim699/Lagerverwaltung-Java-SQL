package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.ProductController;

public class NewProductWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7003676771126229256L;
	private JTextField txtPriceExclVAT;
	private JTextField txtName;
	private JTextField txtQty;
	private JButton btConfirm;

	public NewProductWindow() {

		setTitle("Create product");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labName = new JLabel("Product name");
		JLabel labPriceExclVAT = new JLabel("Price excl. VAT");
		JLabel labQty = new JLabel("Quantity in stock");
		contentPane.add(labName);
		txtName = new JTextField(15);
		contentPane.add(txtName);
		contentPane.add(labPriceExclVAT);
		txtPriceExclVAT = new JTextField(15);
		contentPane.add(txtPriceExclVAT);
		contentPane.add(labQty);
		txtQty = new JTextField(15);
		contentPane.add(txtQty);

		btConfirm = new JButton("Confirm");
		contentPane.add(btConfirm);

		btConfirm.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
		if (e.getSource() == this.btConfirm) {
			String productName = txtName.getText();
			String productPrice = txtPriceExclVAT.getText();
			String productQty = txtQty.getText();

			if (ProductController.addProduct(productName, productPrice, productQty, this)) {
				System.out.println("New product added");
			} else {
				System.out.println("Product not added");
			}
		}
	}

}
