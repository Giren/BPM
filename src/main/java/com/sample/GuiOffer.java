package com.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiOffer extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GuiOffer(String offer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane offerTextPane = new JTextPane();
		offerTextPane.setEditable(false);
		offerTextPane.setBounds(6, 6, 238, 306);
		offerTextPane.setText(offer);
		contentPane.add(offerTextPane);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnOk.setBounds(67, 324, 117, 29);
		contentPane.add(btnOk);
	}
	
	public void close(){
		this.setVisible(false);
		this.dispose();
//		System.exit(0);
	}

}
