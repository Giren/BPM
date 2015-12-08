package com.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthGui extends JFrame {
	
	private static boolean auth;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthGui frame = new AuthGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AuthGui() {
		setAuth(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGenehmigen = new JButton("genehmigen");
		btnGenehmigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				auth = true;
				close();
			}
		});
		btnGenehmigen.setBounds(63, 71, 117, 29);
		contentPane.add(btnGenehmigen);
		
		JButton btnAblehnen = new JButton("ablehnen");
		btnAblehnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auth = false;
				close();
			}
		});
		btnAblehnen.setBounds(245, 71, 117, 29);
		contentPane.add(btnAblehnen);
		
		JLabel lblGenehmigungErforderlich = new JLabel("Genehmigung erforderlich");
		lblGenehmigungErforderlich.setBounds(130, 18, 164, 29);
		contentPane.add(lblGenehmigungErforderlich);
	}

	public void close(){
		this.setVisible(false);
		this.dispose();
	}
	
	public static boolean isAuth() {
		return auth;
	}

	public static void setAuth(boolean auth) {
		AuthGui.auth = auth;
	}
}
