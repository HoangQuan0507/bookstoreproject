package demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

import entities.Account;

import entities.Position;
import models.AccountModel;
import models.PositionModel;

import javax.mail.internet.NewsAddress;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class JFrameEditAccount extends JFrame {

	private JPanel contentPane;
	private JTextField jtextName;
	private JTextField jtextFullname;
	private JDateChooser jdateChooser;
	private JPasswordField jpasswordField;
	private JTextField jtextPhone;
	private JTextField jtextEmail;
	private JCheckBox jcheckAdmin;
	private Map<String, Object> values = new HashMap<String, Object>();
	private Account account;
	private JLabel lblNewLabel_2_2;
	private JTextArea jtextArea;
	private JComboBox jcomboBox;
	private JTextField jtextId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEditAccount frame = new JFrameEditAccount();
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
	public JFrameEditAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(21, 26, 62, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(21, 54, 62, 19);
		contentPane.add(lblNewLabel_1);
		
		jtextName = new JTextField();
		jtextName.setBounds(102, 21, 183, 28);
		contentPane.add(jtextName);
		jtextName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Fullname");
		lblNewLabel_2.setBounds(21, 90, 62, 19);
		contentPane.add(lblNewLabel_2);
		
		jtextFullname = new JTextField();
		jtextFullname.setBounds(102, 85, 183, 28);
		contentPane.add(jtextFullname);
		jtextFullname.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Birthday");
		lblNewLabel_4.setBounds(21, 121, 78, 21);
		contentPane.add(lblNewLabel_4);
		
		jdateChooser = new JDateChooser();
		jdateChooser.setBounds(102, 119, 93, 28);
		jdateChooser.setDateFormatString("dd/MM/yyyy");
		contentPane.add(jdateChooser);
		
		JButton jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSave_actionPerformed( e);
			}
		});
		jbtnSave.setBounds(76, 329, 85, 21);
		contentPane.add(jbtnSave);
		
		JButton jbtnCancel = new JButton("Cancel");
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnCancel_actionPerformed( e) ;
			}
		});
		jbtnCancel.setBounds(173, 329, 85, 21);
		contentPane.add(jbtnCancel);
		
		jpasswordField = new JPasswordField();
		jpasswordField.setBounds(102, 49, 183, 28);
		contentPane.add(jpasswordField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Position");
		lblNewLabel_2_1.setBounds(21, 234, 62, 19);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(21, 155, 62, 19);
		contentPane.add(lblPhone);
		
		jtextPhone = new JTextField();
		jtextPhone.setColumns(10);
		jtextPhone.setBounds(102, 150, 183, 28);
		contentPane.add(jtextPhone);
		
		jtextEmail = new JTextField();
		jtextEmail.setColumns(10);
		jtextEmail.setBounds(102, 189, 183, 28);
		
		contentPane.add(jtextEmail);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Email");
		lblNewLabel_2_1_1.setBounds(21, 194, 62, 19);
		contentPane.add(lblNewLabel_2_1_1);
		
		jcheckAdmin = new JCheckBox("Admin");
		jcheckAdmin.setBounds(207, 125, 93, 21);
		contentPane.add(jcheckAdmin);
		
		lblNewLabel_2_2 = new JLabel("Address");
		lblNewLabel_2_2.setBounds(21, 265, 62, 19);
		contentPane.add(lblNewLabel_2_2);
		
		jtextArea = new JTextArea();
		jtextArea.setBounds(102, 269, 183, 48);
		contentPane.add(jtextArea);
		
		jcomboBox = new JComboBox();
		jcomboBox.setBounds(102, 230, 93, 26);
		contentPane.add(jcomboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Id");
		lblNewLabel_3.setBounds(208, 235, 26, 19);
		contentPane.add(lblNewLabel_3);
		
		jtextId = new JTextField();
		jtextId.setEnabled(false);
		jtextId.setBounds(240, 229, 31, 28);
		contentPane.add(jtextId);
		jtextId.setColumns(10);
	
	}
	public JFrameEditAccount(Map<String, Object> values) {
		this();
		this.values = values;
		loadData();
	}
	private void loadData() {
		PositionModel positionModel = new PositionModel();
		DefaultComboBoxModel<Position> defaultComboBoxModel = new DefaultComboBoxModel<Position>();
		for(Position position : positionModel.findAll()) {
			defaultComboBoxModel.addElement(position);
		}
		jcomboBox.setModel(defaultComboBoxModel);
		jcomboBox.setRenderer(new PositionListCellRender());
		int id = Integer.parseInt(values.get("id").toString());
		AccountModel accountModel = new AccountModel();
		account = accountModel.find(id);
		jtextName.setText(account.getName());
		jtextPhone.setText(account.getPhone());
		jcheckAdmin.setSelected(account.isStatus());
		jdateChooser.setDate(account.getBirthday());
		jtextFullname.setText(account.getFullname());
		jtextEmail.setText(account.getEmail());
		jtextArea.setText(account.getAddress());
		jtextId.setText(values.get("id").toString());
		
	}
	private class PositionListCellRender extends DefaultListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			if (value instanceof Position) {
				Position position = (Position) value;
				value = position.getName();
			}
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}
		
	}
	public void jbtnCancel_actionPerformed(ActionEvent e) {
		
		setVisible(false);
	}
	public void jbtnSave_actionPerformed(ActionEvent e) {
		try {
			Position position = (Position) jcomboBox.getSelectedItem();
			Account account = new  Account();
			account.setId(Integer.parseInt(jtextId.getText()));
			account.setBirthday(jdateChooser.getDate());
			account.setPosition_id(position.getId());
			account.setName(jtextName.getText());
			account.setEmail(jtextEmail.getText());
			account.setFullname(jtextFullname.getText());
			account.setPhone(jtextPhone.getText());
			account.setAddress(jtextArea.getText());
			account.setStatus(jcheckAdmin.isSelected());
			String pass = new String(jpasswordField.getPassword());
			account.setPassword(BCrypt.hashpw(pass, BCrypt.gensalt()));
			AccountModel  accountModel= new AccountModel();
			int result = 0;
			if (accountModel.find(account.getName()) != null) {
				if (accountModel.find(account.getName()).getId()!= account.getId()) {
					JOptionPane.showMessageDialog(null, "This name already exist");
					result += 1;
				}
			}
			if (accountModel.findPhone(account.getPhone()) != null) {
				if (accountModel.findPhone(account.getPhone()).getId()!= account.getId()) {
					JOptionPane.showMessageDialog(null, "This phone already exist");
					result += 1;
				}
			}
			if (result == 0) {
				if (accountModel.update(account)) {
					JOptionPane.showMessageDialog(null, "Success");
					JPanelAdmin jPanelAdmin = new JPanelAdmin();
//					jPanelAdmin.setVisible(true);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
}
