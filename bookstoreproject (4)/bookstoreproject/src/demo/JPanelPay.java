package demo;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import entities.Account;
import entities.Borrow;
import entities.BorrowDetail;
import entities.Pay;
import models.AccountModel;
import models.BorrowDetailModel;
import models.BorrowModel;
import models.PayModel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPanelPay extends JPanel {
	private JTextField jtextSearch;
	private JTable jtableAdmin;
	private JButton jbtnDelete;
	private JButton jbtnPay;
	private JButton jbtnSearch;
	private Map<String, Object> values = new HashMap<String, Object>();
	private Account account;
	private JLabel jlblAdmin;

	/**
	 * Create the panel.
	 */
	public JPanelPay() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(new Color(85, 65, 118));
		add(panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JPanelPay.class.getResource("/resources/Profile.png")));
		panel.add(lblNewLabel);

		jlblAdmin = new JLabel("Pay");

		jlblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlblAdmin.setForeground(Color.WHITE);
		panel.add(jlblAdmin);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1);

		JLabel lblNewLabel_2 = new JLabel("Phone");
		panel_1.add(lblNewLabel_2);

		jtextSearch = new JTextField();
		panel_1.add(jtextSearch);
		jtextSearch.setColumns(10);

		jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSearch_actionPerformed(e);
			}
		});
		panel_1.add(jbtnSearch);

		jbtnPay = new JButton("Pay");
		jbtnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnEdit_actionPerformed(e);
			}
		});
		panel_1.add(jbtnPay);

		jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDelete_actionPerformed(e);
			}
		});
		panel_1.add(jbtnDelete);

		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		jtableAdmin = new JTable();
		jtableAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtableAdmin_mouseClicked(e);
			}
		});
		scrollPane.setViewportView(jtableAdmin);
		loadData();

	}

	public void jtableAdmin_mouseClicked(MouseEvent e) {

	}

	public void jbtnSearch_actionPerformed(ActionEvent e) {
//		String keyword = jtextSearch.getText().trim();
//		if (keyword.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "nhap sdt");
//		} else {
//			AccountModel accountModel = new AccountModel();
//			PayModel payModel = new PayModel();
//
//			fillDataToJTable(payModel.find(accountModel.findPhone(keyword).getId()));
//		}
	}

	public void jbtnDelete_actionPerformed(ActionEvent e) {

	}

	public void jbtnEdit_actionPerformed(ActionEvent e) {

	}

	public JPanelPay(Map<String, Object> values) {
		this();
		this.values = values;
		loadData();
	}

	private void loadData() {
		PayModel payModel = new PayModel();
		fillDataToJTable(payModel.findAll());

	}

	private void fillDataToJTable(List<Pay> pays) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Id");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Pay_day");
		defaultTableModel.addColumn("Late_day");
		AccountModel accountModel = new AccountModel();
		BorrowModel borrowModel = new BorrowModel();
		for (Pay pay : pays) {
			defaultTableModel.addRow(new Object[] { pay.getId(),
					accountModel.find(borrowModel.searchBorrow(pay.getId_borrow()).getId_account()).getFullname(),
					pay.getPay_day(), pay.getLate_day() });
		}
		jtableAdmin.setModel(defaultTableModel);
		jtableAdmin.getTableHeader().setReorderingAllowed(false);
		jtableAdmin.setModel(defaultTableModel);
	}
}
