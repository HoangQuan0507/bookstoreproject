package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import entities.Account;
import helper.MailHelper;
import helper.RandomHelper;
import models.AccountModel;
import models.ConnectDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class JFrameGetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField jtextEmail;
	private JButton jbtnSend;
	private JTextField jtextPhone;
	private JTextField jtextName;
	private JTextField jtextActive;
	private JLabel lblNewPassword_2;
	private JTextField jtextNewPassword;
	private Account account;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameGetPassword frame = new JFrameGetPassword();
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
	public JFrameGetPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(41, 22, 76, 27);
		contentPane.add(lblNewLabel);
		
		jtextEmail = new JTextField();
		jtextEmail.setBounds(109, 22, 166, 27);
		contentPane.add(jtextEmail);
		jtextEmail.setColumns(10);
		
		jbtnSend = new JButton("Send");
		jbtnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSend_actionPerformed( e);
			}
		});
		jbtnSend.setBounds(109, 96, 85, 21);
		contentPane.add(jbtnSend);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(41, 59, 76, 27);
		contentPane.add(lblPhone);
		
		jtextPhone = new JTextField();
		jtextPhone.setColumns(10);
		jtextPhone.setBounds(109, 59, 166, 27);
		contentPane.add(jtextPhone);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(41, 127, 76, 27);
		contentPane.add(lblName);
		
		jtextName = new JTextField();
		jtextName.setColumns(10);
		jtextName.setBounds(109, 127, 166, 27);
		contentPane.add(jtextName);
		
		JLabel lblNewPassword = new JLabel("Active Code");
		lblNewPassword.setBounds(41, 164, 76, 27);
		contentPane.add(lblNewPassword);
		
		jtextActive = new JTextField();
		jtextActive.setColumns(10);
		jtextActive.setBounds(109, 164, 166, 27);
		contentPane.add(jtextActive);
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_actionPerformed( e);
			}
		});
		btnNewButton.setBounds(109, 236, 132, 27);
		contentPane.add(btnNewButton);
		
		lblNewPassword_2 = new JLabel("New Password");
		lblNewPassword_2.setBounds(41, 201, 76, 27);
		contentPane.add(lblNewPassword_2);
		
		jtextNewPassword = new JTextField();
		jtextNewPassword.setColumns(10);
		jtextNewPassword.setBounds(109, 201, 166, 27);
		contentPane.add(jtextNewPassword);
	}
	public void jbtnSend_actionPerformed(ActionEvent e) {
		try {
			String secutityCode = RandomHelper.randomFromUUID();
			AccountModel accountModel = new AccountModel();
			account = accountModel.findPass(jtextEmail.getText(),jtextPhone.getText());
			if (account != null) {
				String content = "Hello" + account.getName() + "<br>Security Code : " + secutityCode;
				if (MailHelper.send("vanthjen.vnl@gmail.com", jtextEmail.getText(), "Active Account", content)) {
					
				}
				jtextName.setText(account.getName());
				accountModel.updatePass(account, secutityCode);
			}else {
				JOptionPane.showMessageDialog(null, "Failed");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
	public void btnNewButton_actionPerformed(ActionEvent e) {
		try {
			AccountModel accountModel = new AccountModel();
			if (accountModel.activePass(jtextName.getText(), jtextActive.getText())) {
				String password = BCrypt.hashpw(jtextNewPassword.getText(), BCrypt.gensalt());
				accountModel.updatePass(account, password);
				JOptionPane.showMessageDialog(null, "Success");
				setVisible(false);
				JFrameLogin jFrameLogin = new JFrameLogin();
				jFrameLogin.setVisible(true);
			}else {
				System.out.println("Invalid");
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}
	
}
