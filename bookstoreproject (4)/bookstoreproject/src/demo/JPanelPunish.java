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
import java.util.Calendar;
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
import entities.Punish;
import models.AccountModel;
import models.BookModel;
import models.BorrowDetailModel;
import models.BorrowModel;
import models.PunishModel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class JPanelPunish extends JPanel {
	private JTextField jtextSearch;
	private JTable jtableAdmin;
	private JButton jbtnDelete;
	private JButton jbtnEdit;
	private JButton jbtnSearch;
	private Map<String, Object> values = new HashMap<String, Object>();
	private Account account;
	private JLabel jlblAdmin;
	/**
	 * Create the panel.
	 */
	public JPanelPunish() {
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
		lblNewLabel.setIcon(new ImageIcon(JPanelPunish.class.getResource("/resources/Profile.png")));
		panel.add(lblNewLabel);
		
		jlblAdmin = new JLabel("Borrow");
		
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
				jbtnSearch_actionPerformed( e);
			}
		});
		panel_1.add(jbtnSearch);
		
		jbtnEdit = new JButton("Edit");
		jbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnEdit_actionPerformed( e);
			}
		});
		panel_1.add(jbtnEdit);
		
		jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDelete_actionPerformed( e);
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
				jtableAdmin_mouseClicked( e);
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
//			BorrowModel borrowModel = new BorrowModel();
//			
//			
//			fillDataToJTable(borrowModel.find(accountModel.findPhone(keyword).getId()));
//		}
	}
	public void jbtnDelete_actionPerformed(ActionEvent e) {
//		try {
//			int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION);
//			if (result == JOptionPane.YES_OPTION) {
//
//				AccountModel accountModel = new AccountModel();
//				int selectedRow = jtableAdmin.getSelectedRow();
//				int id = Integer.parseInt(jtableAdmin.getValueAt(selectedRow, 0).toString());
//				if (accountModel.delete(id)) {
//					fillDataToJTable(accountModel.findAll());
//					jbtnDelete.setEnabled(false);
//				} else {
//					JOptionPane.showMessageDialog(null, "Failed");
//				}
//			}
//		} catch (Exception e2) {
//			JOptionPane.showMessageDialog(null, e2.getMessage());
//		}
	}
	public void jbtnEdit_actionPerformed(ActionEvent e) {
		BorrowModel borrowModel = new BorrowModel();
		PunishModel punishModel = new PunishModel();
		BorrowDetailModel borrowDetailModel = new BorrowDetailModel();
		Date today= new Date(); 
		for(Borrow borrow : borrowModel.findAll()) {
			if (today.after(borrow.getDate_borrowed())) {
				Punish punish = new Punish();
				punish.setId_borrow(borrow.getId());
				for(BorrowDetail borrowDetail : borrowDetailModel.find(borrow.getId())) {
					punish.setId_book(borrowDetail.getId_book());
					punishModel.create(punish);
				}
			}
		}
		
		
		fillDataToJTable(punishModel.findAll());
		
		
	}
	public JPanelPunish(Map<String, Object> values) {
		this();
		this.values = values;
		 loadData();
	}
	private void loadData() {
		BorrowModel borrowModel = new BorrowModel();
		PunishModel punishModel = new PunishModel();
		
		
		
		
		fillDataToJTable(punishModel.findAll());

	

	}
	private void fillDataToJTable(List<Punish> punishs) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Id_Borrow");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Today");
		defaultTableModel.addColumn("Late_Date");
		BorrowModel borrowModel = new BorrowModel();
		BookModel bookModel = new BookModel();
		for(Punish punish : punishs) {
			defaultTableModel.addRow(new Object[] {
					punish.getId_borrow(),
					bookModel.searchBook(punish.getId_book()).getName(),
					punish.getToday(),
					(punish.getToday().getTime()- borrowModel.searchBorrow(punish.getId_borrow()).getDate_borrowed().getTime()) / (24 * 3600 * 1000)
	
			});
		}
		jtableAdmin.setModel(defaultTableModel);
		jtableAdmin.getTableHeader().setReorderingAllowed(false);
		jtableAdmin.setModel(defaultTableModel);
	}
}
