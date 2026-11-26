package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.CatalogSetController;


public class HomeWindow extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = -5727204080733492910L;
	private JButton btAdd, btDelete, btSelect;
	private JTextField txtAdd;
	private JLabel lbCatalogCount;
	private JComboBox<String> cmbDelete, cmbSelect;
	private TextArea taCatalogDetails;

	public HomeWindow() {
		setTitle("Catalogs");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		JPanel panCatalogInfo = new JPanel();
		JPanel panCatalogCount = new JPanel();
		JPanel panCatalogDetails = new JPanel();
		JPanel panCatalogManagement = new JPanel();
		JPanel panAdd = new JPanel();
		JPanel panDelete = new JPanel();
		JPanel panSelect = new JPanel();
		panCatalogCount.setBackground(Color.white);
		panCatalogDetails.setBackground(Color.white);
		panAdd.setBackground(Color.gray);
		panDelete.setBackground(Color.lightGray);
		panSelect.setBackground(Color.gray);

		panCatalogCount.add(new JLabel("We currently have: "));
		lbCatalogCount = new JLabel();
		panCatalogCount.add(lbCatalogCount);

		taCatalogDetails = new TextArea();
		taCatalogDetails.setEditable(false);
		new JScrollPane(taCatalogDetails);
		taCatalogDetails.setPreferredSize(new Dimension(300, 100));
		panCatalogDetails.add(taCatalogDetails);

		panAdd.add(new JLabel("Add a catalog: "));
		txtAdd = new JTextField(10);
		panAdd.add(txtAdd);
		btAdd = new JButton("Add");
		panAdd.add(btAdd);

		panDelete.add(new JLabel("Delete a catalog: "));
		cmbDelete = new JComboBox<String>();
		cmbDelete.setPreferredSize(new Dimension(100, 20));
		panDelete.add(cmbDelete);
		btDelete = new JButton("Delete");
		panDelete.add(btDelete);

		panSelect.add(new JLabel("Select a catalog: "));
		cmbSelect = new JComboBox<String>();
		cmbSelect.setPreferredSize(new Dimension(100, 20));
		panSelect.add(cmbSelect);
		btSelect = new JButton("Select");
		panSelect.add(btSelect);

		panCatalogManagement.setLayout(new BorderLayout());
		panCatalogManagement.add(panAdd, "North");
		panCatalogManagement.add(panDelete);
		panCatalogManagement.add(panSelect, "South");

		panCatalogInfo.setLayout(new BorderLayout());
		panCatalogInfo.add(panCatalogCount, "North");
		panCatalogInfo.add(panCatalogDetails, "South");

		contentPane.add(panCatalogInfo, "North");
		contentPane.add(panCatalogManagement, "South");
		pack();

		btAdd.addActionListener(this);
		btDelete.addActionListener(this);
		btSelect.addActionListener(this);

		addWindowListener(this);
		refreshWindow();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAdd)
		{
			String textToAdd = txtAdd.getText();
			if (!textToAdd.equals(""))
			{
				if (CatalogSetController.getInstance().addCatalog(textToAdd)) {
					refreshWindow();
					JOptionPane.showMessageDialog(this, "Catalog added successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Could not add the catalog.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please enter a value.", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getSource() == btDelete)
		{
			String textToDelete = (String) cmbDelete.getSelectedItem();
			if (textToDelete != null) {
				if (CatalogSetController.getInstance().deleteCatalog(textToDelete)) {
					refreshWindow();
					JOptionPane.showMessageDialog(this, "Catalog deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Error while deleting.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
		if (e.getSource() == btSelect)
		{
			String selectedText = (String) cmbSelect.getSelectedItem();
			if (selectedText != null)
			{
				CatalogSetController.getInstance().openCatalogWindow(selectedText);
				this.dispose();
			}
		}
	}

	private void refreshWindow() {
		updateCatalogLists(CatalogSetController.getInstance().getCatalogNames());
		updateCatalogDetails(CatalogSetController.getInstance().getCatalogDetails());
		updateCatalogCount(CatalogSetController.getInstance().getCatalogCount());
	}

	private void updateCatalogLists(String[] catalogNames) {
		cmbDelete.removeAllItems();
		cmbSelect.removeAllItems();
		if (catalogNames != null)
			for (int i = 0; i < catalogNames.length; i++)
			{
				cmbDelete.addItem(catalogNames[i]);
				cmbSelect.addItem(catalogNames[i]);
			}
	}

	private void updateCatalogCount(int count)
	{
		lbCatalogCount.setText(count + " catalogs");
	}

	private void updateCatalogDetails(String[] catalogDetails) {
		taCatalogDetails.setText("");
		if (catalogDetails != null) {
			for (int i = 0; i < catalogDetails.length; i++)
				taCatalogDetails.append(catalogDetails[i] + "\n");
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		CatalogSetController.disconnectDB();
		System.out.println("Goodbye");
		System.exit(0);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}

	public static void main(String[] args) {
		new HomeWindow();
	}
}
