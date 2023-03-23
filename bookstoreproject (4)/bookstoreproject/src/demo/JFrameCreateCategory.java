package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.mindrot.jbcrypt.BCrypt;

import com.toedter.calendar.JDateChooser;

import entities.Account;
import entities.Author;
import entities.Book;
import entities.Category;
import models.AccountModel;
import models.AuthorModel;
import models.BookModel;
import models.CategoryModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;

public class JFrameCreateCategory extends JFrame {

	private JPanel contentPane;
	private JTextField jtextName;
	private JButton jbtnSave;
	private JButton jbtnCancel;
	private JTable jtable;
	private JButton jbtnEdit;
	private JButton jbtnDelete;
	private JButton jbtnUpdate;
	private int id;

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
					JFrameCreateCategory frame = new JFrameCreateCategory();
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
	public JFrameCreateCategory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Category", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel.setBounds(6, 6, 191, 103);
		contentPane.add(panel);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(25, 17, 90, 29);
		panel.add(lblName);

		jtextName = new JTextField();
		jtextName.setColumns(10);
		jtextName.setBounds(64, 17, 114, 28);
		panel.add(jtextName);

		jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSave_actionPerformed(e);
			}
		});
		jbtnSave.setBounds(25, 48, 60, 28);
		panel.add(jbtnSave);

		jbtnUpdate = new JButton("Update");
		jbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnUpdate_actionPerformed(e);
			}
		});
		jbtnUpdate.setBounds(97, 48, 81, 28);
		panel.add(jbtnUpdate);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "List Category", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel_1.setBounds(209, 6, 348, 241);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 19, 324, 163);
		panel_1.add(scrollPane);

		jtable = new JTable();
		scrollPane.setViewportView(jtable);

		jbtnEdit = new JButton("Edit");
		jbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnEdit_actionPerformed(e);
			}
		});
		jbtnEdit.setBounds(126, 194, 78, 28);
		panel_1.add(jbtnEdit);

		jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDelete_actionPerformed(e);
			}
		});
		jbtnDelete.setBounds(30, 194, 71, 28);
		panel_1.add(jbtnDelete);

		jbtnCancel = new JButton("Cancel");
		jbtnCancel.setBounds(228, 194, 81, 28);
		panel_1.add(jbtnCancel);
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		loadData();
	}

	public void jbtnDelete_actionPerformed(ActionEvent e) {
		try {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				CategoryModel categoryModel = new CategoryModel();
				int selectedRow = jtable.getSelectedRow();
				id = Integer.parseInt(jtable.getValueAt(selectedRow, 0).toString());
				if (categoryModel.delete(id)) {
					JOptionPane.showMessageDialog(null, "Success");
					fillDataToJTable(categoryModel.findAll());
				} else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}

	public void jbtnUpdate_actionPerformed(ActionEvent e) {
		CategoryModel categoryModel = new CategoryModel();
		Category category = new Category();
		category.setId(id);
		category.setName(jtextName.getText());
		
		if (categoryModel.update(category)) {
			JOptionPane.showMessageDialog(null, "Success");

			fillDataToJTable(categoryModel.findAll());
		} else {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}

	public void jbtnEdit_actionPerformed(ActionEvent e) {
		int selectedRow = jtable.getSelectedRow();
		id = Integer.parseInt(jtable.getValueAt(selectedRow, 0).toString());
		CategoryModel categoryModel = new CategoryModel();
		Category category = categoryModel.find(id);
		jtextName.setText(category.getName());


	}

	public void jbtnSave_actionPerformed(ActionEvent e) {
		try {

			Category category = new Category();

			category.setName(jtextName.getText());


			CategoryModel categoryModel = new CategoryModel();
			if (categoryModel.create(category)) {
				JOptionPane.showMessageDialog(null, "Success");
				fillDataToJTable(categoryModel.findAll());

			} else {
				JOptionPane.showMessageDialog(null, "Failed");
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}

	private void loadData() {
		CategoryModel categoryModel = new CategoryModel();

		fillDataToJTable(categoryModel.findAll());

	}

	private void fillDataToJTable(List<Category> categories) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

		};
		defaultTableModel.addColumn("Id");
		defaultTableModel.addColumn("Name");
		
		AuthorModel authorModel = new AuthorModel();

		for (Category category : categories) {
			defaultTableModel.addRow(new Object[] { category.getId(), category.getName() });
		}
		jtable.setModel(defaultTableModel);
		jtable.getTableHeader().setReorderingAllowed(false);
		jtable.setModel(defaultTableModel);
	}

}
