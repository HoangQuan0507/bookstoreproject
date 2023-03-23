package demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import entities.Account;
import entities.Author;
import entities.Book;
import entities.Category;
import entities.Position;
import helper.GetChar;
import helper.NumberHelper;
import models.AccountModel;
import models.AuthorModel;
import models.BookModel;
import models.CategoryModel;
import models.PositionModel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class JFrameCreateBook extends JFrame {

	private JPanel contentPane;
	private JTextField jtextName;
	private JTextField jtextQuantity;
	private JTextField jtextPrice;
	private JComboBox jcomboCategory;
	private JComboBox jcomboAuthor;
	private JTextArea jtextArea;
	private JButton jbtnSave;
	private JButton jbtnCancel;

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
					JFrameCreateBook frame = new JFrameCreateBook();
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
	public JFrameCreateBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 287, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(22, 19, 65, 27);
		contentPane.add(lblNewLabel);

		jtextName = new JTextField();
		jtextName.setBounds(99, 18, 156, 28);
		contentPane.add(jtextName);
		jtextName.setColumns(10);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(22, 44, 65, 27);
		contentPane.add(lblCategory);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(22, 70, 65, 27);
		contentPane.add(lblAuthor);

		jcomboCategory = new JComboBox();
		jcomboCategory.setBounds(99, 44, 156, 26);
		contentPane.add(jcomboCategory);

		jcomboAuthor = new JComboBox();
		jcomboAuthor.setBounds(99, 70, 156, 26);
		contentPane.add(jcomboAuthor);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(22, 98, 65, 27);
		contentPane.add(lblQuantity);

		jtextQuantity = new JTextField();
		jtextQuantity.setColumns(10);
		jtextQuantity.setBounds(99, 97, 156, 28);
		contentPane.add(jtextQuantity);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(22, 127, 65, 27);
		contentPane.add(lblPrice);

		jtextPrice = new JTextField();
		jtextPrice.setColumns(10);
		jtextPrice.setBounds(99, 126, 156, 28);
		contentPane.add(jtextPrice);

		JLabel lblTittle = new JLabel("Tittle");
		lblTittle.setBounds(22, 157, 65, 27);
		contentPane.add(lblTittle);

		jtextArea = new JTextArea();
		jtextArea.setBounds(99, 156, 156, 92);
		contentPane.add(jtextArea);

		jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSave_actionPerformed(e);
			}
		});
		jbtnSave.setBounds(45, 266, 86, 28);
		contentPane.add(jbtnSave);

		jbtnCancel = new JButton("Cancel");
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnCancel_actionPerformed(e);
			}
		});
		jbtnCancel.setBounds(146, 266, 86, 28);
		contentPane.add(jbtnCancel);
		loadData();
	}

	public void jbtnCancel_actionPerformed(ActionEvent e) {
		setVisible(false);
	}

	private void loadData() {
		CategoryModel categoryModel = new CategoryModel();
		DefaultComboBoxModel<Category> defaultComboBoxModel = new DefaultComboBoxModel<Category>();
		for (Category category : categoryModel.findAll()) {
			defaultComboBoxModel.addElement(category);
		}
		jcomboCategory.setModel(defaultComboBoxModel);
		jcomboCategory.setRenderer(new CategoryListCellRender());
		AuthorModel authorModel = new AuthorModel();
		DefaultComboBoxModel<Author> defaultComboBoxModel2 = new DefaultComboBoxModel<Author>();
		for (Author author : authorModel.findAll()) {
			defaultComboBoxModel2.addElement(author);
		}
		jcomboAuthor.setModel(defaultComboBoxModel2);
		jcomboAuthor.setRenderer(new AuthorListCellRender());
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

	public void jbtnSave_actionPerformed(ActionEvent e) {
		try {

			Author author = (Author) jcomboAuthor.getSelectedItem();
			Category category = (Category) jcomboCategory.getSelectedItem();
			Book book = new Book();
			book.setQuantity(Integer.parseInt(jtextQuantity.getText()));
			book.setPrice(Double.parseDouble(jtextPrice.getText()));
			book.setName(jtextName.getText());
			book.setId_author(author.getId());
			book.setId_category(category.getId());
			Date time = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh");
			book.setIsbn(String.valueOf(100 + author.getId()) +"-"+ simpleDateFormat2.format(time)+ simpleDateFormat.format(time));
			book.setTittle(jtextArea.getText());
			String[] au = author.getName().split(" ");
			String[] cate = category.getName().split(" ");
			int number = 100 + author.getId() * 10 + category.getId();
			String callnumber = GetChar.getChar(au[0]) + "-" + GetChar.getChar(cate[0]) + "-" + String.valueOf(number);
			book.setCall_number(callnumber);
			BookModel bookModel = new BookModel();
			if (bookModel.create(book)) {
				JOptionPane.showMessageDialog(null, "Success");

//				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Failed");
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
}
