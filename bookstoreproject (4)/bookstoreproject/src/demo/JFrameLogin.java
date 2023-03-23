package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.AccountModel;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class JFrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldUsername;
	private JPasswordField jpasswordField;
	private JButton jbtnLogin;
	private JButton JbtnCreate;
	private JButton JbtnForget;

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
					JFrameLogin frame = new JFrameLogin();
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
	public JFrameLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jtextFieldUsername = new JTextField();
		jtextFieldUsername.setColumns(10);
		jtextFieldUsername.setBounds(109, 91, 198, 28);
		contentPane.add(jtextFieldUsername);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(35, 91, 81, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(35, 122, 95, 40);
		contentPane.add(lblNewLabel_1);
		
		jpasswordField = new JPasswordField();
		jpasswordField.setBounds(109, 128, 198, 28);
		contentPane.add(jpasswordField);
		
		jbtnLogin = new JButton("Login");
		jbtnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnLogin_actionPerformed( e);
			}
		});
		jbtnLogin.setBounds(139, 170, 111, 35);
		contentPane.add(jbtnLogin);
		
		JbtnForget = new JButton("Forget password ?");
		JbtnForget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbtnForget_actionPerformed( e);
			}
		});
		JbtnForget.setBounds(201, 217, 126, 28);
		contentPane.add(JbtnForget);
		
		JbtnCreate = new JButton("Sign Up");
		JbtnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbtnCreate_actionPerformed( e) ;
			}
		});
		JbtnCreate.setBounds(63, 217, 126, 28);
		contentPane.add(JbtnCreate);
		
		JLabel lblNewLabel_2 = new JLabel("NOOB TEAM");
		lblNewLabel_2.setBounds(173, 39, 95, 28);
		contentPane.add(lblNewLabel_2);
	}
	public void JbtnCreate_actionPerformed(ActionEvent e) {
		JFrameCreateAccount jFrameCreateAccount = new JFrameCreateAccount();
		jFrameCreateAccount.setVisible(true);
		setVisible(false);
	}
	public void JbtnForget_actionPerformed(ActionEvent e) {
		JFrameGetPassword jFrameGetPassword = new JFrameGetPassword();
		jFrameGetPassword.setVisible(true);
		setVisible(false);
	}
	public void jbtnLogin_actionPerformed(ActionEvent e) {
		AccountModel accountModel = new AccountModel();
		String password = new String(jpasswordField.getPassword());
		String username = jtextFieldUsername.getText().trim();
		
		if (accountModel.login(username, password)== null) {
			JOptionPane.showMessageDialog(null, "Invalid");
		}else if(accountModel.login(username, password).getPosition_id()==1){
			JOptionPane.showMessageDialog(null, "This Account can't Login ");
		}else {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("username", username);
			JFrameDemo jFrameDemo = new JFrameDemo(values);
			JPanelStudent jPanelStudent= new JPanelStudent(values);
			jFrameDemo.setVisible(true);
			setVisible(false);
			
		}
	}
}
