package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DisplayWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3269199765969312063L;
	private JButton btOK;

	public DisplayWindow(String text) {

		setTitle("Display");
		setBounds(500, 500, 450, 250);
		JPanel panTop = new JPanel();
		JPanel panBottom = new JPanel();
		panTop.setLayout(new BorderLayout());
		panBottom.setLayout(new FlowLayout());

		JTextArea jtaOutput = new JTextArea("Stock summary: \n", 10, 5);
		jtaOutput.append(text);

		jtaOutput.setLineWrap(true);
		jtaOutput.setEditable(false);
		btOK = new JButton("Close");


		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		panTop.add(jtaOutput);
		panBottom.add(btOK);

		contentPane.add(panTop, "North");
		contentPane.add(panBottom, "South");
		btOK.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
