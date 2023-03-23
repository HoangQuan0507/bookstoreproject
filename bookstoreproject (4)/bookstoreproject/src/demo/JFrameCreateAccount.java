package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

import entities.Account;
import models.AccountModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class JFrameCreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField jtextName;
	private JTextField jtextFullname;
	private JDateChooser jdateChooser;
	private JPasswordField jpasswordField;
	private JTextField jtextPhone;
	private JTextField jtextEmail;
	private JTextArea jtextArea;

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
					JFrameCreateAccount frame = new JFrameCreateAccount();
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
	public JFrameCreateAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name *");
		lblNewLabel.setBounds(21, 21, 62, 19);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(21, 54, 62, 19);
		contentPane.add(lblNewLabel_1);

		jtextName = new JTextField();
		jtextName.setBounds(102, 21, 125, 28);
		contentPane.add(jtextName);
		jtextName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fullname");
		lblNewLabel_2.setBounds(21, 90, 62, 19);
		contentPane.add(lblNewLabel_2);

		jtextFullname = new JTextField();
		jtextFullname.setBounds(102, 85, 125, 28);
		contentPane.add(jtextFullname);
		jtextFullname.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Birthday");
		lblNewLabel_4.setBounds(21, 119, 78, 21);
		contentPane.add(lblNewLabel_4);

		jdateChooser = new JDateChooser();
		jdateChooser.setBounds(102, 119, 125, 28);
		jdateChooser.setDateFormatString("dd/MM/yyyy");
		contentPane.add(jdateChooser);

		JButton jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSave_actionPerformed(e);
			}
		});
		jbtnSave.setBounds(62, 280, 85, 28);
		contentPane.add(jbtnSave);

		JButton jbtnCancel = new JButton("Cancel");
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnCancel_actionPerformed(e);
			}
		});
		jbtnCancel.setBounds(190, 280, 85, 28);
		contentPane.add(jbtnCancel);

		jpasswordField = new JPasswordField();
		jpasswordField.setBounds(102, 54, 125, 28);
		contentPane.add(jpasswordField);

		JLabel lblPhone = new JLabel("Phone *");
		lblPhone.setBounds(21, 150, 62, 19);
		contentPane.add(lblPhone);

		jtextPhone = new JTextField();
		jtextPhone.setColumns(10);
		jtextPhone.setBounds(102, 150, 125, 28);
		contentPane.add(jtextPhone);

		jtextEmail = new JTextField();
		jtextEmail.setColumns(10);
		jtextEmail.setBounds(102, 179, 137, 28);
		jtextEmail.setText("@gmail.com");
		contentPane.add(jtextEmail);

		JLabel lblNewLabel_2_1_1 = new JLabel("Email *");
		lblNewLabel_2_1_1.setBounds(21, 179, 62, 19);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(21, 216, 62, 19);
		contentPane.add(lblAddress);

		jtextArea = new JTextArea();
		jtextArea.setLineWrap(true);
		jtextArea.setBounds(102, 211, 137, 57);
		contentPane.add(jtextArea);
	}

	public void jbtnCancel_actionPerformed(ActionEvent e) {
		JFrameLogin jFrameLogin = new JFrameLogin();
		jFrameLogin.setVisible(true);
		setVisible(false);
	}

	public void jbtnSave_actionPerformed(ActionEvent e) {
		try {

			Account account = new Account();
			account.setBirthday(jdateChooser.getDate());
			account.setPosition_id(1);
			account.setName(jtextName.getText());
			account.setEmail(jtextEmail.getText());
			account.setFullname(jtextFullname.getText());
			account.setPhone(jtextPhone.getText());
			account.setAddress(jtextArea.getText());
			String pass = new String(jpasswordField.getPassword());
			account.setPassword(BCrypt.hashpw(pass, BCrypt.gensalt()));
			AccountModel accountModel = new AccountModel();
			int result = 0;
			if (accountModel.find(jtextName.getText().trim()) != null ) {
				JOptionPane.showMessageDialog(null, "This name already exist");
				result += 1;
			} 
			if (accountModel.findPhone(jtextPhone.getText().trim()) != null ) {
				JOptionPane.showMessageDialog(null, "This phone already exist");
				result += 1;
			}
			if (jtextName.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Plz Enter your name");
				result += 1;
			}
			if (jtextPhone.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Plz Enter your phone");
				result += 1;
			}
			if (jtextEmail.getText().trim().equalsIgnoreCase("@gmail.com")) {
				JOptionPane.showMessageDialog(null, "Plz Enter your email");
				result += 1;
			}
			if (result == 0) {
				if (accountModel.create(account)) {
					JOptionPane.showMessageDialog(null, "Success");
					JFrameLogin jFrameLogin = new JFrameLogin();
					jFrameLogin.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
			

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
}
