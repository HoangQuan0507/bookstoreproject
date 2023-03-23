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
import entities.Book;
import models.AccountModel;
import models.AuthorModel;
import models.BookModel;
import models.CategoryModel;
import models.PositionModel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPanelInventory extends JPanel {
	private JTextField jtextSearch;
	private JTable jtableBook;
	private JButton jbtnDelete;
	private JButton jbtnEdit;
	private JButton jbtnSearch;
	private Map<String, Object> values = new HashMap<String, Object>();
	private Account account;
	private JLabel jlblAdmin;
	private JButton jbtnAuthor;
	private JButton jbtnCallNumber;
	/**
	 * Create the panel.
	 */
	public JPanelInventory() {
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
		lblNewLabel.setIcon(new ImageIcon(JPanelInventory.class.getResource("/resources/Profile.png")));
		panel.add(lblNewLabel);
		
		jlblAdmin = new JLabel("Inventory");
		
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
		
		jbtnAuthor = new JButton("Search Author");
		jbtnAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAuthor_actionPerformed(e);
			}
		});
		panel_1.add(jbtnAuthor);
		
		jbtnCallNumber = new JButton("Search Number");
		jbtnCallNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnCallNumber_actionPerformed(e);
			}
		});
		panel_1.add(jbtnCallNumber);
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
		
		jtableBook = new JTable();
		jtableBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtableAdmin_mouseClicked( e);
			}
		});
		scrollPane.setViewportView(jtableBook);
		 loadData();
		 
	}
	public void jtableAdmin_mouseClicked(MouseEvent e) {
		
	}
	public void jbtnSearch_actionPerformed(ActionEvent e) {
		String keyword = jtextSearch.getText().trim();
		BookModel bookModel = new BookModel();
		if (keyword.isEmpty()) {
			fillDataToJTable(bookModel.findAllInventory());
		} else {

			fillDataToJTable(bookModel.search(keyword));
		}
	}
	public void jbtnAuthor_actionPerformed(ActionEvent e) {
		String keyword = jtextSearch.getText().trim();
		AuthorModel authorModel = new AuthorModel();
		BookModel bookModel = new BookModel();
		int id_author = authorModel.find(keyword).getId();
		if (keyword.isEmpty()) {
			fillDataToJTable(bookModel.findAllInventory());
		} else {

			fillDataToJTable(bookModel.searchAuthor(id_author));
		}
		
	}
	public void jbtnCallNumber_actionPerformed(ActionEvent e) {
		String keyword = jtextSearch.getText().trim();
		BookModel bookModel = new BookModel();
		if (keyword.isEmpty()) {
			fillDataToJTable(bookModel.findAllInventory());
		} else {

			fillDataToJTable(bookModel.searchCallNumber(keyword));
		}
	}
	public void jbtnDelete_actionPerformed(ActionEvent e) {
		try {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {

				BookModel bookModel = new BookModel();
				int selectedRow = jtableBook.getSelectedRow();
				String name = jtableBook.getValueAt(selectedRow, 0).toString();
		
					if (bookModel.delete(bookModel.searchBook(name).getId())) {
						fillDataToJTable(bookModel.findAllInventory());
						
					}
					JOptionPane.showMessageDialog(null, "Book is deleted");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
	public void jbtnEdit_actionPerformed(ActionEvent e) {
		int selectedRow = jtableBook.getSelectedRow();
		int id = Integer.parseInt(jtableBook.getValueAt(selectedRow, 0).toString());
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("id", id);
		JFrameEditAccount jframeEditAccount = new JFrameEditAccount(values);
		jframeEditAccount.setVisible(true);
		
		
	}
	public JPanelInventory(Map<String, Object> values) {
		this();
		this.values = values;
		 loadData();
	}
	
	private void loadData() {
		BookModel bookModel = new BookModel();
		fillDataToJTable(bookModel.findAllInventory());

	

	}
	private void fillDataToJTable(List<Book> books) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

		};
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Category");
		defaultTableModel.addColumn("Author");
		defaultTableModel.addColumn("Status");
		defaultTableModel.addColumn("Price");
		defaultTableModel.addColumn("Quantity");
		defaultTableModel.addColumn("Call_Number");
		defaultTableModel.addColumn("ISBN");
		AuthorModel authorModel = new AuthorModel();
		CategoryModel categoryModel = new CategoryModel();
		for (Book book : books) {
			defaultTableModel.addRow(new Object[] { book.getName(), 
					categoryModel.find(book.getId_category()).getName(),
					authorModel.find(book.getId_author()).getName(),

					book.isStatus() ? "Inventory" : "Empty", book.getPrice(),
					book.getQuantity(),
					book.getCall_number(),
					book.getIsbn()

			});
		}
		jtableBook.setModel(defaultTableModel);
		jtableBook.getTableHeader().setReorderingAllowed(false);
		jtableBook.setModel(defaultTableModel);
	}

}
