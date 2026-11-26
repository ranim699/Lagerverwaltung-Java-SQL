package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.ProductController;

public class DeleteProductWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3712866553831523174L;
	private JButton btDelete;
	private JComboBox<String> combo;

	public DeleteProductWindow(String products[]) {

		setTitle("Delete product");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btDelete = new JButton("Delete");

		combo = new JComboBox<String>(products);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Product"));
		contentPane.add(combo);
		contentPane.add(btDelete);

		btDelete.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
		if (ProductController.deleteProduct((String) combo.getSelectedItem())) {
			JOptionPane.showMessageDialog(this, "The product " + combo.getSelectedItem() + " has been deleted.", "Delete product", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Product deleted");
		} else {
			JOptionPane.showMessageDialog(this, "Error while deleting the product", "Delete product", JOptionPane.WARNING_MESSAGE);
			System.out.println("Product not deleted");
		}
	}

}
