package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Account;
import models.AccountModel;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class JFrameMember extends JFrame {

	private JPanel contentPane;
	private JPanel jpanelMain;
	private Map<String, Object> values = new HashMap<String, Object>();
	private Account account;
	private JMenuItem jmnLogout;
	private JMenu jmnMenu;
	private JButton jbtnBorrow;
	private JButton jbtnPay;
	private JButton jbtnInventory;

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
					JFrameMember frame = new JFrameMember();
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
	public JFrameMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		jmnMenu = new JMenu("Account");
		menuBar.add(jmnMenu);

		jmnLogout = new JMenuItem("Logout");
		jmnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jmnLogout_actionPerformed(e);
			}
		});
		jmnMenu.add(jmnLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel jpanelLeft = new JPanel();
		jpanelLeft.setBackground(new Color(54, 33, 89));
		contentPane.add(jpanelLeft, BorderLayout.WEST);
		jpanelLeft.setLayout(new BoxLayout(jpanelLeft, BoxLayout.Y_AXIS));

		jbtnBorrow = new JButton("Borrow");
		jbtnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnBorrow_actionPerformed(e);
			}
		});
		
		jbtnInventory = new JButton("Inventory");
		jbtnInventory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnInventory_actionPerformed( e) ;
			}
		});
		jbtnInventory.setIcon(new ImageIcon(JFrameMember.class.getResource("/resources/Search.png")));
		jbtnInventory.setForeground(Color.WHITE);
		jbtnInventory.setFocusPainted(false);
		jbtnInventory.setContentAreaFilled(false);
		jbtnInventory.setBorderPainted(false);
		jpanelLeft.add(jbtnInventory);
		jbtnBorrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnBorrow.setForeground(Color.WHITE);
		jbtnBorrow.setFocusPainted(false);
		jbtnBorrow.setContentAreaFilled(false);
		jbtnBorrow.setBorderPainted(false);
		jbtnBorrow.setIcon(new ImageIcon(JFrameMember.class.getResource("/resources/Picture.png")));
		jpanelLeft.add(jbtnBorrow);
		
		jbtnPay = new JButton("Pay");
		jbtnPay.setIcon(new ImageIcon(JFrameMember.class.getResource("/resources/Load.png")));
		jbtnPay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnPay_actionPerformed( e);
			}
		});
		jbtnPay.setForeground(Color.WHITE);
		jbtnPay.setFocusPainted(false);
		jbtnPay.setContentAreaFilled(false);
		jbtnPay.setBorderPainted(false);
		jpanelLeft.add(jbtnPay);

		jpanelMain = new JPanel();
		contentPane.add(jpanelMain, BorderLayout.CENTER);
		jpanelMain.setLayout(new BoxLayout(jpanelMain, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(54, 33, 89));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel = new JLabel("Coppyright 2021");
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
	}
	public void jbtnInventory_actionPerformed(ActionEvent e) {
		clearJPanel();
		JPanelInventoryMember jPanelInventoryMember = new JPanelInventoryMember();
		jpanelMain.add(jPanelInventoryMember);
		jPanelInventoryMember.setVisible(true);
	}
	public void jbtnPay_actionPerformed(ActionEvent e) {
		clearJPanel();
		JPanelPay jPanelPay = new JPanelPay();
		jpanelMain.add(jPanelPay);
		jPanelPay.setVisible(true);
	}
	public void jmnLogout_actionPerformed(ActionEvent e) {
		JFrameLogin jFrameLogin = new JFrameLogin();
		jFrameLogin.setVisible(true);
		setVisible(false);
	}

	public JFrameMember(Map<String, Object> values) {
		this();
		this.values = values;
		loadData();
	}

	private void loadData() {

		AccountModel accountModel = new AccountModel();
		account = accountModel.find(values.get("username").toString());
		
		jmnMenu.setText(account.getName());
		clearJPanel();

		JPanelAdmin jPanelAdmin = new JPanelAdmin();
		jpanelMain.add(jPanelAdmin);
		jPanelAdmin.setVisible(true);
	}



	private void clearJPanel() {
		jpanelMain.removeAll();
		jpanelMain.revalidate();
		jpanelMain.repaint();
	}

	public void jbtnBorrow_actionPerformed(ActionEvent e) {
		clearJPanel();
		JPanelBorrow jPanelBorrow = new JPanelBorrow();
		jpanelMain.add(jPanelBorrow);
		jPanelBorrow.setVisible(true);

	}
}
