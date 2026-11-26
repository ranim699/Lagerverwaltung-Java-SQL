package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.PurchaseSaleController;

public class PurchaseWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 486686843874580464L;
	private JButton btPurchase;
	private JTextField txtQuantity;
	private JComboBox<String> combo;

	public PurchaseWindow(String[] strings) {

		setTitle("Purchase");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btPurchase = new JButton("Purchase");
		txtQuantity = new JTextField(5);
		txtQuantity.setText("0");

		combo = new JComboBox<String>();
		for (int i = 0; i < strings.length; i++) {
			combo.addItem(strings[i]);
		}

		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Product"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantity purchased"));
		contentPane.add(txtQuantity);
		contentPane.add(btPurchase);

		btPurchase.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btPurchase) {
			PurchaseSaleController.purchaseProduct(this, (String) combo.getSelectedItem(), txtQuantity.getText());
		}
	}
}
