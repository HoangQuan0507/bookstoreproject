package demo;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import entities.Account;
import entities.Author;
import entities.Book;
import entities.Borrow;
import entities.BorrowDetail;
import entities.Category;
import models.AccountModel;
import models.AuthorModel;
import models.BookModel;
import models.BorrowDetailModel;
import models.BorrowModel;
import models.CategoryModel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class JPanelStudent extends JPanel {
	private JTable jtableBook;
	private JTable jtableChoose;
	private JTextField jtextField;
	private JComboBox jcomboxCategory;
	private JButton jbtnAdd;
	private List<Book> books = new ArrayList<Book>();
	private JButton jbtnDelete;
	private JButton jbtnClear;
	private JButton jbtnRent;
	private Account account;
	private Map<String, Object> values = new HashMap<String, Object>();
	private JTextField jtextDeposit;
	private JLabel lblNewLabel_2;
	private JTextField jtextAccount;
	private JButton jbtnAuthor;
	private JButton jbtnCallNumber;

	/**
	 * Create the panel.
	 */
	public JPanelStudent() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 60, 93, 0, 0, 0, 0, 74, 0, 69, 0, 0, 48, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 26, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(85, 65, 118));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridwidth = 18;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);

		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("List Book");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		jtextField = new JTextField();
		GridBagConstraints gbc_jtextField = new GridBagConstraints();
		gbc_jtextField.gridwidth = 4;
		gbc_jtextField.insets = new Insets(0, 0, 5, 5);
		gbc_jtextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtextField.gridx = 1;
		gbc_jtextField.gridy = 1;
		add(jtextField, gbc_jtextField);
		jtextField.setColumns(10);

		JButton jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSearch_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_jbtnSearch = new GridBagConstraints();
		gbc_jbtnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_jbtnSearch.gridx = 5;
		gbc_jbtnSearch.gridy = 1;
		add(jbtnSearch, gbc_jbtnSearch);

		jcomboxCategory = new JComboBox();
		jcomboxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcomboxCategory_actionPerformed(e);
			}
		});
		
		jbtnAuthor = new JButton("Search Author");
		jbtnAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAuthor_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_jbtnAuthor = new GridBagConstraints();
		gbc_jbtnAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_jbtnAuthor.gridx = 6;
		gbc_jbtnAuthor.gridy = 1;
		add(jbtnAuthor, gbc_jbtnAuthor);
		
		jbtnCallNumber = new JButton("Search Number");
		jbtnCallNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnCallNumber_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_jbtnCallNumber = new GridBagConstraints();
		gbc_jbtnCallNumber.insets = new Insets(0, 0, 5, 5);
		gbc_jbtnCallNumber.gridx = 8;
		gbc_jbtnCallNumber.gridy = 1;
		add(jbtnCallNumber, gbc_jbtnCallNumber);
		GridBagConstraints gbc_jcomboxCategory = new GridBagConstraints();
		gbc_jcomboxCategory.insets = new Insets(0, 0, 5, 5);
		gbc_jcomboxCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcomboxCategory.gridx = 10;
		gbc_jcomboxCategory.gridy = 1;
		add(jcomboxCategory, gbc_jcomboxCategory);

		jbtnAdd = new JButton("Add");
		jbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAdd_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_jbtnAdd = new GridBagConstraints();
		gbc_jbtnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_jbtnAdd.gridx = 11;
		gbc_jbtnAdd.gridy = 1;
		add(jbtnAdd, gbc_jbtnAdd);

		jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDelete_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_jbtnDelete = new GridBagConstraints();
		gbc_jbtnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_jbtnDelete.gridx = 12;
		gbc_jbtnDelete.gridy = 1;
		add(jbtnDelete, gbc_jbtnDelete);

		jbtnClear = new JButton("Clear All");
		jbtnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnClear_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_jbtnClear = new GridBagConstraints();
		gbc_jbtnClear.insets = new Insets(0, 0, 5, 5);
		gbc_jbtnClear.gridx = 13;
		gbc_jbtnClear.gridy = 1;
		add(jbtnClear, gbc_jbtnClear);
		
				jbtnRent = new JButton("Rent");
				jbtnRent.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jbtnRent_actionPerformed(e);
					}
				});
				
				jtextAccount = new JTextField();
				GridBagConstraints gbc_jtextAccount = new GridBagConstraints();
				gbc_jtextAccount.gridwidth = 2;
				gbc_jtextAccount.insets = new Insets(0, 0, 5, 5);
				gbc_jtextAccount.fill = GridBagConstraints.HORIZONTAL;
				gbc_jtextAccount.gridx = 14;
				gbc_jtextAccount.gridy = 1;
				add(jtextAccount, gbc_jtextAccount);
				jtextAccount.setColumns(10);
				GridBagConstraints gbc_jbtnRent = new GridBagConstraints();
				gbc_jbtnRent.anchor = GridBagConstraints.WEST;
				gbc_jbtnRent.insets = new Insets(0, 0, 5, 5);
				gbc_jbtnRent.gridx = 16;
				gbc_jbtnRent.gridy = 1;
				add(jbtnRent, gbc_jbtnRent);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 13;
		gbc_scrollPane.gridheight = 10;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		jtableBook = new JTable();
		scrollPane.setViewportView(jtableBook);
		
				lblNewLabel_2 = new JLabel("Deposit");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_2.gridx = 13;
				gbc_lblNewLabel_2.gridy = 2;
				add(lblNewLabel_2, gbc_lblNewLabel_2);

		jtextDeposit = new JTextField();
		jtextDeposit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jtextDeposit.setEnabled(false);
		GridBagConstraints gbc_jtextDeposit = new GridBagConstraints();
		gbc_jtextDeposit.insets = new Insets(0, 0, 5, 5);
		gbc_jtextDeposit.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtextDeposit.gridx = 15;
		gbc_jtextDeposit.gridy = 2;
		add(jtextDeposit, gbc_jtextDeposit);
		jtextDeposit.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridheight = 9;
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 13;
		gbc_scrollPane_1.gridy = 3;
		add(scrollPane_1, gbc_scrollPane_1);

		jtableChoose = new JTable();
		scrollPane_1.setViewportView(jtableChoose);
		loadData();

	}
	public void jbtnCallNumber_actionPerformed(ActionEvent e) {
		String keyword = jtextField.getText().trim();
		BookModel bookModel = new BookModel();
		if (keyword.isEmpty()) {
			fillDataToJTable(bookModel.findAll());
		} else {

			fillDataToJTable(bookModel.searchCallNumber(keyword));
		}
	}
	public void jbtnAuthor_actionPerformed(ActionEvent e) {
		String keyword = jtextField.getText().trim();
		AuthorModel authorModel = new AuthorModel();
		BookModel bookModel = new BookModel();
		int id_author = authorModel.find(keyword).getId();
		if (keyword.isEmpty()) {
			fillDataToJTable(bookModel.findAll());
		} else {

			fillDataToJTable(bookModel.searchAuthor(id_author));
		}
		
	}
	public JPanelStudent(Map<String, Object> values) {
		this();
		this.values = values;
		loadData();
	}

	public void jbtnRent_actionPerformed(ActionEvent e) {
		BookModel bookModel = new BookModel();
		if (bookModel.findAllCart().size() == 0) {
			JOptionPane.showMessageDialog(null, "please add 1 book");
		} else {
			AccountModel accountModel = new AccountModel();
			String keyword = jtextAccount.getText().trim();
			
			if (keyword.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter your account");
			} else {
				account = accountModel.findPhone(keyword);
//				account = accountModel.find(values.get("username").toString());
				System.out.println(account.getFullname());
//				BookModel bookModel = new BookModel();
				try {
					Borrow borrow = new Borrow();
					borrow.setId_account(account.getId());

					Date today = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(today);

					borrow.setDate_borrowed(today);
					calendar.add(Calendar.DATE, 7);
					borrow.setPay_day(calendar.getTime());

					BorrowModel borrowModel = new BorrowModel();
					int id = borrowModel.create(borrow);
					if (id != 0) {
						for (Book book : bookModel.findAllCart()) {
							
							BorrowDetail borrowDetail = new BorrowDetail();
							borrowDetail.setId_borrow(id);
							borrowDetail.setId_book(book.getId());
							borrowDetail.setDeposit(book.getPrice());
							BorrowDetailModel borrowDetailModel = new BorrowDetailModel();
							borrowDetailModel.create(borrowDetail);
							if (book.getQuantity() == 1) {
								bookModel.updateStatusEmpty(book);
							}
							bookModel.updateBorrow(book);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Failed");
					}
					int deposit = Integer.parseInt(jtextDeposit.getText());
					JOptionPane.showMessageDialog(null, deposit);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				fillDataToJTable(bookModel.findAll());
				jbtnClear_actionPerformed( e);
			}

		}
	}

	public void jbtnDelete_actionPerformed(ActionEvent e) {

		BookModel bookModel = new BookModel();
		int selectedRow = jtableChoose.getSelectedRow();
		String name = jtableChoose.getValueAt(selectedRow, 0).toString();
		bookModel.deleteCart(bookModel.searchBook(name).getId());
		fillDataToJTableChoose(bookModel.findAllCart());
		int deposit = Integer.parseInt(jtextDeposit.getText());
		deposit = (int) (deposit - bookModel.searchBook(name).getPrice());
		jtextDeposit.setText(String.valueOf(deposit));
	}

	public void jbtnClear_actionPerformed(ActionEvent e) {
		BookModel bookModel = new BookModel();
		bookModel.deleteAllCart();
		fillDataToJTableChoose(bookModel.findAllCart());
//		books.clear();
		DefaultTableModel model = new DefaultTableModel();
		jtableChoose.setModel(model);
		jtextDeposit.setText("0");
	}

	public void jbtnAdd_actionPerformed(ActionEvent e) {
		int selectedRow = jtableBook.getSelectedRow();
		String name = jtableBook.getValueAt(selectedRow, 0).toString();
		System.out.println(name);
		BookModel bookModel = new BookModel();
		if (bookModel.findAllCart().size() >= 3) {
			JOptionPane.showMessageDialog(null, "only select 3 book");
		} else {
			int count = 0;
			for (Book book : bookModel.findAllCart()) {
				if (book.getId() == bookModel.searchBook(name).getId()) {
					count += 1;
				}
			}
			if (count == 0) {
//				books.add(bookModel.searchBook(name));
				bookModel.createCart(bookModel.searchBook(name));
				int deposit = Integer.parseInt(jtextDeposit.getText());
				deposit = (int) (deposit + bookModel.searchBook(name).getPrice());
				jtextDeposit.setText(String.valueOf(deposit));
				fillDataToJTableChoose(bookModel.findAllCart());
			}else {
				JOptionPane.showMessageDialog(null, "book already exist");
			}
			
		}

	}

	public void jcomboxCategory_actionPerformed(ActionEvent e) {
		BookModel bookModel = new BookModel();
		Category category = (Category) jcomboxCategory.getSelectedItem();
		fillDataToJTable(bookModel.searchCategory(category.getId()));
	}

	public void jbtnSearch_actionPerformed(ActionEvent e) {
		String keyword = jtextField.getText().trim();
		BookModel bookModel = new BookModel();
		if (keyword.isEmpty()) {
			fillDataToJTable(bookModel.findAll());
		} else {

			fillDataToJTable(bookModel.search(keyword));
		}
	}

	private void loadData() {
		CategoryModel categoryModel = new CategoryModel();
		DefaultComboBoxModel<Category> defaultComboBoxModel = new DefaultComboBoxModel<Category>();
		for (Category category : categoryModel.findAll()) {
			defaultComboBoxModel.addElement(category);
		}
		AuthorModel authorModel = new AuthorModel();
		DefaultComboBoxModel<Author> defaultComboBoxModel2 = new DefaultComboBoxModel<Author>();
		for (Author author : authorModel.findAll()) {
			defaultComboBoxModel2.addElement(author);
		}

		jcomboxCategory.setModel(defaultComboBoxModel);
		jcomboxCategory.setRenderer(new CategoryListCellRender());
		BookModel bookModel = new BookModel();
		fillDataToJTable(bookModel.findAll());
		fillDataToJTableChoose(bookModel.findAllCart());
		jtextDeposit.setText("0");
	}

	private class CategoryListCellRender extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			if (value instanceof Category) {
				Category category = (Category) value;
				value = category.getName();
			}
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}

	}

	private class AuthorListCellRender extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			if (value instanceof Author) {
				Author author = (Author) value;
				value = author.getName();
			}
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}

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

	private void fillDataToJTableChoose(List<Book> books) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

		};
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Price");
		for (Book book : books) {
			defaultTableModel.addRow(new Object[] { book.getName(), book.getPrice(),

			});
			jtableChoose.setModel(defaultTableModel);
			jtableChoose.getTableHeader().setReorderingAllowed(false);
			jtableChoose.setModel(defaultTableModel);

		}
	}
}
