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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import entities.Account;
import entities.Book;
import entities.Borrow;
import entities.BorrowDetail;
import entities.Pay;
import entities.PayDetail;
import models.AccountModel;
import models.BookModel;
import models.BorrowDetailModel;
import models.BorrowModel;
import models.PayDetailModel;
import models.PayModel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPanelDate extends JPanel {
	private JTextField jtextSearch;
	private JTable jtableAdmin;
	private JButton jbtnSearch;
	private Map<String, Object> values = new HashMap<String, Object>();
	private Account account;
	private JLabel jlblAdmin;

	/**
	 * Create the panel.
	 */
	public JPanelDate() {
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
		lblNewLabel.setIcon(new ImageIcon(JPanelDate.class.getResource("/resources/Profile.png")));
		panel.add(lblNewLabel);

		jlblAdmin = new JLabel("Borrow");

		jlblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlblAdmin.setForeground(Color.WHITE);
		panel.add(jlblAdmin);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1);

		JLabel lblNewLabel_2 = new JLabel("Date");
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
		String keyword = jtextSearch.getText().trim();
		if (keyword.isEmpty()) {
			JOptionPane.showMessageDialog(null, "nhap sdt");
		} else {
			AccountModel accountModel = new AccountModel();
			BorrowModel borrowModel = new BorrowModel();

			fillDataToJTable(borrowModel.find(accountModel.findPhone(keyword).getId()));
		}
	}

	public JPanelDate(Map<String, Object> values) {
		this();
		this.values = values;
		loadData();
	}

	private void loadData() {
		BorrowModel borrowModel = new BorrowModel();
		fillDataToJTable(borrowModel.findAll());

	}

	private void fillDataToJTable(List<Borrow> borrows) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Id");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Date_borrowed");
		defaultTableModel.addColumn("Status");
		AccountModel accountModel = new AccountModel();
		for (Borrow borrow : borrows) {
			defaultTableModel
					.addRow(new Object[] { borrow.getId(), accountModel.find(borrow.getId_account()).getFullname(),
							borrow.getDate_borrowed(), borrow.isStatus() ? "Unpaid" : "Paid", });
		}
		jtableAdmin.setModel(defaultTableModel);
		jtableAdmin.getTableHeader().setReorderingAllowed(false);
		jtableAdmin.setModel(defaultTableModel);
	}
}
