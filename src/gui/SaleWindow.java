package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.PurchaseSaleController;

public class SaleWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6509810633838914324L;
	private JButton btSale;
	private JTextField txtQuantity;
	private JComboBox<String> combo;

	public SaleWindow(String[] strings) {
		setTitle("Sale");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSale = new JButton("Sell");
		txtQuantity = new JTextField(5);
		txtQuantity.setText("0");

		combo = new JComboBox<String>();
		for (int i = 0; i < strings.length; i++) {
			combo.addItem(strings[i]);
		}

		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Product"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantity sold"));
		contentPane.add(txtQuantity);
		contentPane.add(btSale);

		btSale.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btSale) {
			PurchaseSaleController.sellProduct(this, (String) combo.getSelectedItem(), txtQuantity.getText());
		}
	}

}
