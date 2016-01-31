package com.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class GuiRental extends JFrame implements IaddDriver {

	private Person tenant;
	private ArrayList<Person> drivers;
	
	private MyRequest request;
	private PersonGui personGui;
	
	private JPanel contentPane;
	private JTextField dayTextField;
	private JTextField durationOfRentalTextField;
	private JList carList;
	private JRadioButton automatikRadioButton;
	private JTextField monthTextField;
	
	private int driverCount;
	private DefaultListModel driverListModel;
	private JList driverList;
	

	/**
	 * Create the frame.
	 */
	public GuiRental() {
		drivers = new ArrayList<Person>();
		driverCount = 1;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModell = new JLabel("Modell");
		lblModell.setBounds(6, 45, 61, 16);
		contentPane.add(lblModell);
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setBounds(6, 111, 61, 16);
		contentPane.add(lblDatum);
		
		JLabel lblZeitraum = new JLabel("Zeitraum");
		lblZeitraum.setBounds(6, 146, 61, 16);
		contentPane.add(lblZeitraum);
		
		carList = new JList();
		carList.setLayoutOrientation(JList.VERTICAL_WRAP);
		carList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Kleinwagen", "Mittelklassewagen", "Kompaktwagen", "Oberklassewagen"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		carList.setBounds(79, 17, 134, 73);
		contentPane.add(carList);
		
		dayTextField = new JTextField();
		dayTextField.setBounds(79, 105, 61, 28);
		contentPane.add(dayTextField);
		dayTextField.setColumns(10);
		
		durationOfRentalTextField = new JTextField();
		durationOfRentalTextField.setBounds(79, 140, 134, 28);
		contentPane.add(durationOfRentalTextField);
		durationOfRentalTextField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setRequest();
			}
		});
		btnOk.setBounds(6, 360, 117, 29);
		contentPane.add(btnOk);
		
		JLabel lblMieter = new JLabel("Mieter");
		lblMieter.setBounds(6, 313, 61, 16);
		contentPane.add(lblMieter);
		
		JButton btnMieterAngaben = new JButton("Mieter Angaben");
		btnMieterAngaben.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPersonGui(true);
			}
		});
		btnMieterAngaben.setBounds(79, 308, 134, 29);
		contentPane.add(btnMieterAngaben);
		
		JButton btnFahrerHinzufgen = new JButton("Fahrer hinzuf\u00FCgen");
		btnFahrerHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showPersonGui(false);
			}
		});
		btnFahrerHinzufgen.setBounds(268, 360, 142, 29);
		contentPane.add(btnFahrerHinzufgen);
		
		JLabel lblAutomatik = new JLabel("Automatik");
		lblAutomatik.setBounds(6, 184, 71, 16);
		contentPane.add(lblAutomatik);
		
		automatikRadioButton = new JRadioButton("New radio button");
		automatikRadioButton.setBounds(130, 180, 28, 23);
		contentPane.add(automatikRadioButton);
		
		monthTextField = new JTextField();
		monthTextField.setBounds(152, 105, 61, 28);
		contentPane.add(monthTextField);
		monthTextField.setColumns(10);
		
		driverListModel = new DefaultListModel();		
		driverList = new JList(driverListModel);
		driverList.setBounds(237, 17, 207, 332);
		contentPane.add(driverList);
	}
	
	
	public Calendar getDate(){
		Calendar ca = Calendar.getInstance();
		ca.set(2016, Integer.parseInt(monthTextField.getText())-1, 
				Integer.parseInt(dayTextField.getText()));
		return ca;
	}
	
	public int getCarModel(){
		switch (carList.getSelectedIndex()){
			case 0:
				return CarModel.smallClass;
			case 1:
				return CarModel.middleClass;
			case 2:
				return CarModel.compactClass;
			case 3:
			default:
				return CarModel.upperClass;
		}
	}
	
	public void showPersonGui(boolean isTenant){
		personGui = new PersonGui(this);
		personGui.show(true);
		personGui.setTenant(isTenant);
	}
	
	public void setRequest(){
		this.request = new MyRequest(tenant, 
				getCarModel(), 
				getDate(), 
				Integer.parseInt(durationOfRentalTextField.getText()), 
				CarRental.getCarPool(), 
				drivers, 
				automatikRadioButton.isSelected());
		
		driverCount = 0;
		cleanGui();
		this.setVisible(false);
		this.dispose();
	}
	
	public void cleanGui(){
		dayTextField.setText("");
		durationOfRentalTextField.setText("");
		automatikRadioButton.setSelected(false);
		monthTextField.setText("");;
		driverCount = 0;
		driverListModel.removeAllElements();
		drivers = new ArrayList<Person>();
		tenant = new Person();
	}

	@Override
	public void addDriver(Person person, boolean tenant) {
		if(tenant){
			this.tenant = person;
		} 
		drivers.add(person);
		driverListModel.addElement("Fahrer" + driverCount + ":" + person.getAge());
		driverCount++;
	}

	public MyRequest getRequest() {
		return this.request;
	}

	public void setRequest(MyRequest request) {
		this.request = request;
	}
}
