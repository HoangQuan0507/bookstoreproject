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

public class JFrameDemo extends JFrame {

	private JPanel contentPane;
	private JPanel jpanelMain;
	private Map<String, Object> values = new HashMap<String, Object>();
	private Account account;
	private JButton jbtnUser;
	private JMenuItem jmnLogout;
	private JMenu jmnMenu;
	private JButton jbtnBooks;
	private JButton jbtnBorrow;
	private JButton jbtnPay;
	private JMenuItem jmnAddBook;
	private JButton jbtnInventory;
	private JMenuItem jmnEdit;

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
					JFrameDemo frame = new JFrameDemo();
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
	public JFrameDemo() {
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
		
		jmnEdit = new JMenuItem("Edit");
		jmnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jmnEdit_actionPerformed( e);
			}
		});
		jmnMenu.add(jmnEdit);
		jmnMenu.add(jmnLogout);
		
		JMenu mnNewMenu = new JMenu("Book");
		menuBar.add(mnNewMenu);
		
		jmnAddBook = new JMenuItem("AddBook");
		jmnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameCreateBook jFrameCreateBook = new JFrameCreateBook();
				jFrameCreateBook.setVisible(true);
			}
		});
		mnNewMenu.add(jmnAddBook);
		
		JMenuItem jmnAddAuthor = new JMenuItem("AddAuthor");
		jmnAddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameCreateAuthor jFrameCreateAuthor = new JFrameCreateAuthor();
				jFrameCreateAuthor.setVisible(true);
			}
		});
		mnNewMenu.add(jmnAddAuthor);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("AddCategory");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameCreateCategory jFrameCreateCategory = new JFrameCreateCategory();
				jFrameCreateCategory.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel jpanelLeft = new JPanel();
		jpanelLeft.setBackground(new Color(54, 33, 89));
		contentPane.add(jpanelLeft, BorderLayout.WEST);
		jpanelLeft.setLayout(new BoxLayout(jpanelLeft, BoxLayout.Y_AXIS));

		jbtnUser = new JButton("Admin");
		jbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnUser_actionPerformed(e);
			}
		});
		jbtnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnUser.setForeground(Color.WHITE);
		jbtnUser.setFocusPainted(false);
		jbtnUser.setBorderPainted(false);
		jbtnUser.setContentAreaFilled(false);
		jbtnUser.setIcon(new ImageIcon(JFrameDemo.class.getResource("/resources/Profile.png")));
		jpanelLeft.add(jbtnUser);

		jbtnBooks = new JButton("Books");
		jbtnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnBooks_actionPerformed(e);
			}
		});
		jbtnBooks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnBooks.setForeground(Color.WHITE);
		jbtnBooks.setBorderPainted(false);
		jbtnBooks.setContentAreaFilled(false);
		jbtnBooks.setFocusPainted(false);
		jbtnBooks.setIcon(new ImageIcon(JFrameDemo.class.getResource("/resources/Loading.png")));
		jpanelLeft.add(jbtnBooks);

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
		jbtnInventory.setIcon(new ImageIcon(JFrameDemo.class.getResource("/resources/Search.png")));
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
		jbtnBorrow.setIcon(new ImageIcon(JFrameDemo.class.getResource("/resources/Picture.png")));
		jpanelLeft.add(jbtnBorrow);
		
		jbtnPay = new JButton("Pay");
		jbtnPay.setIcon(new ImageIcon(JFrameDemo.class.getResource("/resources/Load.png")));
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
	public void jmnEdit_actionPerformed(ActionEvent e) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("id", account.getId());
		JFrameEditAccount jframeEditAccount = new JFrameEditAccount(values);
		jframeEditAccount.setVisible(true);
	}
	public void jbtnInventory_actionPerformed(ActionEvent e) {
		clearJPanel();
		JPanelInventory jPanelInventory = new JPanelInventory();
		jpanelMain.add(jPanelInventory);
		jPanelInventory.setVisible(true);
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

	public JFrameDemo(Map<String, Object> values) {
		this();
		this.values = values;
		loadData();
	}

	private void loadData() {

		AccountModel accountModel = new AccountModel();
		account = accountModel.find(values.get("username").toString());
		if (account.isStatus()) {
			jbtnUser.setText("Admin");
		} else {
			jbtnUser.setText("Manager");
		}
		jmnMenu.setText(account.getName());
		clearJPanel();

		JPanelAdmin jPanelAdmin = new JPanelAdmin();
		jpanelMain.add(jPanelAdmin);
		jPanelAdmin.setVisible(true);
	}

	public void jbtnUser_actionPerformed(ActionEvent e) {
		clearJPanel();

		JPanelAdmin jPanelAdmin = new JPanelAdmin();
		jpanelMain.add(jPanelAdmin);
		jPanelAdmin.setVisible(true);

	}

	public void btnNewButton_5_actionPerformed(ActionEvent e) {
		clearJPanel();
		JPanelStudent jPanelStudent = new JPanelStudent();
		jpanelMain.add(jPanelStudent);
		jPanelStudent.setVisible(true);

	}

	public void jbtnBooks_actionPerformed(ActionEvent e) {
		clearJPanel();
		JPanelStudent jPanelStudent = new JPanelStudent();
		jpanelMain.add(jPanelStudent);
		jPanelStudent.setVisible(true);

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
