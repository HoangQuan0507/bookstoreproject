package demo;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import entities.Account;
import models.AccountModel;
import models.PositionModel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPanelAdmin extends JPanel {
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
	public JPanelAdmin() {
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
		lblNewLabel.setIcon(new ImageIcon(JPanelAdmin.class.getResource("/resources/Profile.png")));
		panel.add(lblNewLabel);
		
		jlblAdmin = new JLabel("Admin");
		
		jlblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlblAdmin.setForeground(Color.WHITE);
		panel.add(jlblAdmin);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
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
		String keyword = jtextSearch.getText().trim();
		if (keyword.isEmpty()) {
			JOptionPane.showMessageDialog(null, "nhap it nhat 1 ky tu");
		} else {
			AccountModel accountModel = new AccountModel();
			fillDataToJTable(accountModel.search(keyword));
		}
	}
	public void jbtnDelete_actionPerformed(ActionEvent e) {
		try {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {

				AccountModel accountModel = new AccountModel();
				int selectedRow = jtableAdmin.getSelectedRow();
				int id = Integer.parseInt(jtableAdmin.getValueAt(selectedRow, 0).toString());
				if (accountModel.find(id).getPosition_id()== 1) {
					if (accountModel.delete(id)) {
						fillDataToJTable(accountModel.findAll());
					}
				}else {
					JOptionPane.showMessageDialog(null, "You only can delete members");
				}
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
	public void jbtnEdit_actionPerformed(ActionEvent e) {
		int selectedRow = jtableAdmin.getSelectedRow();
		int id = Integer.parseInt(jtableAdmin.getValueAt(selectedRow, 0).toString());
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("id", id);
		JFrameEditAccount jframeEditAccount = new JFrameEditAccount(values);
		jframeEditAccount.setVisible(true);
		
		
	}
	public JPanelAdmin(Map<String, Object> values) {
		this();
		this.values = values;
		 loadData();
	}
	private void loadData() {
		AccountModel accountModel = new AccountModel();
		fillDataToJTable(accountModel.findAll());

	

	}
	private void fillDataToJTable(List<Account> accounts) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Id");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Phone");
		defaultTableModel.addColumn("Email");
		defaultTableModel.addColumn("Status");
		defaultTableModel.addColumn("Department");
		PositionModel positionModel = new PositionModel();
		for(Account account : accounts) {
			defaultTableModel.addRow(new Object[] {
					account.getId(),
					account.getFullname(),
					account.getPhone(),
					account.getEmail(),
					account.isStatus()? "Admin" : "Member",
					positionModel.find(account.getPosition_id()).getName()
			});
		}
		jtableAdmin.setModel(defaultTableModel);
		jtableAdmin.getTableHeader().setReorderingAllowed(false);
		jtableAdmin.setModel(defaultTableModel);
	}
}
