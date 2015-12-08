package com.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class PersonGui extends JFrame {
	
	private GuiRental guiRental;
	
	private boolean isTenant;
	private Person person;
	
	private JPanel contentPane;
	private JTextField ageTextField;
	private JTextField lizenzTextField;
	private JRadioButton claimRadioButton;
	private JRadioButton safetyTrainingRadioButton;
	private JRadioButton newCustomerRadioButton;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PersonGui frame = new PersonGui();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public PersonGui(GuiRental guiRental) {
		isTenant = false;
		this.guiRental = guiRental;
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnOk.setBounds(327, 243, 117, 29);
		contentPane.add(btnOk);
		
		JLabel lblAge = new JLabel("Alter");
		lblAge.setBounds(6, 6, 61, 16);
		contentPane.add(lblAge);
		
		ageTextField = new JTextField();
		ageTextField.setBounds(141, 0, 134, 28);
		contentPane.add(ageTextField);
		ageTextField.setColumns(10);
		
		JLabel lblSicherheitstraining = new JLabel("Sicherheitstraining");
		lblSicherheitstraining.setBounds(6, 93, 126, 16);
		contentPane.add(lblSicherheitstraining);
		
		JLabel lblJahreDes = new JLabel("F\u00FChrerscheinbesitz");
		lblJahreDes.setBounds(6, 46, 126, 16);
		contentPane.add(lblJahreDes);
		
		lizenzTextField = new JTextField();
		lizenzTextField.setBounds(141, 40, 134, 28);
		contentPane.add(lizenzTextField);
		lizenzTextField.setColumns(10);
		
		JLabel lblNeukunde = new JLabel("Neukunde");
		lblNeukunde.setBounds(6, 138, 126, 16);
		contentPane.add(lblNeukunde);
		
		newCustomerRadioButton = new JRadioButton("New radio button");
		newCustomerRadioButton.setBounds(192, 134, 28, 23);
		contentPane.add(newCustomerRadioButton);
		
		JLabel lblAnspruch = new JLabel("Anspruch");
		lblAnspruch.setBounds(6, 185, 61, 16);
		contentPane.add(lblAnspruch);
		
		claimRadioButton = new JRadioButton("New radio button");
		claimRadioButton.setBounds(192, 181, 28, 23);
		contentPane.add(claimRadioButton);
		
		safetyTrainingRadioButton = new JRadioButton("New radio button");
		safetyTrainingRadioButton.setBounds(192, 89, 28, 23);
		contentPane.add(safetyTrainingRadioButton);
	}

	public void close(){
		person = new Person(Integer.parseInt(ageTextField.getText()),
				Integer.parseInt(lizenzTextField.getText()), 
				0, 
				safetyTrainingRadioButton.isSelected(), 
				newCustomerRadioButton.isSelected(), 
				claimRadioButton.isSelected());
		
		guiRental.addDriver(person, isTenant);
				
		this.setVisible(false);
	}
	
	public void setTenant(boolean isTenant){
		this.isTenant = isTenant;
	}
}
